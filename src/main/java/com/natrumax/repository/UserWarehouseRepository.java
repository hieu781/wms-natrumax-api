package com.natrumax.repository;

import com.natrumax.dto.response.WarehouseResponse;
import com.natrumax.models.User;
import com.natrumax.models.UserWarehouse;
import com.natrumax.models.Warehouse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserWarehouseRepository extends JpaRepository<UserWarehouse, Long> {

    List<UserWarehouse> findByUserId(Long userId);

    List<UserWarehouse> findByWarehouse_WarehouseId(Long warehouseId);

    @Query("SELECT NEW com.natrumax.dto.response.WarehouseResponse(w.warehouseId, w.warehouseName, w.description, uw.roleInWarehouse" +
            ", w.accessCode) " +
            "FROM UserWarehouse uw JOIN uw.warehouse w WHERE uw.user.id = :userId")
    List<WarehouseResponse> findWarehouseDTOsByUserId(@Param("userId") Long userId);

    Optional<UserWarehouse> findByUserIdAndRoleInWarehouse(Long userId, String roleInWarehouse);

    Optional<UserWarehouse> findByWarehouseAndRoleInWarehouse(Warehouse warehouse, String roleInWarehouse);
}

