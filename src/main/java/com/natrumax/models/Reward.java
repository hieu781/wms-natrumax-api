package com.natrumax.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Rewards")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "status", nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "reward", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RedemptionCode> redemptionCodes;

    // Getters and Setters

    public Reward() {
    }

    public Reward(int id, String name, boolean status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
