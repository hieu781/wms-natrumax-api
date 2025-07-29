package com.natrumax.models.MockAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MisaInventoryOut {

    @JsonProperty("_id")
    private String id;
    @JsonProperty("Voucher_no")
    private String voucherNo;
    @JsonProperty("items")
    private List<InventoryOutItem> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getVoucherNo() {
        return voucherNo;
    }

    public void setVoucherNo(String voucherNo) {
        this.voucherNo = voucherNo;
    }

    public List<InventoryOutItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryOutItem> items) {
        this.items = items;
    }
}
