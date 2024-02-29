package com.geeklazy.check.task.controller;

import com.alibaba.fastjson2.JSON;
import com.geeklazy.check.api.CheckTaskApi;
import com.geeklazy.check.api.request.CreateTaskRequest;
import com.geeklazy.check.task.entity.CheckTask;
import com.geeklazy.check.task.repository.CheckTaskRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/19 20:46
 * @Description
 */
@Slf4j
@RestController
@RequestMapping("/check/task")
public class CheckTaskController implements CheckTaskApi {
    @Autowired
    private CheckTaskRepository checkTaskRepository;

    @PostMapping("")
    @Override
    public void create(@RequestBody CreateTaskRequest request) {
        Optional<CheckTask> exist = checkTaskRepository.findOneByServiceAndName(request.getService(), request.getName());
        if (exist.isPresent()) {
            System.out.println("exist");
        } else {
            CheckTask checkTask = new CheckTask();
            BeanUtils.copyProperties(request, checkTask);
            checkTask.setCheckConfig(JSON.toJSONString(request.getCheckConfig()));
            System.out.println(checkTask);
            checkTaskRepository.save(checkTask);
        }
    }
}
