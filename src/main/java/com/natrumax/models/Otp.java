package com.natrumax.models;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "otp")
public class Otp {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id", nullable = false, unique = true)
    private User user;

    @Column(name = "otp_verification", nullable = false)
    private int otpVerification;

    @Column(name = "otp_expiration_time", nullable = false)
    private LocalDateTime otpExpirationTime;

    @Column(name = "otp_attempts", nullable = false)
    private Integer otpAttempts = 0; // Số lần nhập sai OTP

    @Column(name = "last_otp_sent_time")
    private LocalDateTime lastOtpSentTime; // Thời gian gửi OTP lần cuối

    @Column(name = "otp_request_count", nullable = false)
    private Integer otpRequestCount = 0; // Số lần yêu cầu OTP trong ngày

    @Column(name = "otp_request_date", nullable = false)
    private LocalDate otpRequestDate = LocalDate.now(); // Ngày ghi nhận số lần yêu cầu OTP

    public Otp() {}

    public Otp(User user, int otpVerification, LocalDateTime otpExpirationTime) {
        this.user = user;
        this.otpVerification = otpVerification;
        this.otpExpirationTime = otpExpirationTime;
    }

    // Getters & Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getOtpVerification() {
        return otpVerification;
    }

    public void setOtpVerification(int otpVerification) {
        this.otpVerification = otpVerification;
    }

    public LocalDateTime getOtpExpirationTime() {
        return otpExpirationTime;
    }

    public void setOtpExpirationTime(LocalDateTime otpExpirationTime) {
        this.otpExpirationTime = otpExpirationTime;
    }

    public Integer getOtpAttempts() {
        return otpAttempts;
    }

    public void setOtpAttempts(Integer otpAttempts) {
        this.otpAttempts = otpAttempts;
    }

    public LocalDateTime getLastOtpSentTime() {
        return lastOtpSentTime;
    }

    public void setLastOtpSentTime(LocalDateTime lastOtpSentTime) {
        this.lastOtpSentTime = lastOtpSentTime;
    }

    public Integer getOtpRequestCount() {
        return otpRequestCount;
    }

    public void setOtpRequestCount(Integer otpRequestCount) {
        this.otpRequestCount = otpRequestCount;
    }

    public LocalDate getOtpRequestDate() {
        return otpRequestDate;
    }

    public void setOtpRequestDate(LocalDate otpRequestDate) {
        this.otpRequestDate = otpRequestDate;
    }
}
