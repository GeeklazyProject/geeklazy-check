package com.geeklazy.check.rule.controller.request;

import com.geeklazy.check.common.CheckArrangeConfig;
import com.geeklazy.check.common.CheckTargetConfig;
import lombok.Data;

import java.time.LocalDate;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/27 19:36
 * @Description
 */
@Data
public class CreateRuleRequest {
    private String service;
    private String name;
    private Integer creatorId;
    private String icon;
    private LocalDate startDate;
    private LocalDate endDate;

    private CheckTargetConfig targetConfig;
    private CheckArrangeConfig arrangeConfig;
}
