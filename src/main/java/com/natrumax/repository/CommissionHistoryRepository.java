package com.natrumax.repository;

import com.natrumax.models.CommissionHistories;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CommissionHistoryRepository extends JpaRepository<CommissionHistories, Long> {
    List<CommissionHistories> findByCommissionReferrerId(Long userId);

    @Query("""
    SELECT ch FROM CommissionHistories ch
    JOIN FETCH ch.commission c
    JOIN FETCH c.referral ref
    JOIN FETCH c.referrer referrer
    JOIN FETCH ch.commissionHistoriesDetails d
    JOIN FETCH d.commissionPolicy cp
    JOIN FETCH cp.category cat
    WHERE c.referrer.id = :referrerId AND ch.month = :month AND ch.year = :year
""")
    List<CommissionHistories> getCommissionListByReferrerIdAndMonthAndYear(
            @Param("referrerId") Long referrerId,
            @Param("month") int month,
            @Param("year") int year
    );

    Optional<CommissionHistories> findByCommission_CommissionIdAndMonthAndYear(int commissionId, int month, int year);
}
