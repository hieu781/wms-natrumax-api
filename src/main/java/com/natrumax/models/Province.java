package com.natrumax.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "provinces")
public class Province {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "province_id")
    private Long provinceId;

    @Column(name = "name", nullable = false, length = 255)
    private String name;

    @Column(name = "province_code", nullable = false, length = 50)
    private String provinceCode;

    @Column(name = "region", nullable = false, length = 50)
    private String region;

    @Column(name = "create_date", nullable = false)
    private LocalDate createDate;

    @Column(name = "modify_date")
    private LocalDate modifyDate;

    @OneToMany(mappedBy = "province", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<User> users;


    public Province() {
    }

    public Province(Long provinceId, String name, String provinceCode, LocalDate createDate, LocalDate modifyDate) {
        this.provinceId = provinceId;
        this.name = name;
        this.provinceCode = provinceCode;
        this.createDate = createDate;
        this.modifyDate = modifyDate;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
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

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }
}
