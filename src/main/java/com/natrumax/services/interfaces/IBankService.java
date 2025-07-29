package com.natrumax.services.interfaces;

import com.natrumax.dto.request.CreateBankRequest;
import com.natrumax.dto.request.UpdateBankRequest;
import com.natrumax.models.Banks;

public interface IBankService {
    Banks getBankById(Long id);

    Banks getBankByUserId(Long userId);

    Banks createBank(CreateBankRequest request);

    Banks updateBank(Long id, UpdateBankRequest request);
}
