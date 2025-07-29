package com.natrumax.services.interfaces;

import com.natrumax.dto.request.LoginRequest;
<<<<<<< HEAD
import com.natrumax.dto.response.ReferrerResponse;
import com.natrumax.models.User;

import java.util.List;

=======
import com.natrumax.models.User;

>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
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
<<<<<<< HEAD

    List<ReferrerResponse> getReferrerList();
=======
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
}
