package com.natrumax.services;

import com.natrumax.dto.request.CreateWarehouseProductRequest;
import com.natrumax.models.Product;
import com.natrumax.models.Warehouse;
import com.natrumax.models.WarehouseProduct;
import com.natrumax.repository.ProductRepository;
import com.natrumax.repository.WarehouseProductRepository;
import com.natrumax.repository.WarehouseRepository;
import com.natrumax.services.interfaces.IWarehouseProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class WarehouseProductService implements IWarehouseProductService {
    @Autowired
    private WarehouseProductRepository warehouseProductRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private ProductRepository productRepository;

    public WarehouseProduct createWarehouseProduct(CreateWarehouseProductRequest request) {
        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        WarehouseProduct warehouseProduct = new WarehouseProduct();
        warehouseProduct.setWarehouse(warehouse);
        warehouseProduct.setProduct(product);
        warehouseProduct.setQuantity(request.getQuantity());

        return warehouseProductRepository.save(warehouseProduct);
    }

    public List<Product> getProductsByUser(Long userId) {
        return warehouseProductRepository.findProductsByUserId(userId);
    }

    public List<Product> getProductsByCategoryAndWarehouse(Long warehouseId, Long categoryId) {
        List<WarehouseProduct> warehouseProducts =
                warehouseProductRepository.findByWarehouse_WarehouseIdAndProduct_Category_CategoryId(warehouseId, categoryId);

        // Lấy danh sách sản phẩm từ WarehouseProduct
        return warehouseProducts.stream()
                .map(WarehouseProduct::getProduct)
                .collect(Collectors.toList());
    }
}
