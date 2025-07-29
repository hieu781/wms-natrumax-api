package com.natrumax.models;

<<<<<<< HEAD
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
=======
import jakarta.persistence.*;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
import java.util.List;

@Entity
@Table(name = "Rewards")
public class Reward {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    @Column(name = "reward_id")
    private int rewardId;
=======
    private int id;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6

    @Column(name = "name", length = 255)
    private String name;

<<<<<<< HEAD
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

=======
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    @Column(name = "status", nullable = false)
    private boolean status;

    @OneToMany(mappedBy = "reward", cascade = CascadeType.ALL, orphanRemoval = true)
<<<<<<< HEAD
    @JsonIgnore
    private List<LotteryCode> lotterycodes;
=======
    private List<RedemptionCode> redemptionCodes;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6

    // Getters and Setters

    public Reward() {
    }

<<<<<<< HEAD
    public Reward(int rewardId, String name, boolean status) {
        this.rewardId = rewardId;
=======
    public Reward(int id, String name, boolean status) {
        this.id = id;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
        this.name = name;
        this.status = status;
    }

<<<<<<< HEAD
    public int getRewardId() {
        return rewardId;
    }

    public void setRewardId(int rewardId) {
        this.rewardId = rewardId;
=======
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
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
<<<<<<< HEAD

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
=======
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
}
