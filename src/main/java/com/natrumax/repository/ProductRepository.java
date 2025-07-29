package com.natrumax.repository;

import com.natrumax.models.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByStatus(boolean status);

    Optional<Product> findByMisaCode(String misaId);

    Optional<Product> findByProductId(Long productId);

    @Query("SELECT wp.product FROM WarehouseProduct wp WHERE wp.warehouse.warehouseId = :warehouseId")
    List<Product> findProductsByWarehouseId(@Param("warehouseId") Long warehouseId);

    List<Product> findByCategoryCategoryId(Long categoryId);
}
