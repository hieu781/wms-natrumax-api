package com.natrumax.controllers;

<<<<<<< HEAD
import com.natrumax.dto.request.LoginRequest;
import com.natrumax.dto.request.OtpRequest;
import com.natrumax.dto.request.OtpVerificationRequest;
import com.natrumax.dto.request.ResetPasswordRequest;
import com.natrumax.dto.response.LoginResponse;
import com.natrumax.dto.response.MessageResponse;
import com.natrumax.models.User;
import com.natrumax.repository.RoleRepository;
import com.natrumax.repository.UserRepository;
import com.natrumax.security.jwt.JwtUtils;
import com.natrumax.services.UserDetailsImpl;
import com.natrumax.services.UserDetailsServiceImpl;
import com.natrumax.services.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @PostMapping("/login/request-otp")
    public ResponseEntity<?> loginRequestOtp(@Valid @RequestBody LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(loginRequest.getPhoneNumber());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        User user = optionalUser.get();

        // Load user details using UserDetailsService
        UserDetailsImpl userDetails = (UserDetailsImpl) ((UserDetailsServiceImpl) userDetailsService).loadUserByPhoneNumber(loginRequest.getPhoneNumber());

        if (!userDetails.isStatus()) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN) // 403 Forbidden
                    .body(new MessageResponse("You are blocked from signing in."));
        }

        // Kiểm tra mật khẩu trước khi gửi OTP
        if (!encoder.matches(loginRequest.getPassword(), user.getPassword())) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid password.");
        }

        boolean otpSent = userService.loginUser(loginRequest);
        if (!otpSent) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to send OTP. Please try again.");
        }

        return ResponseEntity.ok("OTP sent successfully.");
    }

    @PostMapping("/login/verify-otp")
    public ResponseEntity<?> loginVerifyOtp(@RequestBody OtpRequest otpRequest) {
        boolean isValid = userService.verifyUser(otpRequest.getPhoneNumber(), otpRequest.getOtp());

        if (!isValid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired OTP.");
        }

        // Load user details using UserDetailsService
        UserDetailsImpl userDetails = (UserDetailsImpl) ((UserDetailsServiceImpl) userDetailsService).loadUserByPhoneNumber(otpRequest.getPhoneNumber());

        if (!userDetails.isStatus()) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN) // 403 Forbidden
                    .body(new MessageResponse("You are blocked from signing in."));
        }


        // Tạo JWT token cho người dùng sau khi xác thực thành công.
        String jwtToken = jwtUtils.generateJwtToken(userDetails);

        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        // Create response with full user details
        LoginResponse loginResponse = new LoginResponse(
                jwtToken,
                userDetails.getId(),
                userDetails.getAccountName(),
                userDetails.getPhoneNumber(),
                userDetails.getEmail(),
                userDetails.getAddress(),
                userDetails.getDetail(),
                userDetails.getCreateDate(),
                userDetails.getModifyDate(),
                userDetails.isStatus(),
                roles
        );

        return ResponseEntity.ok()
                .body(loginResponse);
    }

    @PostMapping("/resend-otp")
    public ResponseEntity<?> resendOtp(@RequestBody OtpRequest otpRequest) {
        boolean otpResent = userService.resendOtp(otpRequest.getPhoneNumber());

        // Load user details using UserDetailsService
        UserDetailsImpl userDetails = (UserDetailsImpl) ((UserDetailsServiceImpl) userDetailsService).loadUserByPhoneNumber(otpRequest.getPhoneNumber());

        if (!userDetails.isStatus()) {
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN) // 403 Forbidden
                    .body(new MessageResponse("You are blocked from signing in."));
        }

        if (!otpResent) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to resend OTP. Please try again.");
        }

        return ResponseEntity.ok("OTP resent successfully.");
    }

    @PostMapping("/forgot-password/request-otp")
    public ResponseEntity<?> forgotPasswordRequestOtp(@RequestBody OtpRequest otpRequest) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(otpRequest.getPhoneNumber());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        boolean otpSent = userService.resendOtp(otpRequest.getPhoneNumber());

        if (!otpSent) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Failed to send OTP. Please try again.");
        }

        return ResponseEntity.ok("OTP sent successfully.");
    }

    @PostMapping("/forgot-password/verify-otp")
    public ResponseEntity<?> forgotPasswordVerifyOtp(@RequestBody OtpVerificationRequest otpVerificationRequest) {
        boolean isValidOtp = userService.verifyUser(otpVerificationRequest.getPhoneNumber(), otpVerificationRequest.getOtp());

        if (!isValidOtp) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired OTP.");
        }

        return ResponseEntity.ok("OTP verified. You may now reset your password.");
    }

    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody ResetPasswordRequest resetRequest) {
        if (!resetRequest.getNewPassword().equals(resetRequest.getConfirmPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Passwords do not match.");
        }

        Optional<User> optionalUser = userRepository.findByPhoneNumber(resetRequest.getPhoneNumber());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found.");
        }

        User user = optionalUser.get();
        user.setPassword(encoder.encode(resetRequest.getNewPassword())); // Mã hóa mật khẩu mới
        userRepository.save(user); // Lưu mật khẩu mới vào DB

        return ResponseEntity.ok("Password reset successfully.");
    }

//    @PostMapping("/signout")
//    public ResponseEntity<?> logoutUser() {
//        ResponseCookie cookie = jwtUtils.getCleanJwtCookie();
//        return ResponseEntity.ok().header(HttpHeaders.SET_COOKIE, cookie.toString())
//                .body(new MessageResponse("You've been signed out!"));
//    }

//    @GetMapping("/token-expiry")
//    public ResponseEntity<?> getTokenExpiry(HttpServletRequest request) {
//        String jwt = jwtUtils.getJwtFromCookies(request);
//
//        if (jwt == null || jwt.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("No JWT token found in cookies.");
//        }
//
//        try {
//            Date expirationDate = jwtUtils.getExpirationDateFromJwtToken(jwt);
//            long remainingTime = (expirationDate.getTime() - System.currentTimeMillis()) / 1000; // Convert to seconds
//
//            if (remainingTime <= 0) {
//                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("JWT token has expired.");
//            }
//
//            return ResponseEntity.ok(Collections.singletonMap("remainingTimeInSeconds", remainingTime));
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid JWT token.");
//        }
//    }
=======
public class AuthController {
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
}
