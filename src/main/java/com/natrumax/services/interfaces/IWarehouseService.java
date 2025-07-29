package com.natrumax.services.interfaces;

import com.natrumax.dto.request.CreateWarehouseRequest;
import com.natrumax.dto.request.UpdateWarehouseRequest;
import com.natrumax.dto.response.WarehouseProductResponse;
import com.natrumax.models.Enum.ERole;
import com.natrumax.models.Product;
import com.natrumax.models.User;
import com.natrumax.models.Warehouse;

import java.util.List;
import java.util.Optional;

public interface IWarehouseService {
    List<Warehouse> getAllWarehouses();

    Optional<Warehouse> getWarehouseById(Long id);

    Warehouse createWarehouse(CreateWarehouseRequest request);

    Warehouse updateWarehouse(Long id, UpdateWarehouseRequest request);

//    List<Product> getProductsByUserRoleAndProvince(ERole role, Long provinceId);

    List<WarehouseProductResponse> getProductsByWarehouse(Long warehouseId);

    User getWarehouseOwnerByMemberId(Long memberId);
}
