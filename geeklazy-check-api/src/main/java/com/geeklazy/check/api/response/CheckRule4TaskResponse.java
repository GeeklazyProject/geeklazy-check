package com.geeklazy.check.api.response;

import com.geeklazy.check.enums.CycleTypeEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

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

    private TargetConfigResponse targetConfig;

    private ArrangeConfigResponse arrangeConfig;

    private boolean running;
    private Integer lastIn;

    @Data
    public static class TargetConfigResponse {
        // 数量
        private Integer target;
        // 单位
        private String unit;
        // 周期类型
        private CycleTypeEnum cycleType;
        // 周期限制
        private Integer cycleLimit;
    }

    @Data
    public static class ArrangeConfigResponse {
        // 周期类型 固定每天、每周、每月、每年
        private CycleTypeEnum cycleType;
        // 周期列表
        private List<String> cycleOffsets;
        // 特殊执行
        private List<String> specialIncludes;
        // 特殊不执行
        private List<String> specialExcludes;
        // 工作日设定
        private Boolean weekdayInclude;
        // 周末设定
        private Boolean weekendInclude;
        // 法定调休设定
        private Boolean exchangeInclude;
        // 法定休假设定
        private Boolean holidayInclude;
    }

}
