package com.natrumax.services.interfaces;

import com.natrumax.dto.request.PromotionRequest;
import com.natrumax.models.Promotion;
import com.natrumax.models.User;

public interface IPromotionService {
    Promotion createPromotion(User user, PromotionRequest request);
}
