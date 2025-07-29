package com.natrumax.models.MockAPI.response;

import com.natrumax.models.MockAPI.MisaWarehouse;

import java.util.List;

public class MisaWarehouseResponse {
    private int total;
    private int pageSize;
    private List<MisaWarehouse> data;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<MisaWarehouse> getData() {
        return data;
    }

    public void setData(List<MisaWarehouse> data) {
        this.data = data;
    }
}
