package com.natrumax.repository;

import com.natrumax.models.Discount;
import com.natrumax.models.Enum.EOrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface DiscountRepository extends JpaRepository<Discount, Integer> {
    Discount findTopByMinimumAmountLessThanEqualAndActiveDateBeforeAndExpiryDateAfterAndStatusOrderByMinimumAmountDesc(
            double totalAmount, LocalDateTime now1, LocalDateTime now2, boolean status
    );
}
