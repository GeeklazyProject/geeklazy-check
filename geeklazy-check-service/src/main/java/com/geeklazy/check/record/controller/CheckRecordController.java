package com.geeklazy.check.record.controller;

import com.geeklazy.check.api.CheckRecordApi;
import com.geeklazy.check.api.request.BaseCheckRequest;
import com.geeklazy.check.api.request.CheckRequest;
import com.geeklazy.check.record.entity.CheckRecord;
import com.geeklazy.check.record.repository.CheckRecordRepository;
import com.geeklazy.check.record.service.CheckRecordService;
import com.geeklazy.check.rule.entity.CheckRule;
import com.geeklazy.check.rule.service.CheckRuleService;
import com.geeklazy.check.task.entity.CheckTask;
import com.geeklazy.check.task.service.CheckTaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Optional;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/19 18:00
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/web/check/record")
public class CheckRecordController implements CheckRecordApi {

    @Autowired
    private CheckRecordService checkRecordService;
    @Autowired
    private CheckRuleService checkRuleService;

    @PostMapping("")
    @Override
    public void check(@RequestBody CheckRequest request) {
        CheckRule checkRule = checkRuleService.getRule(request.getRuleId());

        switch (request.getCheckType()) {
            case CHECK_IN -> checkRecordService.checkIn(checkRule, request.getRuleId(), request.getRecorderId());
            case CHECK_OUT -> checkRecordService.checkOut(checkRule, request.getRuleId(), request.getRecorderId());
        }
    }

    @GetMapping("lastRecord")
    public void lastRecord(BaseCheckRequest request) {

    }
}
