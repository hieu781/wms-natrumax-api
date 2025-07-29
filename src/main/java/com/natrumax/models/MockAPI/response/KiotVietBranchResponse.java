package com.natrumax.models.MockAPI.response;

import com.natrumax.models.MockAPI.KiotVietBranch;
import com.natrumax.models.MockAPI.MisaGood;

import java.util.List;

public class KiotVietBranchResponse {
    private int total;
    private int pageSize;
    private List<KiotVietBranch> data;

    public KiotVietBranchResponse() {
    }

    public KiotVietBranchResponse(int total, int pageSize, List<KiotVietBranch> data) {
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

    public List<KiotVietBranch> getData() {
        return data;
    }

    public void setData(List<KiotVietBranch> data) {
        this.data = data;
    }
}
