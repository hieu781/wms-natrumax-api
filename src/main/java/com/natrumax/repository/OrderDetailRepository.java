package com.natrumax.repository;

import com.natrumax.models.OrderDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface OrderDetailRepository extends JpaRepository<OrderDetail, Integer> {
    @Query("SELECT od FROM OrderDetail od " +
            "WHERE od.order.user.id = :userId " +
            "AND od.product.productId = :productId " +
            "AND MONTH(od.order.orderDate) = :month " +
            "AND YEAR(od.order.orderDate) = :year")
    List<OrderDetail> findByUserProductAndMonthAndYear(
            @Param("userId") Long userId,
            @Param("productId") Long productId,
            @Param("month") int month,
            @Param("year") int year
    );
}
