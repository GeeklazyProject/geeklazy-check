package com.geeklazy.check.api.request;

import lombok.Data;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/19 23:39
 * @Description
 */
@Data
public class BaseCheckRequest {
    private Integer ruleId;
    private Integer recorderId;
}
