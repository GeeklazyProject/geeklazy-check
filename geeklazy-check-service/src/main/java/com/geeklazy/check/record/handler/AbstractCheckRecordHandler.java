package com.geeklazy.check.record.handler;

import com.geeklazy.check.task.entity.CheckTask;

import java.time.LocalDateTime;

public abstract class AbstractCheckRecordHandler {
    abstract boolean validateCheckIn(CheckTask checkTask, LocalDateTime inTime);
}
