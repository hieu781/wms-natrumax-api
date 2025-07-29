package com.natrumax.services.interfaces;

import com.natrumax.models.Enum.EInvoiceStatus;
import com.natrumax.models.Enum.EOrderStatus;

public interface IOrderInvoiceService {
    void updateInvoiceStatus(Long orderId, EInvoiceStatus status);
}
