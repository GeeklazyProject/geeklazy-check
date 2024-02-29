package com.geeklazy.check.api.request;

import com.geeklazy.check.enums.CheckTypeEnum;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/19 18:26
 * @Description
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class CheckRequest extends BaseCheckRequest{
    private CheckTypeEnum checkType;
}
