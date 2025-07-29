package com.natrumax.models.MockAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class KiotVietProduct {
    @JsonProperty("_id")
    private String _id;

    @JsonProperty("code")
    private String code;

    @JsonProperty("name")
    private String name;

    @JsonProperty("categoryId")
    private String categoryId;

    @JsonProperty("categoryName")
    private String categoryName;

    @JsonProperty("allowsSale")
    private boolean allowsSale;

    @JsonProperty("hasVariants")
    private boolean hasVariants;

    @JsonProperty("basePrice")
    private int basePrice;

    @JsonProperty("conversionValue")
    private int conversionValue;

    @JsonProperty("description")
    private String description;

    @JsonProperty("isActive")
    private boolean isActive;

    @JsonProperty("isRewardPoint")
    private boolean isRewardPoint;

    @JsonProperty("isLotSerialControl")
    private boolean isLotSerialControl;

    @JsonProperty("isBatchExpireControl")
    private boolean isBatchExpireControl;

    @JsonProperty("inventories")
    private List<Inventory> inventories;


    @JsonProperty("images")
    private Object images;

    @JsonProperty("__v")
    private int __v;

    // Getters and setters

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public boolean isAllowsSale() {
        return allowsSale;
    }

    public void setAllowsSale(boolean allowsSale) {
        this.allowsSale = allowsSale;
    }

    public boolean isHasVariants() {
        return hasVariants;
    }

    public void setHasVariants(boolean hasVariants) {
        this.hasVariants = hasVariants;
    }

    public int getBasePrice() {
        return basePrice;
    }

    public void setBasePrice(int basePrice) {
        this.basePrice = basePrice;
    }

    public int getConversionValue() {
        return conversionValue;
    }

    public void setConversionValue(int conversionValue) {
        this.conversionValue = conversionValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public boolean isRewardPoint() {
        return isRewardPoint;
    }

    public void setRewardPoint(boolean rewardPoint) {
        isRewardPoint = rewardPoint;
    }

    public boolean isLotSerialControl() {
        return isLotSerialControl;
    }

    public void setLotSerialControl(boolean lotSerialControl) {
        isLotSerialControl = lotSerialControl;
    }

    public boolean isBatchExpireControl() {
        return isBatchExpireControl;
    }

    public void setBatchExpireControl(boolean batchExpireControl) {
        isBatchExpireControl = batchExpireControl;
    }

    public List<Inventory> getInventories() {
        return inventories;
    }

    public void setInventories(List<Inventory> inventories) {
        this.inventories = inventories;
    }

    public Object getImages() {
        return images;
    }

    public void setImages(Object images) {
        this.images = images;
    }

    public int get__v() {
        return __v;
    }

    public void set__v(int __v) {
        this.__v = __v;
    }
}

