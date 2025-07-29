package com.natrumax.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "commission_histories_detail")
public class CommissionHistoriesDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commission_histories_detail_id")
    private Long commissionHistoriesId;

    @Column(name = "category_name", nullable = false, length = 255)
    private String categoryName;

    @Column(name = "percentage", nullable = false)
    private double percentage;

    @Column(name = "amount", nullable = false)
    private double amount;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "modify_date")
    private LocalDateTime modifyDate;

    @ManyToOne
    @JoinColumn(name = "commission_history_id", nullable = false)
    private CommissionHistories commissionHistory;

    @ManyToOne
    @JoinColumn(name = "commission_policy_id", nullable = false)
    private CommissionPolicies commissionPolicy;


    public Long getCommissionHistoriesId() {
        return commissionHistoriesId;
    }

    public void setCommissionHistoriesId(Long commissionHistoriesId) {
        this.commissionHistoriesId = commissionHistoriesId;
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

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public CommissionHistories getCommissionHistory() {
        return commissionHistory;
    }

    public void setCommissionHistory(CommissionHistories commissionHistory) {
        this.commissionHistory = commissionHistory;
    }

    public CommissionPolicies getCommissionPolicy() {
        return commissionPolicy;
    }

    public void setCommissionPolicy(CommissionPolicies commissionPolicy) {
        this.commissionPolicy = commissionPolicy;
    }

}

