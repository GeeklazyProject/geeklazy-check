package com.geeklazy.check.rule.service.impl;

import com.geeklazy.check.common.CheckArrangeConfig;
import com.geeklazy.check.common.CheckTargetConfig;
import com.geeklazy.check.common.enums.ContainEnum;
import com.geeklazy.check.rule.controller.response.CheckRule4TaskResponse;
import com.geeklazy.check.record.entity.CheckRecord;
import com.geeklazy.check.record.service.CheckRecordService;
import com.geeklazy.check.rule.entity.CheckRule;
import com.geeklazy.check.rule.entity.CheckRuleSubscribe;
import com.geeklazy.check.rule.repository.CheckRuleRepository;
import com.geeklazy.check.rule.repository.CheckRuleSubscribeRepository;
import com.geeklazy.check.rule.service.CheckRuleService;
import com.geeklazy.frame.exception.BizException;
import com.geeklazy.frame.exception.ResultCode;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/25 15:30
 * @Description
 */
@Service
public class CheckRuleServiceImpl implements CheckRuleService {
    private static final List<DayOfWeek> WEEKENDS = List.of(DayOfWeek.SATURDAY, DayOfWeek.SUNDAY);
    @Autowired
    private CheckRuleRepository checkRuleRepository;
    @Autowired
    private CheckRuleSubscribeRepository checkRuleSubscribeRepository;
    @Autowired
    private CheckRecordService checkRecordService;

    @Override
    public Integer create(String service, Integer creatorId, CheckRule checkRule) {
        Optional<CheckRule> optional = checkRuleRepository.findByServiceAndCreatorIdAndName(service, creatorId, checkRule.getName());
        optional.ifPresent(rule -> {
            throw new BizException(ResultCode.ERROR_CODE_FOR_UNKNOWN_ERROR, "存在同名规则");
        });

        checkRule.setService(service);
        checkRule.setCreatorId(creatorId);
        checkRuleRepository.save(checkRule);

        return checkRule.getId();
    }

    @Override
    public void subscribe(Integer ruleId, Integer recorderId) {
        Optional<CheckRule> ruleOptional = checkRuleRepository.findById(ruleId);
        if (ruleOptional.isEmpty()) {
            throw new BizException(ResultCode.ERROR_CODE_FOR_UNKNOWN_ERROR, "规则不存在");
        }
        Optional<CheckRuleSubscribe> subscribeOptional = checkRuleSubscribeRepository.findByRuleIdAndRecorderId(ruleId, recorderId);
        subscribeOptional.ifPresent(ruleSubscribe -> {
            throw new BizException(ResultCode.ERROR_CODE_FOR_UNKNOWN_ERROR, "已订阅改规则");
        });

        CheckRuleSubscribe checkRuleSubscribe = new CheckRuleSubscribe();
        checkRuleSubscribe.setRuleId(ruleId);
        checkRuleSubscribe.setRecorderId(recorderId);
        checkRuleSubscribeRepository.save(checkRuleSubscribe);
    }

