package com.natrumax.services.interfaces;

import com.natrumax.dto.response.CommissionResponse;

public interface ICommissionService {
    CommissionResponse getCommissionListByReferrerId(Long referrerId);

    void createReport(int month, int year);
    void createCommissionHistory(int month, int year);
}
