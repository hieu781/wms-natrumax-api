package com.natrumax.models.MockAPI;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class MisaBank {
    @JsonProperty("_id")
    private String _id;
    @JsonProperty("Number")
    private String Number;
    @JsonProperty("Bank_name")
    private String Bank_name;
    @JsonProperty("Branch")
    private String Branch;
    @JsonProperty("Holder")
    private String Holder;
    @JsonProperty("Status")
    private boolean Status;

    public MisaBank(String _id, String number, String bank_name, String branch, String holder, boolean status) {
        this._id = _id;
        Number = number;
        Bank_name = bank_name;
        Branch = branch;
        Holder = holder;
        Status = status;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getBank_name() {
        return Bank_name;
    }

    public void setBank_name(String bank_name) {
        Bank_name = bank_name;
    }

    public String getBranch() {
        return Branch;
    }

    public void setBranch(String branch) {
        Branch = branch;
    }

    public String getHolder() {
        return Holder;
    }

    public void setHolder(String holder) {
        Holder = holder;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
