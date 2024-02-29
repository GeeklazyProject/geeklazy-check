package com.geeklazy.check.api.request;

import lombok.Data;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/19 21:04
 * @Description
 */
@Data
public class CreateTaskRequest {
    private String service;
    private String name;
    private Object checkConfig;
    private String creatorId;
}
