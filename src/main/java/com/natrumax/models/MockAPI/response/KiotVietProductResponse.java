package com.natrumax.models.MockAPI.response;

import com.natrumax.models.MockAPI.KiotVietProduct;

import java.util.List;

public class KiotVietProductResponse {
    private int total;
    private int pageSize;
    private List<KiotVietProduct> data;

    // Getters and setters


    public KiotVietProductResponse(int total, int pageSize, List<KiotVietProduct> data) {
        this.total = total;
        this.pageSize = pageSize;
        this.data = data;
    }

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

    public List<KiotVietProduct> getData() {
        return data;
    }

    public void setData(List<KiotVietProduct> data) {
        this.data = data;
    }
}

