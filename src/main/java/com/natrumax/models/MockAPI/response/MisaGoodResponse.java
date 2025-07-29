package com.natrumax.models.MockAPI.response;

import com.natrumax.models.MockAPI.MisaGood;

import java.util.List;

public class MisaGoodResponse {
    private int total;
    private int pageSize;
    private List<MisaGood> data;

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

    public List<MisaGood> getData() {
        return data;
    }

    public void setData(List<MisaGood> data) {
        this.data = data;
    }
}
