package com.natrumax.utils;

import com.natrumax.config.MisaApiClient;
import com.natrumax.models.Warehouse;
import com.natrumax.repository.WarehouseRepository;
import org.springframework.stereotype.Service;

@Service
public class AccessCodeValidatorService {

    private final MisaApiClient misaApiClient;
    private final WarehouseRepository warehouseRepository;

    public AccessCodeValidatorService(MisaApiClient misaApiClient, WarehouseRepository warehouseRepository) {
        this.misaApiClient = misaApiClient;
        this.warehouseRepository = warehouseRepository;
    }

//    public boolean isValidForWarehouse(Long warehouseId, String accessCodeHeader) {
//        Warehouse warehouse = warehouseRepository.findById(warehouseId)
//                .orElse(null);
//
//        if (warehouse == null) return false;
//
//        // Kiểm tra access_code từ header có khớp clientSecret trong MISA không
//        return misaApiClient.getAllMisaWarehouses().getData().stream()
//                .anyMatch(mw ->
//                        mw.getClientSecret().equals(accessCodeHeader)
//                                && mw.getClientSecret().equals(warehouse.getAccessCode()));
//    }
}
