package com.geeklazy.check.rule.repository;

import com.geeklazy.check.rule.entity.CheckRuleSubscribe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CheckRuleSubscribeRepository extends JpaRepository<CheckRuleSubscribe, Integer> {

    Optional<CheckRuleSubscribe> findByRuleIdAndRecorderId(Integer ruleId, Integer recorderId);
}
