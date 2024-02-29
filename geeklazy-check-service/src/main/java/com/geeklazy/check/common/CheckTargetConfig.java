package com.geeklazy.check.common;

import com.geeklazy.check.common.enums.DirectionEnum;
import com.geeklazy.check.common.enums.MeasureTypeEnum;
import com.geeklazy.check.enums.CycleTypeEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/27 19:38
 * @Description
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CheckTargetConfig {
    // 数量
    private Integer target;
    // 打卡单位
    private String unit;
    // 计量方式
    private MeasureTypeEnum measureType;
    // 方向
    private DirectionEnum direction;
    // 周期类型
    private CycleTypeEnum cycleType;
    // 周期限制 TODO 抛弃每X周期完成N次的想法吧
    private Integer cycleLimit;
}
