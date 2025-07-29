package com.natrumax.repository;

import com.natrumax.models.Commission;
import com.natrumax.models.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CommissionRepository extends JpaRepository<Commission, Integer> {
    @Query("SELECT c FROM Commission c " +
            "JOIN FETCH c.referral r " +
            "JOIN FETCH c.referrer f " +
            "LEFT JOIN FETCH c.commissionPolicies p " +
            "LEFT JOIN FETCH p.category " +
            "WHERE c.referrer.id = :referrerId")
    List<Commission> getCommissionListByReferrerId(@Param("referrerId") Long referrerId);

    @Transactional
    @Modifying
    @Query("UPDATE CommissionPolicies cp SET cp.percentage = :percentage WHERE cp.commissionPoliciesId = :commissionPolicyId")
    void updateCommissionPolicyById(@Param("commissionPolicyId") Long id, @Param("percentage") Double percentage);

    Optional<Commission> findByReferrerAndReferral(User referrer, User referral);

    List<Commission> findAll();
}
