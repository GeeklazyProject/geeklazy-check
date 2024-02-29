package com.geeklazy.check.task.service;

import com.geeklazy.check.task.entity.CheckTask;

public interface CheckTaskService {
    CheckTask getTaskById(Integer taskId);
}