    @Override
    public List<CheckRule4TaskResponse> getSubscribeRuleWithRecord(String service, Integer recorderId, LocalDate date) {
        // 获取订阅任务列表 WHERE subscribeDate <= date
        List<CheckRule> subscribeRules = checkRuleRepository.findRunningSubscribeCheckRule(service, recorderId, date);
        // 遍历订阅任务
        return subscribeRules.stream()
                .filter(checkRule -> {
                    boolean flag = true;
                    switch (checkRule.getArrangeConfig().getCycleType()) {
                        case DAILY -> {
                            if (checkRule.getArrangeConfig().getSpecialConfig() != null) {
                                // 工作日设定
                                if (checkRule.getArrangeConfig().getSpecialConfig().getWeekdayContain() == ContainEnum.EXCLUDE) {
                                    if (!WEEKENDS.contains(date.getDayOfWeek())) {
                                        flag = false;
                                    }
                                }
                                // 周末设定
                                if (checkRule.getArrangeConfig().getSpecialConfig().getWeekendContain() == ContainEnum.EXCLUDE) {// 排除周末
                                    if (WEEKENDS.contains(date.getDayOfWeek())) {
                                        flag = false;
                                    }
                                }
                            }
                        } case WEEKLY -> {
                            flag = checkRule.getArrangeConfig().getCycleOffsets().stream().anyMatch(offset -> date.getDayOfWeek() == DayOfWeek.of(Integer.parseInt(offset)));
                            if (!flag) return false;

                            // 工作日设定
                            if (checkRule.getArrangeConfig().getSpecialConfig().getWeekdayContain() == ContainEnum.EXCLUDE) {
                                if (!WEEKENDS.contains(date.getDayOfWeek())) {
                                    flag = false;
                                }
                            }
                            // 周末设定
                            if (checkRule.getArrangeConfig().getSpecialConfig().getWeekendContain() == ContainEnum.EXCLUDE) {// 排除周末
                                if (WEEKENDS.contains(date.getDayOfWeek())) {
                                    flag = false;
                                }
                            }
                        }
                    }
                    return flag;
                })
                .map(checkRule -> {
                    CheckRule4TaskResponse rule4Task = new CheckRule4TaskResponse();
                    rule4Task.setRuleId(checkRule.getId());
                    rule4Task.setName(checkRule.getName());
                    rule4Task.setIcon(checkRule.getIcon());
                    rule4Task.setRunning(false);

                    LocalDate startDate = null;
                    LocalDate endDate = null;
                    switch (checkRule.getTargetConfig().getCycleType()) {
                        case DAILY -> {
                            startDate = date;
                            endDate = date;
                        }
                        case WEEKLY -> {
                            startDate = date.with(TemporalAdjusters.previousOrSame(DayOfWeek.MONDAY));
                            endDate = date.with(TemporalAdjusters.nextOrSame(DayOfWeek.SUNDAY));
                        }
                        case MONTHLY -> {
                            startDate = date.with(TemporalAdjusters.firstDayOfMonth());
                            endDate = date.with(TemporalAdjusters.lastDayOfMonth());
                        }
                        case YEARLY -> {
                            startDate = date.with(TemporalAdjusters.firstDayOfYear());
                            endDate = date.with(TemporalAdjusters.lastDayOfYear());
                        }
                    }
                    List<CheckRecord> checkRecords = checkRecordService.getRecords(checkRule.getId(), recorderId, startDate, endDate);
                    if (checkRecords != null) {
                        Integer totalCount = checkRecords.stream().map(CheckRecord::getCount).filter(Objects::nonNull).reduce(0, Integer::sum);
                        Integer totalDuration = checkRecords.stream().map(CheckRecord::getDuration).filter(Objects::nonNull).reduce(0, Integer::sum);
                        rule4Task.setCount(totalCount);
                        rule4Task.setDuration(totalDuration);
                        Optional<CheckRecord> lastRecord = checkRecords.stream()
                                .max(Comparator.comparing(CheckRecord::getInDt));
                        lastRecord.ifPresent(checkRecord -> {
                            rule4Task.setRunning(checkRecord.isRunning());
                            rule4Task.setLastIn(checkRecord.isRunning() ? checkRecord.getInDt().toLocalTime().toSecondOfDay() : null);
                        });
                    }

                    rule4Task.setTargetConfig(new CheckTargetConfig());
                    BeanUtils.copyProperties(checkRule.getTargetConfig(), rule4Task.getTargetConfig());

                    rule4Task.setArrangeConfig(new CheckArrangeConfig());
                    BeanUtils.copyProperties(checkRule.getArrangeConfig(), rule4Task.getArrangeConfig());

                    return rule4Task;
                }).collect(Collectors.toList());
    }

    @Override
    public CheckRule getRule(Integer id) {
        return checkRuleRepository.findById(id).orElseThrow(() -> new BizException(ResultCode.ERROR_CODE_FOR_UNKNOWN_ERROR, "不存在该规则"));
    }
}
