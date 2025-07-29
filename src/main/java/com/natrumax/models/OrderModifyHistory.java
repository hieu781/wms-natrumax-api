package com.natrumax.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "order_modify_history")
public class OrderModifyHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_modify_history_id")
    private int orderModifyHistoryId;

    private String title;
    private String actor;
    @Column(name = "create_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime createDate;

    @Column(name = "modify_date")
    @Temporal(TemporalType.TIMESTAMP)
    private LocalDateTime modifyDate;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    @JsonIgnore
    private Order order;

    public OrderModifyHistory() {
    }


    public OrderModifyHistory(int orderModifyHistoryId, String title, LocalDateTime createDate, LocalDateTime modifyDate, Order order) {
        this.orderModifyHistoryId = orderModifyHistoryId;
        this.title = title;
        this.actor = actor;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
        this.order = order;
    }

    public int getOrderModifyHistoryId() {
        return orderModifyHistoryId;
    }

    public void setOrderModifyHistoryId(int orderModifyHistoryId) {
        this.orderModifyHistoryId = orderModifyHistoryId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public String getActor() {
        return actor;
    }

    public void setActor(String actor) {
        this.actor = actor;
    }
}
