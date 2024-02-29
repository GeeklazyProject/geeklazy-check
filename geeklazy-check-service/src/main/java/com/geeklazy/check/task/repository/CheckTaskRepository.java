package com.geeklazy.check.task.repository;

import com.geeklazy.check.task.entity.CheckTask;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckTaskRepository extends JpaRepository<CheckTask, Integer> {
    Optional<CheckTask> findOneByServiceAndName(String service, String name);
}
