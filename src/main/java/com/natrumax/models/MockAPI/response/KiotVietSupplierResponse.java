package com.natrumax.models.MockAPI.response;

import com.natrumax.models.MockAPI.KiotVietBranch;
import com.natrumax.models.MockAPI.KiotVietSupplier;

import java.util.List;

public class KiotVietSupplierResponse {
    private int total;
    private int pageSize;
    private List<KiotVietSupplier> data;

    public KiotVietSupplierResponse() {
    }

    public KiotVietSupplierResponse(int total, int pageSize, List<KiotVietSupplier> data) {
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

    public List<KiotVietSupplier> getData() {
        return data;
    }

    public void setData(List<KiotVietSupplier> data) {
        this.data = data;
    }
}
