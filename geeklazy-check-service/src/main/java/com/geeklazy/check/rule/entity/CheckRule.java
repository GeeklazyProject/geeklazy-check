package com.geeklazy.check.rule.entity;

import com.geeklazy.check.common.CheckArrangeConfig;
import com.geeklazy.check.common.CheckTargetConfig;
import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/20 11:43
 * @Description
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "check_rule")
@TypeDef(name = "json", typeClass = JsonStringType.class)
public class CheckRule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String service;
    private Integer creatorId;
    private String name;
    private String icon;
    private LocalDate startDate;
    private LocalDate endDate;

    // 打卡类型

//    @Convert(converter = TargetConfigConverter.class)
    // 目标设定
    @Type(type = "json")
    private CheckTargetConfig targetConfig;

    // 安排设定
    @Type(type = "json")
    private CheckArrangeConfig arrangeConfig;

//    private Config in;
//    private Config out;

    @Data
    @Builder
    public static class Config {
        // 校验时间
        private boolean validateTime;
        // 打卡范围
        private List<Integer[]> timeRange;
        // 校验定位
        private boolean validateLocation;
        // 定位名称
        private String locationName;
        // 定位坐标
        private double[] location;
        // 定位范围
        private int distance;
    }
}
