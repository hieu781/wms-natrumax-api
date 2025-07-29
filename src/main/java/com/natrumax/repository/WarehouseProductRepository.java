package com.natrumax.repository;

import com.natrumax.models.Product;
import com.natrumax.models.Role;
import com.natrumax.models.WarehouseProduct;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseProductRepository extends JpaRepository<WarehouseProduct, Long> {
    List<WarehouseProduct> findByWarehouse_WarehouseId(Long warehouseId);

    Optional<WarehouseProduct> findByProduct_ProductIdAndWarehouse_WarehouseId(Long productId, Long warehouseId);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<WarehouseProduct> findByWarehouse_WarehouseIdAndProduct_ProductId(Long warehouseId, Long productId);
    List<WarehouseProduct> findByWarehouse_WarehouseIdAndProduct_Category_CategoryId(Long warehouseId, Long categoryId);

    @Query("SELECT wp.product FROM WarehouseProduct wp " +
            "JOIN wp.warehouse w " +
            "JOIN w.userWarehouses uw " +
            "WHERE uw.user.id = :userId")
    List<Product> findProductsByUserId(@Param("userId") Long userId);
    WarehouseProduct findByProduct_ProductId(Long productId);
}