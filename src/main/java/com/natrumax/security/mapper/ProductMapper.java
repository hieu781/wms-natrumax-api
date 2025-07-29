package com.natrumax.security.mapper;

import com.natrumax.dto.response.ProductResponse;
import com.natrumax.models.MockAPI.MisaGood;
import com.natrumax.models.Product;
import com.natrumax.models.WarehouseProduct;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Component
public class ProductMapper {

//    public Product mapToProduct(MisaGood misaGood) {
//        Product product = new Product();
//
//        // Set the MISA code
//        product.setMisaCode(misaGood.getCode());
//
//        // Map other fields
//        product.setName(misaGood.getName());
//        product.setDescription(misaGood.getDescription() != null && !misaGood.getDescription().isEmpty()
//                ? misaGood.getDescription()
//                : misaGood.getName());
//
//        // Convert price string to double
//        try {
//            double price = Double.parseDouble(misaGood.getPrice());
//            product.setBasePrice(price);
//        } catch (NumberFormatException e) {
//            product.setBasePrice(0.0);
//        }
//
//        // Convert quantity string to int
////        try {
////            int qty = Integer.parseInt(misaGood.getQuantity());
////            product.setQuantity(qty);
////        } catch (NumberFormatException e) {
////            product.setQuantity(0);
////        }
//
//        // Set default values
//        product.setBarcode(misaGood.getId()); // Using MISA ID as barcode initially
//        product.setCreateDate(LocalDateTime.now());
//        product.setDiscount(0);
//        product.setStatus(true);
//
//        // Map unit - might need a proper mapping based on your unit values
//        product.setUnit("Hộp");
//
//        return product;
//    }

    public ProductResponse toProductResponse(WarehouseProduct warehouseProduct) {
        Product product = warehouseProduct.getProduct();
        return new ProductResponse(
                product.getProductId(),
                product.getBarcode(),
                product.getMisaCode(),
                product.getName(),
                product.getImage(),
                product.getCategory().getCategoryName(),
                product.getBasePrice(),
                product.getDiscount(),
                warehouseProduct.getQuantity(),
                product.getUnit(),
                product.getQuantityToGetPromotion(),
                product.getDescription(),
                product.isStatus()
        );
    }

    public Product mapToProduct(MisaGood misaGood, String barcode) {
        Product product = new Product();

        product.setMisaCode(misaGood.getCode());
        product.setName(misaGood.getName());
        product.setDescription(misaGood.getDescription());
        product.setUnit(misaGood.getGroup());

        // Default fields
        product.setBarcode(barcode);

        // Convert price string to double
        try {
            double price = Double.parseDouble(misaGood.getPrice());
            product.setBasePrice(price);
        } catch (NumberFormatException e) {
            product.setBasePrice(0.0);
        }

        product.setDiscount(0);
        product.setQuantityToGetPromotion(0);
        product.setStatus(true);

        product.setCreateDate(LocalDateTime.now());
        product.setModifyDate(LocalDateTime.now());

        return product;
    }
}