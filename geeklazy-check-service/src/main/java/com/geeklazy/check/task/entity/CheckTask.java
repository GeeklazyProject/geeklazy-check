package com.geeklazy.check.task.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/19 20:47
 * @Description
 */
@Entity
@Getter
@Setter
@ToString
@Table(name = "check_task")
public class CheckTask {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String service;
    private String name;
    private String checkConfig;
    private String creatorId;
    @Column(nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime createTime;
}
