package com.natrumax.repository;

import com.natrumax.models.Invoices;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderInvoiceRepository extends JpaRepository<Invoices, Integer> {
    Invoices findByOrder_OrderId(Long orderId);
}
