package com.geeklazy.check.task.service.impl;

import com.geeklazy.check.task.entity.CheckTask;
import com.geeklazy.check.task.repository.CheckTaskRepository;
import com.geeklazy.check.task.service.CheckTaskService;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/20 13:50
 * @Description
 */
@Service
public class CheckTaskServiceImpl implements CheckTaskService {
    private final CheckTaskRepository checkTaskRepository;

    public CheckTaskServiceImpl(CheckTaskRepository checkTaskRepository) {
        this.checkTaskRepository = checkTaskRepository;
    }

    @Override
    public CheckTask getTaskById(Integer taskId) {
        Optional<CheckTask> persist = checkTaskRepository.findById(taskId);
        return persist.orElse(null);
    }
}
