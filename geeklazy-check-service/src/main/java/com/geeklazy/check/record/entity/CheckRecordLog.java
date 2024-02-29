package com.geeklazy.check.record.entity;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/23 09:48
 * @Description 打卡记录日志
 */
@Data
public class CheckRecordLog {
    private Integer taskId;
    private Integer recorderId;
    private String desc;
    private List<String> resource;
    private LocalDateTime createDt;
    private Integer count;
}
