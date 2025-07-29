package com.natrumax.repository;

import com.natrumax.models.Promotion;
import com.natrumax.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PromotionRepository extends JpaRepository<Promotion, Long> {
    Promotion findByUser(User user);
    Promotion findByUserId(Long userId);
}
