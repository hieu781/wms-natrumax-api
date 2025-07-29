package com.natrumax.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.natrumax.models.MockAPI.MisaGood;
import com.natrumax.models.MockAPI.MisaSaleItem;

import java.time.LocalDateTime;
import java.util.List;

public class SaleRequest {
    @JsonProperty("Voucher_date")
    private LocalDateTime Voucher_date;
    @JsonProperty("Posted_date")
    private LocalDateTime Posted_date;
    @JsonProperty("Customer")
    private String Customer;
    @JsonProperty("sale_items")
    private List<MisaSaleItem> saleItems;


    public LocalDateTime getPosted_date() {
        return Posted_date;
    }

    public void setPosted_date(LocalDateTime posted_date) {
        Posted_date = posted_date;
    }

    public LocalDateTime getVoucher_date() {
        return Voucher_date;
    }

    public void setVoucher_date(LocalDateTime voucher_date) {
        Voucher_date = voucher_date;
    }

    public String getCustomer() {
        return Customer;
    }

    public void setCustomer(String customer) {
        Customer = customer;
    }

    public List<MisaSaleItem> getSaleItems() {
        return saleItems;
    }

    public void setSaleItems(List<MisaSaleItem> saleItems) {
        this.saleItems = saleItems;
    }
}
