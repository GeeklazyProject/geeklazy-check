package com.geeklazy.check.rule.repository;

import com.geeklazy.check.rule.entity.CheckRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * @Author geeklazy@163.com
 * @Date 2024/2/25 15:35
 * @Description
 */
@Repository
public interface CheckRuleRepository extends JpaRepository<CheckRule, Integer> {
    @Query(value = "SELECT cr.* FROM check_rule  cr INNER JOIN check_rule_subscribe crs ON cr.id = crs.rule_id WHERE cr.service = :service AND cr.start_date <= :date AND (cr.end_date <= :date OR cr.end_date IS NULL) AND crs.recorder_id = :recorderId AND DATE(crs.create_dt) <= :date", nativeQuery = true)
    List<CheckRule> findRunningSubscribeCheckRule(@Param("service") String service, @Param("recorderId") Integer recorderId, @Param("date") LocalDate date);

    Optional<CheckRule> findByServiceAndCreatorIdAndName(String service, Integer creatorId, String name);
}
