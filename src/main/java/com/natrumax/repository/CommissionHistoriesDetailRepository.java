package com.natrumax.repository;

import com.natrumax.models.CommissionHistoriesDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommissionHistoriesDetailRepository extends JpaRepository<CommissionHistoriesDetail, Long> {
}
