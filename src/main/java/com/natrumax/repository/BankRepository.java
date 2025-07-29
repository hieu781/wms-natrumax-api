package com.natrumax.repository;

import com.natrumax.models.Banks;
import com.natrumax.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankRepository extends JpaRepository<Banks, Long> {
    Optional<Banks> findByUserId(Long userId);

    Banks findByUser(User user);
}
