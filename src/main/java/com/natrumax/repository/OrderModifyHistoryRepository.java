package com.natrumax.repository;

import com.natrumax.models.OrderModifyHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderModifyHistoryRepository extends JpaRepository<OrderModifyHistory, Integer> {
}
