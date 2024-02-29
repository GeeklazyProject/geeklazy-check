package com.geeklazy.check.record.repository;

import com.geeklazy.check.record.entity.CheckRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface CheckRecordRepository extends JpaRepository<CheckRecord, Integer> {
    @Transactional
    @Modifying
    @Query(value = "UPDATE check_record SET out_dt = :outDt, duration = (str_to_date(:outTime, '%Y-%m-%d %H:%i:%s.%f') - in_time), is_running = FALSE WHERE recorder_id = :recorderId AND rule_id = :ruleId AND is_running = TRUE", nativeQuery = true)
    void checkOut(@Param("ruleId") int ruleId, @Param("recorderId") int recorderId, @Param("outDt") LocalDateTime outDt);

    Optional<CheckRecord> findFirstByRuleIdAndRecorderIdAndDateEqualsAndRunningTrueOrderByInDtDesc(Integer ruleId, Integer recorderId, LocalDate date);

    List<CheckRecord> findAllByRuleIdAndRecorderIdAndDateBetween(Integer ruleId, Integer recorderId, LocalDate startDate, LocalDate endDate);
}
