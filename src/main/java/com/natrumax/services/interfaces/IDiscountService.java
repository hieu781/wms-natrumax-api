package com.natrumax.services.interfaces;

import com.natrumax.dto.request.CreateDiscountRequest;
import com.natrumax.dto.request.GetDiscountByTotalAmountRequest;
import com.natrumax.dto.request.UpdateDiscountRequest;
import com.natrumax.models.Discount;

import java.util.List;
import java.util.Optional;

public interface IDiscountService {
    List<Discount> getAllDiscounts();

    Optional<Discount> getDiscountById(int id);

    Discount createDiscount(CreateDiscountRequest request);

    Discount updateDiscount(int id, UpdateDiscountRequest request);
    Discount getDiscountByTotalAmount(GetDiscountByTotalAmountRequest request);
}
