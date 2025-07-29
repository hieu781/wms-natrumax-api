package com.natrumax.models.MockAPI.response;

import com.natrumax.models.MockAPI.MisaBank;
import lombok.Data;

import java.util.List;

@Data
public class MisaBankResponse {
    private int total;
    private int pageSize;
    private List<MisaBank> data;

    public MisaBankResponse(int total, int pageSize, List<MisaBank> data) {
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

    public List<MisaBank> getData() {
        return data;
    }

    public void setData(List<MisaBank> data) {
        this.data = data;
    }
}
