package com.geeklazy.check.record.service;

import com.geeklazy.check.record.entity.CheckRecord;
import com.geeklazy.check.rule.entity.CheckRule;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/19 18:44
 * @Description
 */
public interface CheckRecordService {
    void checkIn(CheckRule checkRule, Integer ruleId, Integer recorderId);

    void checkOut(CheckRule checkRule, Integer ruleId, Integer recorderId);

    List<CheckRecord> getRecords(Integer ruleId, Integer recorderId, LocalDate startDate, LocalDate endDate);
}
