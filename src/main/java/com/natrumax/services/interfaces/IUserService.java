package com.natrumax.services.interfaces;

import com.natrumax.dto.request.LoginRequest;
import com.natrumax.dto.response.ReferrerResponse;
import com.natrumax.models.User;

import java.util.List;

public interface IUserService {
    Boolean existsByPhoneNumber(String phoneNumber);

    Boolean existsByEmail(String email);

    Boolean existsById(Long id);

    Boolean loginUser(LoginRequest loginRequest);

    Boolean createAdminUser();

    Boolean verifyUser(String phoneNumber, Integer otp);

    Boolean resendOtp(String phoneNumber);

    User findUserByPhoneNumber(String phoneNumber);

    User findUserById(Long id);

    List<ReferrerResponse> getReferrerList();
}
