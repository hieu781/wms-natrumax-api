package com.natrumax.repository;

import com.natrumax.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WarehouseRepository extends JpaRepository<Warehouse, Long> {
    List<Warehouse> findByProvince_ProvinceId(Long provinceId);

//    @Query("SELECT w FROM Warehouse w WHERE w.province.id = :provinceId AND w.isMainWarehouse = true")
//    Optional<Warehouse> findMainWarehouseByProvince(@Param("provinceId") Long provinceId);
//
//    @Query("SELECT w FROM Warehouse w WHERE w.province.id = :provinceId AND w.isMainWarehouse = false")
//    List<Warehouse> findBranchWarehousesByProvince(@Param("provinceId") Long provinceId);


}