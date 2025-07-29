package com.natrumax.services;

import com.natrumax.dto.request.LoginRequest;
import com.natrumax.models.Role;
import com.natrumax.models.User;
import com.natrumax.repository.RoleRepository;
import com.natrumax.repository.UserRepository;
import com.natrumax.services.interfaces.IUserService;
import com.natrumax.utils.SpeedSMSAPI;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Optional;
import java.util.Random;
import java.util.Set;

@Service
public class UserService implements IUserService {
    @Override
    public Boolean existsByEmail(String email) {
        return null;
    }

    @Override
    public Boolean existsById(Long id) {
        return null;
    }

    @Override
    public Boolean createAdminUser() {
        return null;
    }

    @Value("${wms-natrumax.app.smsAccessToken}")
    private String smsAccessToken;

    @Value("${wms-natrumax.app.senderId}")
    private String senderId;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Override
    public Boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Boolean loginUser(LoginRequest loginRequestp) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(loginRequestp.getPhonenumber());

        if (optionalUser.isEmpty()) {
            return false; // User does not exist
        }

        User user = optionalUser.get();

        // Generate OTP
        Random r = new Random();
        String randomNumber = String.format("%04d", r.nextInt(10000));
        user.setOtpVerification(Integer.parseInt(randomNumber));
        user.setOtpExpirationTime(LocalDateTime.now().plusMinutes(30)); // OTP valid for 30 minutes

        userRepository.save(user); // Save the updated OTP and expiration time

        // Send SMS to user phone number
        SpeedSMSAPI api = new SpeedSMSAPI(smsAccessToken);
        try {
            String result = api.sendSMS(
                    loginRequestp.getPhonenumber(),
                    "Chào bạn, mã xác thực của bạn là " + randomNumber,
                    5, senderId
            );
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }

    @Override
    public Boolean verifyUser(String phoneNumber, Integer otp) {
        Optional<User> u = userRepository.findByPhoneNumber(phoneNumber);
        if (u.isPresent()) {
            User user = u.get();
            if (otp == user.getOtpVerification()) {
                if (user.getOtpExpirationTime().isAfter(LocalDateTime.now())) {
                    user.setOtpVerification(0);
                    userRepository.save(user);
                    return true;
                }
                return false;
            }
            return false;
        }
        return false;
    }

    @Override
    public Boolean resendOtp(String phoneNumber) {
        User user = userRepository.findByPhoneNumber(phoneNumber).orElse(null);
        if (user != null) {
            Random r = new Random();
            String randomNumber = String.format("%04d", r.nextInt(10000));
            user.setOtpVerification(Integer.parseInt(randomNumber));
            user.setOtpExpirationTime(LocalDateTime.now().plusMinutes(30)); // 30 phút verification

            userRepository.save(user);

            // Gửi SMS cho user phone number
            SpeedSMSAPI api = new SpeedSMSAPI(smsAccessToken);
            try {
                String result = api.sendSMS(phoneNumber, "Chào bạn, mã xác thực của bạn là " + randomNumber, 5, senderId);
                System.out.println(result);
                if (result.contains("error")) {
                    return false;
                }
            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).orElse(null);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }
}
