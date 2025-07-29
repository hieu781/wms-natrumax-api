package com.natrumax.models.MockAPI;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.ZonedDateTime;
import java.util.List;

public class SaleData {
    @JsonProperty("_id")
    private String id;

    @JsonProperty("Voucher_no")
    private String voucherNo;

    @JsonProperty("Voucher_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private ZonedDateTime voucherDate;

    @JsonProperty("Posted_date")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss.SSSX")
    private ZonedDateTime postedDate;

    @JsonProperty("Customer")
    private String customerId;

    @JsonProperty("warehouse_id")
    private String warehouseId;

    @JsonProperty("status")
    private boolean status;

    @JsonProperty("sale_items")
    private List<String> saleItemIds;

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

    public ZonedDateTime getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(ZonedDateTime voucherDate) {
        this.voucherDate = voucherDate;
    }

    public ZonedDateTime getPostedDate() {
        return postedDate;
    }

    public void setPostedDate(ZonedDateTime postedDate) {
        this.postedDate = postedDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public List<String> getSaleItemIds() {
        return saleItemIds;
    }

    public void setSaleItemIds(List<String> saleItemIds) {
        this.saleItemIds = saleItemIds;
    }
}
