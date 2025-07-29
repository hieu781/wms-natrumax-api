package com.natrumax.dto.request;

import java.time.LocalDateTime;

public class UpdateOrderDetailRequest {
    private Integer quantity;
    private Boolean hasPromotion;
    private LocalDateTime modifyDate;

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Boolean getHasPromotion() {
        return hasPromotion;
    }

    public void setHasPromotion(Boolean hasPromotion) {
        this.hasPromotion = hasPromotion;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }
}
