package com.natrumax.dto.response;

import java.util.List;

public class CommissionHistoriesResponse {
    private UserDTO referrer;
    private List<CommissionDTO> commissions;

    public CommissionHistoriesResponse() {
    }

    public CommissionHistoriesResponse(UserDTO referrer, List<CommissionDTO> commissions) {
        this.referrer = referrer;
        this.commissions = commissions;
    }

    public UserDTO getReferrer() {
        return referrer;
    }

    public void setReferrer(UserDTO referrer) {
        this.referrer = referrer;
    }

    public List<CommissionDTO> getCommissions() {
        return commissions;
    }

    public void setCommissions(List<CommissionDTO> commissions) {
        this.commissions = commissions;
    }

    public static class UserDTO {
        private Long userId;
        private String accountName;
        private String role;
        private String province;

        public UserDTO() {
        }

        public UserDTO(Long userId, String accountName, String role, String province) {
            this.userId = userId;
            this.accountName = accountName;
            this.role = role;
            this.province = province;
        }

        public Long getUserId() {
            return userId;
        }

        public void setUserId(Long userId) {
            this.userId = userId;
        }

        public String getAccountName() {
            return accountName;
        }

        public void setAccountName(String accountName) {
            this.accountName = accountName;
        }

        public String getRole() {
            return role;
        }

        public void setRole(String role) {
            this.role = role;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }
    }

    public static class CommissionDTO {
        private int commissionId;
        private UserDTO referral;
        private CommissionHistoryDTO commissionHistory;

        public CommissionDTO() {
        }

        public CommissionDTO(int commissionId, UserDTO referral, CommissionHistoryDTO commissionHistory) {
            this.commissionId = commissionId;
            this.referral = referral;
            this.commissionHistory = commissionHistory;
        }

        public int getCommissionId() {
            return commissionId;
        }

        public void setCommissionId(int commissionId) {
            this.commissionId = commissionId;
        }

        public UserDTO getReferral() {
            return referral;
        }

        public void setReferral(UserDTO referral) {
            this.referral = referral;
        }

        public CommissionHistoryDTO getCommissionHistory() {
            return commissionHistory;
        }

        public void setCommissionHistory(CommissionHistoryDTO commissionHistory) {
            this.commissionHistory = commissionHistory;
        }
    }

    public static class CommissionHistoryDTO {
        private Long commissionHistoryId;
        private int month;
        private int year;
        private double totalAmount;
        private List<CommissionHistoryDetailDTO> details;

        public CommissionHistoryDTO() {
        }

        public CommissionHistoryDTO(Long commissionHistoryId, int month, int year, double totalAmount, List<CommissionHistoryDetailDTO> details) {
            this.commissionHistoryId = commissionHistoryId;
            this.month = month;
            this.year = year;
            this.totalAmount = totalAmount;
            this.details = details;
        }

        public Long getCommissionHistoryId() {
            return commissionHistoryId;
        }

        public void setCommissionHistoryId(Long commissionHistoryId) {
            this.commissionHistoryId = commissionHistoryId;
        }

        public int getMonth() {
            return month;
        }

        public void setMonth(int month) {
            this.month = month;
        }

        public int getYear() {
            return year;
        }

        public void setYear(int year) {
            this.year = year;
        }

        public double getTotalAmount() {
            return totalAmount;
        }

        public void setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
        }

        public List<CommissionHistoryDetailDTO> getDetails() {
            return details;
        }

        public void setDetails(List<CommissionHistoryDetailDTO> details) {
            this.details = details;
        }
    }

    public static class CommissionHistoryDetailDTO {
        private Long commissionHistoryDetailId;
        private String categoryName;
        private double percentage;
        private double amount;

        public CommissionHistoryDetailDTO() {
        }

        public CommissionHistoryDetailDTO(Long commissionHistoryDetailId, String categoryName, double percentage, double amount) {
            this.commissionHistoryDetailId = commissionHistoryDetailId;
            this.categoryName = categoryName;
            this.percentage = percentage;
            this.amount = amount;
        }

        public Long getCommissionHistoryDetailId() {
            return commissionHistoryDetailId;
        }

        public void setCommissionHistoryDetailId(Long commissionHistoryDetailId) {
            this.commissionHistoryDetailId = commissionHistoryDetailId;
        }

        public String getCategoryName() {
            return categoryName;
        }

        public void setCategoryName(String categoryName) {
            this.categoryName = categoryName;
        }

        public double getPercentage() {
            return percentage;
        }

        public void setPercentage(double percentage) {
            this.percentage = percentage;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }
}
