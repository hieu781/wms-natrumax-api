package com.natrumax.models.MockAPI.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MisaInventoryOutRequest {
    @JsonProperty("Voucher_date")
    private String voucherDate;
    @JsonProperty("Posted_date")
    private String postedDate;
    @JsonProperty("inventory_out_items")
    private List<InventoryOutItem> items;

    // getters & setters...

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public String getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(String postedDate) {
        this.postedDate = postedDate;
    }

    public List<InventoryOutItem> getItems() {
        return items;
    }

    public void setItems(List<InventoryOutItem> items) {
        this.items = items;
    }

    public static class InventoryOutItem {
        @JsonProperty("Code")
        private String code;
        @JsonProperty("Quantity")
        private String quantity;
        // getters & setters...

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }
    }
}
