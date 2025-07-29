package com.natrumax.services;

import com.natrumax.dto.request.PromotionRequest;
import com.natrumax.models.Promotion;
import com.natrumax.models.User;
import com.natrumax.repository.PromotionRepository;
import com.natrumax.repository.UserRepository;
import com.natrumax.services.interfaces.IPromotionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PromotionService implements IPromotionService {
    @Autowired
    private PromotionRepository promotionRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Promotion> getAllPromotions() {
        return promotionRepository.findAll();
    }

    public Promotion getPromotionByUser(User user) {
        return promotionRepository.findByUser(user);
    }

    public Promotion createPromotion(User user, PromotionRequest request) {
        if (promotionRepository.findByUser(user) != null) {
            throw new IllegalArgumentException("Cannot create promotion, user already has a promotion");
        }

        Promotion promotion = new Promotion();
        promotion.setUser(user);
        promotion.setSameProduct(false);
        promotion.setQuantityToGetPromotion(0);
        promotion.setBonusQuantity(0);

        return promotionRepository.save(promotion);
    }

    public Promotion checkPromotion(User user, String product, int quantityBought) {
        Promotion promotion = promotionRepository.findByUser(user);

        if (promotion != null && quantityBought >= promotion.getQuantityToGetPromotion()) {
            return promotion;
        }
        return null;
    }

    public int calculateBonus(Promotion promotion, int quantityBought) {
        if (promotion == null) return 0;

        int bonusUnits = (quantityBought / promotion.getQuantityToGetPromotion()) * promotion.getBonusQuantity();
        return bonusUnits;
    }

    public Promotion updatePromotion(Long id, PromotionRequest request) {
        Optional<Promotion> existingPromotion = promotionRepository.findById(id);
        if (existingPromotion.isEmpty()) {
            return null;
        }

        Promotion promotion = existingPromotion.get();
        promotion.setSameProduct(request.isSameProduct());
        promotion.setQuantityToGetPromotion(request.getQuantityToGetPromotion());

        User user = userRepository.findById(request.getUserId()).orElse(null);
        if (user == null) {
            return null;
        }

        promotion.setUser(user);
        return promotionRepository.save(promotion);
    }
}
