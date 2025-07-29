package com.natrumax.models;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Commissions")
public class Commission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
    }
}
