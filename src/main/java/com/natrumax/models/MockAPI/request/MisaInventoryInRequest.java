package com.natrumax.models.MockAPI.request;
import com.natrumax.models.MockAPI.InventoryInItem;

import java.time.LocalDateTime;
import java.util.List;

public class MisaInventoryInRequest {
    private LocalDateTime Posted_date;
    private LocalDateTime Voucher_date;
    private List<InventoryInItem> inventory_in_items;

    public MisaInventoryInRequest() {
    }

    public MisaInventoryInRequest(LocalDateTime posted_date, LocalDateTime voucher_date, List<InventoryInItem> inventory_in_items) {
        Posted_date = posted_date;
        Voucher_date = voucher_date;
        this.inventory_in_items = inventory_in_items;
    }

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

    public List<InventoryInItem> getInventory_in_items() {
        return inventory_in_items;
    }

    public void setInventory_in_items(List<InventoryInItem> inventory_in_items) {
        this.inventory_in_items = inventory_in_items;
    }
}
