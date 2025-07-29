package com.natrumax.models;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reports")
public class Report {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long reportId;

    @Column(name = "month", nullable = false)
    private int month;

    @Column(name = "year", nullable = false)
    private int year;

    @Column(name = "quantity_imported", nullable = false)
    private int quantityImported;

    @Column(name = "ending_inventory", nullable = false)
    private int endingInventory;

    @Column(name = "create_date", nullable = false)
    private LocalDateTime createDate;

    @Column(name = "modify_date")
    private LocalDateTime modifyDate;

    @ManyToOne
    @JoinColumn(name = "user_product_id", nullable = false)
    private UserProduct userProduct;

    public Long getReportId() {
        return reportId;
    }

    public void setReportId(Long reportId) {
        this.reportId = reportId;
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

    public int getQuantityImported() {
        return quantityImported;
    }

    public void setQuantityImported(int quantityImported) {
        this.quantityImported = quantityImported;
    }

    public int getEndingInventory() {
        return endingInventory;
    }

    public void setEndingInventory(int endingInventory) {
        this.endingInventory = endingInventory;
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

    public UserProduct getUserProduct() {
        return userProduct;
    }

    public void setUserProduct(UserProduct userProduct) {
        this.userProduct = userProduct;
    }


}

