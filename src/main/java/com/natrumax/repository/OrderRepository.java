package com.natrumax.repository;

import com.natrumax.models.Enum.EInvoiceStatus;
import com.natrumax.models.Enum.EOrderStatus;
import com.natrumax.models.Order;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

    @Query("SELECT o FROM Order o LEFT JOIN FETCH o.orderDetails WHERE o.orderId = :orderId")
    Optional<Order> findByIdWithDetails(@Param("orderId") Long orderId);

    List<Order> findByUserId(Long userId);

    List<Order> findByStatus(EOrderStatus status);

<<<<<<< HEAD
    List<Order> findAllByInvoices_Status(EInvoiceStatus status);
=======
    List<Order> findByInvoices_Status(EInvoiceStatus status);

>>>>>>> ae0d55371b2b4dbb113dd2f2d6fe0a26ecf08c4d
}
