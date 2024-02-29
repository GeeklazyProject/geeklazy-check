package com.geeklazy.check.rule.controller.response;

import com.geeklazy.check.common.CheckArrangeConfig;
import com.geeklazy.check.common.CheckTargetConfig;
import com.geeklazy.check.enums.CycleTypeEnum;
import lombok.Data;

import java.util.List;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/24 16:31
 * @Description
 */
@Data
public class CheckRule4TaskResponse {
    private Integer ruleId;
    private String name;
    private String icon;

    private Integer count;
    private Integer duration;

    private CheckTargetConfig targetConfig;
    private CheckArrangeConfig arrangeConfig;

    private boolean running;
    private Integer lastIn;

}
