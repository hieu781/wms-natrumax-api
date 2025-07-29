package com.natrumax.services;

import com.natrumax.dto.request.CreateDiscountRequest;
import com.natrumax.dto.request.GetDiscountByTotalAmountRequest;
import com.natrumax.dto.request.UpdateDiscountRequest;
import com.natrumax.models.Discount;
import com.natrumax.models.Enum.EOrderStatus;
import com.natrumax.models.LotteryCode;
import com.natrumax.repository.DiscountRepository;
import com.natrumax.services.interfaces.IDiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class DiscountService implements IDiscountService {
    @Autowired
    private DiscountRepository discountRepository;

    public List<Discount> getAllDiscounts() {
        return discountRepository.findAll();
    }

    public Optional<Discount> getDiscountById(int id) {
        return discountRepository.findById(id);
    }

    public Discount createDiscount(CreateDiscountRequest request) {
        Discount discount = new Discount();
        discount.setCreateDate(LocalDateTime.now());
        discount.setActiveDate(request.getActiveDate());
        discount.setExpiryDate(request.getExpiryDate());
        discount.setDescription(request.getDescription());
        discount.setDiscountPercent(request.getDiscountPercent());
        discount.setMinimumAmount(request.getMinimumAmount());
        discount.setStatus(true);

        return discountRepository.save(discount);
    }

    public Discount updateDiscount(int id, UpdateDiscountRequest request) {
        return discountRepository.findById(id).map(discount -> {
            discount.setModifyDate(LocalDateTime.now());
            discount.setActiveDate(request.getActiveDate());
            discount.setExpiryDate(request.getExpiryDate());
            discount.setDescription(request.getDescription());
            discount.setDiscountPercent(request.getDiscountPercent());
            discount.setMinimumAmount(request.getMinimumAmount());

            return discountRepository.save(discount);
        }).orElse(null);
    }

    @Override
    public Discount getDiscountByTotalAmount(GetDiscountByTotalAmountRequest request) {
        LocalDateTime now = LocalDateTime.now();
        double totalAmount = request.getTotalAmount();

        return discountRepository.findTopByMinimumAmountLessThanEqualAndActiveDateBeforeAndExpiryDateAfterAndStatusOrderByMinimumAmountDesc(
                totalAmount, now, now, true
        );
    }

    public Discount changeStatus(int id) {
        Discount discount = discountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lottery code not found"));
        discount.setStatus(!discount.isStatus());
        return discountRepository.save(discount);
    }
}
