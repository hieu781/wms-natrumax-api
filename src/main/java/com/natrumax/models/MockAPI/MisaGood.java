package com.natrumax.models.MockAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MisaGood {
    @JsonProperty("_id")
    private String _id;

    @JsonProperty("Name")
    private String Name;

    @JsonProperty("Code")
    private String Code;

    @JsonProperty("Group")
    private String Group;

    @JsonProperty("Price")
    private String price;

    @JsonProperty("Description")
    private String Description;


    @JsonProperty("Inventory_account")
    private String Inventory_account;

    @JsonProperty("Characteristic")
    private String Characteristic;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getCode() {
        return Code;
    }

    public void setCode(String code) {
        Code = code;
    }

    public String getGroup() {
        return Group;
    }

    public void setGroup(String group) {
        Group = group;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getInventory_account() {
        return Inventory_account;
    }

    public void setInventory_account(String inventory_account) {
        Inventory_account = inventory_account;
    }

    public String getCharacteristic() {
        return Characteristic;
    }

    public void setCharacteristic(String characteristic) {
        Characteristic = characteristic;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
