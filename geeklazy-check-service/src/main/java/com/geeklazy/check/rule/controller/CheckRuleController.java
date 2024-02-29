package com.geeklazy.check.rule.controller;

import com.geeklazy.check.rule.controller.request.CreateRuleRequest;
import com.geeklazy.check.rule.controller.request.SubscribeRuleRequest;
import com.geeklazy.check.rule.controller.response.CheckRule4TaskResponse;
import com.geeklazy.check.rule.entity.CheckRule;
import com.geeklazy.check.rule.service.CheckRuleService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/24 16:28
 * @Description
 */
@RestController
@RequestMapping("/web/check/rule")
public class CheckRuleController {
    @Autowired
    private CheckRuleService checkRuleService;

    @PostMapping
    public Integer create(@RequestBody CreateRuleRequest createRuleRequest) {
        CheckRule checkRule = new CheckRule();
        BeanUtils.copyProperties(createRuleRequest, checkRule);
        return checkRuleService.create(createRuleRequest.getService(), createRuleRequest.getCreatorId(), checkRule);
    }

    @PostMapping("/subscribe")
    public void subscribe(@RequestBody SubscribeRuleRequest subscribeRuleRequest) {
        checkRuleService.subscribe(subscribeRuleRequest.getRuleId(), subscribeRuleRequest.getRecorderId());
    }

    @GetMapping("/task")
    public List<CheckRule4TaskResponse> getRule4Check(String service, Integer recorderId, LocalDate date) {
        List<CheckRule4TaskResponse> resp = checkRuleService.getSubscribeRuleWithRecord(service, recorderId, date);
        return resp;
    }
}
