package com.geeklazy.check.common;

import com.geeklazy.check.common.enums.ContainEnum;
import com.geeklazy.check.enums.CycleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/27 19:42
 * @Description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckArrangeConfig {
    // 周期类型 固定每天、每周、每月、每年
    private CycleTypeEnum cycleType;
    // 周期列表
    private List<String> cycleOffsets;

    private SpecialConfig specialConfig;

    @Data
    @Builder
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SpecialConfig {
        // 特殊执行
        private List<String> specialIncludes;
        // 特殊不执行
        private List<String> specialExcludes;
        // 工作日设定
        private ContainEnum weekdayContain;
        // 周末设定
        private ContainEnum weekendContain;
        // 法定调休设定
        private ContainEnum exchangeContain;
        // 法定休假设定
        private ContainEnum holidayContain;
    }
}
