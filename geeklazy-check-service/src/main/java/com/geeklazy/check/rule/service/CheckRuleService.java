package com.geeklazy.check.rule.service;

import com.geeklazy.check.rule.controller.response.CheckRule4TaskResponse;
import com.geeklazy.check.rule.entity.CheckRule;
import com.geeklazy.check.task.entity.CheckTask;

import java.time.LocalDate;
import java.util.List;

public interface CheckRuleService {
    // 创建打卡规则
    Integer create(String service, Integer creatorId, CheckRule checkRule);

    // 订阅打卡规则
    void subscribe(Integer ruleId, Integer recorderId);

    List<CheckRule4TaskResponse> getSubscribeRuleWithRecord(String service, Integer recorderId, LocalDate date);

    CheckRule getRule(Integer ruleId);
}
