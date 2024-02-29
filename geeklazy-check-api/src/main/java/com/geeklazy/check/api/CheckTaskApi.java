package com.geeklazy.check.api;

import com.geeklazy.check.api.request.CreateTaskRequest;

public interface CheckTaskApi {
    void create(CreateTaskRequest request);
}
