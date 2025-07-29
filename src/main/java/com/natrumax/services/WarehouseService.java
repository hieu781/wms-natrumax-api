package com.natrumax.services;

import com.natrumax.dto.request.CreateWarehouseRequest;
import com.natrumax.dto.request.UpdateWarehouseRequest;
import com.natrumax.dto.response.WarehouseProductResponse;
import com.natrumax.models.*;
import com.natrumax.models.Enum.ERole;
import com.natrumax.repository.*;
import com.natrumax.services.interfaces.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProvinceRepository provinceRepository;
    @Autowired
    private WarehouseProductRepository warehouseProductRepository;
    @Autowired
    private UserWarehouseRepository userWarehouseRepository;

    @Override
    public List<Warehouse> getAllWarehouses() {
        return warehouseRepository.findAll();
    }

    @Override
    public Optional<Warehouse> getWarehouseById(Long id) {
        return warehouseRepository.findById(id);
    }

    @Override
    public Warehouse createWarehouse(CreateWarehouseRequest request) {
        Province province = provinceRepository.findById(request.getProvinceId())
                .orElseThrow(() -> new RuntimeException("Province not found"));

        Warehouse warehouse = new Warehouse();
        warehouse.setAccessCode(request.getAccessCode());
        warehouse.setDescription(request.getDescription());
        warehouse.setWarehouseName(request.getName());
        warehouse.setProvince(province);
        warehouse.setCreateDate(LocalDateTime.now());
//        warehouse.setMainWarehouse(false);
        warehouse.setModifyDate(null);

        Warehouse savedWarehouse = warehouseRepository.save(warehouse);

        // Gán cho user nếu có
        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));

            UserWarehouse userWarehouse = new UserWarehouse();
            userWarehouse.setUser(user);
            userWarehouse.setWarehouse(savedWarehouse);
            userWarehouse.setCreateDate(LocalDateTime.now());

            userWarehouseRepository.save(userWarehouse);
        }

        // Tạo các WarehouseProduct ứng với tất cả các Product hiện có
        List<Product> allProducts = productRepository.findAll();
        List<WarehouseProduct> warehouseProducts = new ArrayList<>();

        for (Product product : allProducts) {
            WarehouseProduct warehouseProduct = new WarehouseProduct();
            warehouseProduct.setWarehouse(savedWarehouse);
            warehouseProduct.setProduct(product);
            warehouseProduct.setQuantity(0);

            warehouseProducts.add(warehouseProduct);
        }

        warehouseProductRepository.saveAll(warehouseProducts);

        return savedWarehouse;
    }



    @Override
    public Warehouse updateWarehouse(Long id, UpdateWarehouseRequest request) {
        Warehouse warehouse = warehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        warehouse.setAccessCode(request.getAccessCode());
        warehouse.setDescription(request.getDescription());
        warehouse.setWarehouseName(request.getName());
        warehouse.setModifyDate(LocalDateTime.now());

        // Hạ cấp Owner hiện tại thành Member nếu có
        for (UserWarehouse uw : warehouse.getUserWarehouses()) {
            if ("Owner".equals(uw.getRoleInWarehouse())) {
                uw.setRoleInWarehouse("Member");
                uw.setModifyDate(LocalDateTime.now());
            }
        }

        // Kiểm tra nếu user đã tồn tại trong warehouse
        Optional<UserWarehouse> existingUW = warehouse.getUserWarehouses().stream()
                .filter(uw -> uw.getUser().getId().equals(user.getId()))
                .findFirst();

        if (existingUW.isPresent()) {
            // Nếu đã tồn tại, cập nhật vai trò thành Owner
            UserWarehouse userWarehouse = existingUW.get();
            userWarehouse.setRoleInWarehouse("Owner");
            userWarehouse.setModifyDate(LocalDateTime.now());
        } else {
            // Nếu chưa tồn tại thì tạo mới
            UserWarehouse userWarehouse = new UserWarehouse();
            userWarehouse.setUser(user);
            userWarehouse.setWarehouse(warehouse);
            userWarehouse.setRoleInWarehouse("Owner");
            userWarehouse.setCreateDate(LocalDateTime.now());
            warehouse.getUserWarehouses().add(userWarehouse);
        }

        return warehouseRepository.save(warehouse);
    }


//    public List<Product> getProductsByUserRoleAndProvince(ERole role, Long provinceId) {
//        Warehouse selectedWarehouse = null;
//
//        if (role == ERole.ROLE_DISTRIBUTOR) {
//            selectedWarehouse = warehouseRepository.findMainWarehouseByProvince(provinceId)
//                    .orElse(null);
//        } else if (role == ERole.ROLE_BRANCH_OWNER) {
//            List<Warehouse> branchWarehouses = warehouseRepository.findBranchWarehousesByProvince(provinceId);
//            if (!branchWarehouses.isEmpty()) {
//                selectedWarehouse = branchWarehouses.get(0);
//            } else {
//                selectedWarehouse = warehouseRepository.findMainWarehouseByProvince(provinceId)
//                        .orElse(null);
//            }
//        } else {
//            return null; // Vai trò không hợp lệ
//        }
//
//        return selectedWarehouse != null ? productRepository.findProductsByWarehouseId(selectedWarehouse.getWarehouseId()) : null;
//    }

    public List<WarehouseProductResponse> getProductsByWarehouse(Long warehouseId) {
        List<WarehouseProduct> warehouseProducts = warehouseProductRepository.findByWarehouse_WarehouseId(warehouseId);

        return warehouseProducts.stream().map(warehouseProduct ->
                new WarehouseProductResponse(
                        warehouseProduct.getProduct().getProductId(),
                        warehouseProduct.getProduct().getBarcode(),
                        warehouseProduct.getProduct().getName(),
                        warehouseProduct.getProduct().getBasePrice(),
                        warehouseProduct.getProduct().getDiscount(),
                        warehouseProduct.getQuantity(),
                        warehouseProduct.getProduct().getQuantityToGetPromotion(),
                        warehouseProduct.getProduct().isStatus()
                )
        ).collect(Collectors.toList());
    }

    public User getWarehouseOwnerByMemberId(Long memberId) {
        UserWarehouse memberUW = userWarehouseRepository
                .findByUserIdAndRoleInWarehouse(memberId, "Member")
                .orElseThrow(() -> new RuntimeException("Member not assigned to a warehouse"));

        Warehouse warehouse = memberUW.getWarehouse();

        UserWarehouse ownerUW = userWarehouseRepository
                .findByWarehouseAndRoleInWarehouse(warehouse, "Owner")
                .orElseThrow(() -> new RuntimeException("Warehouse has no owner"));

        return ownerUW.getUser();
    }
}
