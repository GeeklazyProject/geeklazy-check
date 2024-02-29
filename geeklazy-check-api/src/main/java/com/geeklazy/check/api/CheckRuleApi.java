package com.geeklazy.check.api;

import com.geeklazy.check.api.response.CheckRule4TaskResponse;

import java.time.LocalDate;
import java.util.List;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/24 16:29
 * @Description
 */
public interface CheckRuleApi {
    /**
     * 获取指定日期的打卡任务
     * @param date
     * @return
     */
    List<CheckRule4TaskResponse> getRule4Check(LocalDate date);
}
