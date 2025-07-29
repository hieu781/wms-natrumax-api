package com.natrumax.models.MockAPI.response;

import com.natrumax.models.MockAPI.MisaSaleDTO;

import java.util.List;

public class MisaSaleResponse {
    private int total;
    private int pageSize;
    private List<MisaSaleDTO> data;

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

    public List<MisaSaleDTO> getData() {
        return data;
    }

    public void setData(List<MisaSaleDTO> data) {
        this.data = data;
    }
}
