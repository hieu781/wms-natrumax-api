package com.natrumax.models.MockAPI.response;

import com.natrumax.models.MockAPI.MisaCustomer;
import com.natrumax.models.MockAPI.MisaGood;

import com.natrumax.models.MockAPI.KiotVietSupplier;
import com.natrumax.models.MockAPI.MisaCustomer;


import java.util.List;

public class MisaCustomerResponse {
    private int total;
    private int pageSize;
    private List<MisaCustomer> data;


    public MisaCustomerResponse(int total, int pageSize, List<MisaCustomer> data) {
        this.total = total;
        this.pageSize = pageSize;
        this.data = data;
    }

    public MisaCustomerResponse() {
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

    public List<MisaCustomer> getData() {
        return data;
    }

    public void setData(List<MisaCustomer> data) {
        this.data = data;
    }
}
