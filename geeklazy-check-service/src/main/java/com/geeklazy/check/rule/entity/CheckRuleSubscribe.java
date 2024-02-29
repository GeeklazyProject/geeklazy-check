package com.geeklazy.check.rule.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/26 10:56
 * @Description
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "check_rule_subscribe")
public class CheckRuleSubscribe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer ruleId;
    private Integer recorderId;
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDt;
}
