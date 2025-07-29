package com.natrumax.models.MockAPI.response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class MisaInventoryInResponse {
    private String message;

    @JsonProperty("inventory_in")
    private InventoryIn inventoryIn;

    @JsonProperty("inventory_in_items")
    private List<InventoryInItem> inventoryInItems;

    // Getters and Setters

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public InventoryIn getInventoryIn() {
        return inventoryIn;
    }

    public void setInventoryIn(InventoryIn inventoryIn) {
        this.inventoryIn = inventoryIn;
    }

    public List<InventoryInItem> getInventoryInItems() {
        return inventoryInItems;
    }

    public void setInventoryInItems(List<InventoryInItem> inventoryInItems) {
        this.inventoryInItems = inventoryInItems;
    }

    // Nested static classes

    public static class InventoryIn {

        @JsonProperty("Posted_date")
        private String postedDate;

        @JsonProperty("Voucher_date")
        private String voucherDate;

        @JsonProperty("warehouse_id")
        private String warehouseId;

        @JsonProperty("_id")
        private String id;

        @JsonProperty("Voucher_no")
        private String voucherNo;

        @JsonProperty("__v")
        private int version;

        // Getters and Setters

        public String getPostedDate() {
            return postedDate;
        }

        public void setPostedDate(String postedDate) {
            this.postedDate = postedDate;
        }

        public String getVoucherDate() {
            return voucherDate;
        }

        public void setVoucherDate(String voucherDate) {
            this.voucherDate = voucherDate;
        }

        public String getWarehouseId() {
            return warehouseId;
        }

        public void setWarehouseId(String warehouseId) {
            this.warehouseId = warehouseId;
        }

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

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }

    public static class InventoryInItem {

        @JsonProperty("Good_id")
        private String goodId;

        @JsonProperty("Inventory_in")
        private String inventoryIn;

        @JsonProperty("Quantity")
        private String quantity;

        @JsonProperty("_id")
        private String id;

        @JsonProperty("__v")
        private int version;

        // Getters and Setters

        public String getGoodId() {
            return goodId;
        }

        public void setGoodId(String goodId) {
            this.goodId = goodId;
        }

        public String getInventoryIn() {
            return inventoryIn;
        }

        public void setInventoryIn(String inventoryIn) {
            this.inventoryIn = inventoryIn;
        }

        public String getQuantity() {
            return quantity;
        }

        public void setQuantity(String quantity) {
            this.quantity = quantity;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }
    }

}
