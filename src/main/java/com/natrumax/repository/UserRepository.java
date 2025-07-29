package com.natrumax.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.natrumax.models.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);

    Boolean existsByPhoneNumber(String phoneNumber);
}
