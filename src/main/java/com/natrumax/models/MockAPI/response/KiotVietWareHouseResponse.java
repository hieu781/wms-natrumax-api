package com.natrumax.models.MockAPI.response;

import com.natrumax.models.MockAPI.KVWarehouseDTO;

import java.util.List;

public class KiotVietWareHouseResponse {
    private int total;
    private int pageSize;
    private List<KVWarehouseDTO> data;

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

    public List<KVWarehouseDTO> getData() {
        return data;
    }

    public void setData(List<KVWarehouseDTO> data) {
        this.data = data;
    }
}
