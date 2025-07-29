package com.natrumax.controllers;

import com.natrumax.dto.request.CreateProductRequest;
import com.natrumax.dto.request.UpdateProductRequest;
import com.natrumax.dto.response.ProductResponse;
import com.natrumax.dto.response.WarehouseProductResponse;
import com.natrumax.models.MockAPI.MisaGood;
import com.natrumax.models.Product;
import com.natrumax.models.WarehouseProduct;
import com.natrumax.repository.WarehouseProductRepository;
import com.natrumax.services.APIService.MisaService;
import com.natrumax.services.ProductService;
import com.natrumax.utils.AccessCodeValidatorService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @Autowired
    private final MisaService misaService;

    @Autowired
    private WarehouseProductRepository warehouseProductRepository;

    @Autowired
    private AccessCodeValidatorService accessCodeValidatorService;

    @Autowired
    public ProductController(ProductService productService, MisaService misaService) {
        this.productService = productService;
        this.misaService = misaService;
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/list-products-paging")
    public ResponseEntity<Page<Product>> getProductsByPaging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<Product> products = productService.getProductsByPaging(page, size);
        return ResponseEntity.ok(products);
    }

    // Get all active products (status = true)
    @GetMapping("/active")
    public ResponseEntity<List<Product>> getActiveProducts() {
        List<Product> activeProducts = productService.getProductsByStatus(true);
        return ResponseEntity.ok(activeProducts);
    }

    // Get all inactive products (status = false)
    @GetMapping("/inactive")
    public ResponseEntity<List<Product>> getInactiveProducts() {
        List<Product> inactiveProducts = productService.getProductsByStatus(false);
        return ResponseEntity.ok(inactiveProducts);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProductById(@PathVariable Long id) {
        return productService.getProductById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Product not found"));
    }

    @PostMapping()
    public ResponseEntity<?> createProduct(@Valid @RequestBody CreateProductRequest request) {
        try {
            Product product = productService.createProduct(request);
            return ResponseEntity.status(HttpStatus.CREATED).body(product);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error creating product: " + e.getMessage());
        }
    }

    @PutMapping("/{id}/change-status")
    public ResponseEntity<?> updateProductStatus(@PathVariable Long id) {
        try{
            Product updatedProduct = productService.updateProductStatus(id);
            return ResponseEntity.ok("Product is "+(updatedProduct.isStatus() ? "activated" : "deactivated"));
        }catch (RuntimeException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateProduct(
            @PathVariable Long id,
            @RequestPart("request") UpdateProductRequest request,
            @RequestPart(value = "imageFile", required = false) MultipartFile imageFile) {
        try {
            Product updatedProduct = productService.updateProduct(id, request, imageFile);
            return ResponseEntity.ok(updatedProduct);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error updating product: " + e.getMessage());
        }
    }

    @GetMapping("/{productId}/warehouse/{warehouseId}")
    public ResponseEntity<?> getProductDetailByProductIdAndWarehouseId(
            @PathVariable Long productId,
            @PathVariable Long warehouseId) {

        Optional<WarehouseProduct> warehouseProductOpt =
                warehouseProductRepository.findByProduct_ProductIdAndWarehouse_WarehouseId(productId, warehouseId);

        if (warehouseProductOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Products not found");
        }

        WarehouseProduct warehouseProduct = warehouseProductOpt.get();
        Product product = warehouseProduct.getProduct();

        // Trả về thông tin chi tiết sản phẩm + số lượng trong kho
        WarehouseProductResponse response = new WarehouseProductResponse(
                product.getProductId(),
                product.getBarcode(),
                product.getMisaCode(),
                product.getName(),
                product.getImage(),
                product.getCategory(),
                product.getBasePrice(),
                product.getDiscount(),
                warehouseProduct.getQuantity(),
                product.getUnit(),
                product.getDescription(),
                product.getQuantityToGetPromotion(),
                product.isStatus()
                );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/image/{productId}")
    public ResponseEntity<?> uploadImage(@PathVariable final Long productId, @RequestPart final MultipartFile file) {
        productService.uploadImage(productId, file);
        return ResponseEntity.ok("Upload successfully");
    }

    @GetMapping("/warehouse/{warehouseId}")
    public ResponseEntity<List<ProductResponse>> getWarehouseProducts(@PathVariable Long warehouseId) {
        List<ProductResponse> products = productService.getWarehouseProducts(warehouseId);
        return ResponseEntity.ok(products);
    }
}
