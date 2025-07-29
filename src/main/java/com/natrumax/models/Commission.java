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
@Table(name = "Commissions")
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
<<<<<<< HEAD
    @Column(name = "commission_id")
    private int commissionId;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "modify_date")
    private LocalDate modifyDate;

    @ManyToOne
    @JoinColumn(name = "referral_id", nullable = false)
    private User referral;

    @ManyToOne
    @JoinColumn(name = "referrer_id", nullable = false)
    private User referrer;

    @OneToMany(mappedBy = "commission", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CommissionPolicies> commissionPolicies;

    @OneToMany(mappedBy = "commission", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<CommissionHistories> commissionHistories;
    // Getters and Setters


    public int getCommissionId() {
        return commissionId;
    }

    public void setCommissionId(int commissionId) {
        this.commissionId = commissionId;
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

    public User getReferral() {
        return referral;
    }

    public void setReferral(User referral) {
        this.referral = referral;
    }

    public User getReferrer() {
        return referrer;
    }

    public void setReferrer(User referrer) {
        this.referrer = referrer;
    }

    public List<CommissionPolicies> getCommissionPolicies() {
        return commissionPolicies;
    }

    public void setCommissionPolicies(List<CommissionPolicies> commissionPolicies) {
        this.commissionPolicies = commissionPolicies;
=======
    private int id;

    @ManyToOne
    @JoinColumn(name = "distributor_id", nullable = false)
    private User distributor;

    @ManyToOne
    @JoinColumn(name = "referer_id", nullable = false)
    private User referer;

    @OneToMany(mappedBy = "commission", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CommissionProduct> commissionProducts;
    // Getters and Setters


    public Commission() {
    }

    public Commission(int id, User distributor, User referer) {
        this.id = id;
        this.distributor = distributor;
        this.referer = referer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getDistributor() {
        return distributor;
    }

    public void setDistributor(User distributor) {
        this.distributor = distributor;
    }

    public User getReferer() {
        return referer;
    }

    public void setReferer(User referer) {
        this.referer = referer;
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
    }
}
