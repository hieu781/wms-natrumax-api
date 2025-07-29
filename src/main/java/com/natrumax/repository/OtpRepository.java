package com.natrumax.repository;

import com.natrumax.models.Otp;
import com.natrumax.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OtpRepository extends JpaRepository<Otp, Long> {
    Optional<Otp> findByUser(User user);
}
