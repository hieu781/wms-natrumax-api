package com.natrumax.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "commission_histories")
public class CommissionHistories {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commission_histories_id")
    private Long commissionHistoriesId;

    @Column(name = "total_amount", nullable = false)
    private double totalAmount;

    @Column(name = "month", nullable = false)
    private int month;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "modify_date")
    private LocalDate modifyDate;

    @OneToMany(mappedBy = "commissionHistory", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CommissionHistoriesDetail> commissionHistoriesDetails;

    @ManyToOne
    @JoinColumn(name = "commission_id", nullable = false)
    private Commission commission;

    // Getters and Setters


    public CommissionHistories() {
    }

    public Long getCommissionHistoriesId() {
        return commissionHistoriesId;
    }

    public void setCommissionHistoriesId(Long commissionHistoriesId) {
        this.commissionHistoriesId = commissionHistoriesId;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
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

    public List<CommissionHistoriesDetail> getCommissionHistoriesDetails() {
        return commissionHistoriesDetails;
    }

    public void setCommissionHistoriesDetails(List<CommissionHistoriesDetail> commissionHistoriesDetails) {
        this.commissionHistoriesDetails = commissionHistoriesDetails;
    }

    public Commission getCommission() {
        return commission;
    }

    public void setCommission(Commission commission) {
        this.commission = commission;
    }
}

