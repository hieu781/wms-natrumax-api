package com.natrumax.repository;

<<<<<<< HEAD
import com.natrumax.models.Enum.ERole;
import com.natrumax.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import com.natrumax.models.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
=======
import org.springframework.data.jpa.repository.JpaRepository;

import com.natrumax.models.User;

>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByPhoneNumber(String phoneNumber);
<<<<<<< HEAD
    Boolean existsByPhoneNumber(String phoneNumber);
    boolean existsByAccountName(String accountName);
    Optional<User> findByAccountName(String accountName);

    List<User> findByRole_Name(ERole roleName);
    @Query("SELECT DISTINCT c.referrer FROM Commission c")
    List<User> findDistinctReferrers();
=======

    Boolean existsByPhoneNumber(String phoneNumber);
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
}
