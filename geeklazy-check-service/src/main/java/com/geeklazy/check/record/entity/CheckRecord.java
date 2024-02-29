package com.geeklazy.check.record.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/19 18:52
 * @Description
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "check_record")
public class CheckRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer ruleId;
    private Integer recorderId;
    private LocalDate date;
    private boolean running;
    private boolean recheck;
    private Integer duration;
    private Integer count;
    private LocalDateTime inDt;
    private LocalDateTime outDt;
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createDt;
}
