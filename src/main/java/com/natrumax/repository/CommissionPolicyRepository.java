package com.natrumax.repository;

import com.natrumax.models.CommissionHistories;
import com.natrumax.models.CommissionPolicies;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CommissionPolicyRepository extends JpaRepository<CommissionPolicies, Long> {
    @Query("SELECT c FROM CommissionPolicies c WHERE c.modifyDate IS NULL")
    CommissionPolicies findActivePolicy();

    Optional<CommissionPolicies> findByCommissionCommissionIdAndCategoryCategoryId(int commissionId, Long categoryId);
}
