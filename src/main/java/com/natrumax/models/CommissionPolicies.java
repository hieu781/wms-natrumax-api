package com.natrumax.models;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "Commissions_policies")
public class CommissionPolicies {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commissions_policies_id")
    private Long commissionPoliciesId;

    @Column(name = "percentage", nullable = false)
    private double percentage;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "modify_date")
    private LocalDate modifyDate;

    @ManyToOne
    @JoinColumn(name = "commission_id", nullable = false)
    private Commission commission;

    @ManyToOne
    @JoinColumn(name = "category_id", nullable = false)
    private Category category;
    // Getters and Setters


    public Long getCommissionPoliciesId() {
        return commissionPoliciesId;
    }

    public void setCommissionPoliciesId(Long commissionPoliciesId) {
        this.commissionPoliciesId = commissionPoliciesId;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public LocalDate getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDate createDate) {
        this.createDate = createDate;
    }

    public LocalDate getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(LocalDate modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

}
