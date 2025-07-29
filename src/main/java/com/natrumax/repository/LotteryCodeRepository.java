package com.natrumax.repository;

import com.natrumax.models.LotteryCode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LotteryCodeRepository extends JpaRepository<LotteryCode, Integer> {

    @Query("SELECT l FROM LotteryCode l WHERE l.user.id = :userId")
    List<LotteryCode> findByUserId(@Param("userId") Long userId);

    boolean existsByCode(String code);
}
