package com.natrumax.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "Rewards")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reward_id")
    private int rewardId;

    @Column(name = "name", length = 255)
    private String name;

    @Column(name = "image", length = 500)
    private String image;

    @JsonIgnore
    private String cloudinaryImageId;

    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "modify_date")
    private LocalDate modifyDate;

    @Column(name = "status", nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "reward", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<LotteryCode> lotterycodes;

    // Getters and Setters

    public Reward() {
    }

    public Reward(int rewardId, String name, boolean status) {
        this.rewardId = rewardId;
        this.name = name;
        this.status = status;
    }

    public int getRewardId() {
        return rewardId;
    }

    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getCloudinaryImageId() {
        return cloudinaryImageId;
    }

    public void setCloudinaryImageId(String cloudinaryImageId) {
        this.cloudinaryImageId = cloudinaryImageId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public List<LotteryCode> getLotterycodes() {
        return lotterycodes;
    }

    public void setLotterycodes(List<LotteryCode> lotterycodes) {
        this.lotterycodes = lotterycodes;
    }

    public List<LotteryCode> getRedemptionCodes() {
        return lotterycodes;
    }

    public void setRedemptionCodes(List<LotteryCode> lotterycodes) {
        this.lotterycodes = lotterycodes;
    }
}
