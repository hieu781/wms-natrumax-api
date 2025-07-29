package com.natrumax.repository;

import com.natrumax.models.Transactions;
import com.natrumax.models.UserProduct;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserProductRepository extends JpaRepository<UserProduct, Long> {
    List<UserProduct> findByUserId(Long userId);
}
