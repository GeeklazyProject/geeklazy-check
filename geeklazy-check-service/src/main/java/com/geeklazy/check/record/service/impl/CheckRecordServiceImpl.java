package com.geeklazy.check.record.service.impl;

import com.alibaba.fastjson2.JSONObject;
import com.geeklazy.check.record.entity.CheckRecord;
import com.geeklazy.check.record.handler.annotation.Handler;
import com.geeklazy.check.record.repository.CheckRecordRepository;
import com.geeklazy.check.record.service.CheckRecordService;
import com.geeklazy.check.rule.entity.CheckRule;
import com.geeklazy.check.task.entity.CheckTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/20 10:46
 * @Description
 */
@Slf4j
@Service
public class CheckRecordServiceImpl implements CheckRecordService {
    private final Map<String, Object> handlerMap;

    private final CheckRecordRepository checkRecordRepository;

    public CheckRecordServiceImpl(ApplicationContext context, CheckRecordRepository checkRecordRepository) {
        this.handlerMap = context.getBeansWithAnnotation(Handler.class);
        this.checkRecordRepository = checkRecordRepository;
    }

    @Override
    public void checkIn(CheckRule checkRule, Integer taskId, Integer recorderId) {
        LocalDateTime now = LocalDateTime.now();
//        validateCheckIn(checkTask, now);
        Optional<CheckRecord> exits = checkRecordRepository.findFirstByRuleIdAndRecorderIdAndDateEqualsAndRunningTrueOrderByInDtDesc(taskId, recorderId, now.toLocalDate());

        if (exits.isPresent()) {
            log.warn("已有记录");
        } else {

            CheckRecord checkRecord = new CheckRecord();
            checkRecord.setRuleId(taskId);
            checkRecord.setRecorderId(recorderId);
            checkRecord.setDate(now.toLocalDate());
            checkRecord.setRunning(true);
            checkRecord.setInDt(now);
            checkRecordRepository.save(checkRecord);
        }
    }

    @Override
    public void checkOut(CheckRule checkRule, Integer taskId, Integer recorderId) {
        LocalDateTime now = LocalDateTime.now();
//        validateCheckOut(checkTask, now);
        Optional<CheckRecord> exits = checkRecordRepository.findFirstByRuleIdAndRecorderIdAndDateEqualsAndRunningTrueOrderByInDtDesc(taskId, recorderId, now.toLocalDate());

        if (exits.isPresent()) {
            int duration = now.toLocalTime().toSecondOfDay() - exits.get().getInDt().toLocalTime().toSecondOfDay();
            exits.get().setOutDt(now);
            exits.get().setDuration(duration);
            exits.get().setRunning(false);
            checkRecordRepository.save(exits.get());
//                    checkRecordRepository.checkOut(request.getTaskId(), request.getRecorderId(), now);
        } else {
            System.out.println("not exist");
        }
    }

    @Override
    public List<CheckRecord> getRecords(Integer ruleId, Integer recorderId, LocalDate startDate, LocalDate endDate) {
        return checkRecordRepository.findAllByRuleIdAndRecorderIdAndDateBetween(ruleId, recorderId, startDate, endDate);
    }

    boolean validateCheckIn(CheckTask checkTask, LocalDateTime inTime) {
        CheckRule checkRule = JSONObject.parseObject(checkTask.getCheckConfig(), CheckRule.class);
        // 签到规则
//        CheckRule.Config in = checkRule.getIn();
        boolean res = true;

//        if (in != null) {
//            // 指定时间
//            if (in.isValidateTime()) {
//                long seconds = ChronoUnit.SECONDS.between(inTime, LocalDateTime.now().withHour(0).withMinute(0).withSecond(0));
//                res = in.getTimeRange().stream().anyMatch(time -> seconds >= time[0] && seconds < time[1]);
//            }
//            // 指定地点
//        }

        return res;
    }

    private void validateCheckOut(CheckTask checkTask, LocalDateTime outTime) {

    }
}
