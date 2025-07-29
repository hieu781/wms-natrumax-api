package com.natrumax.models.MockAPI;

import lombok.Data;

@Data
public class MisaWarehouseGood {
    private String _id;
    private String warehouse;
    private MisaGood goods;
    private int quantity;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public MisaGood getGoods() {
        return goods;
    }

    public void setGoods(MisaGood goods) {
        this.goods = goods;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
