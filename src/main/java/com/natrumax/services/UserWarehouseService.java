package com.natrumax.services;

import com.natrumax.dto.request.CreateUserWarehouseRequest;
import com.natrumax.dto.request.UpdateUserWarehouseRequest;
import com.natrumax.dto.response.WarehouseResponse;
import com.natrumax.models.User;
import com.natrumax.models.UserWarehouse;
import com.natrumax.models.Warehouse;
import com.natrumax.repository.UserRepository;
import com.natrumax.repository.UserWarehouseRepository;
import com.natrumax.repository.WarehouseRepository;
import com.natrumax.services.interfaces.IUserWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserWarehouseService implements IUserWarehouseService {
    @Autowired
    private UserWarehouseRepository userWarehouseRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WarehouseRepository warehouseRepository;

    public List<UserWarehouse> getAllUserWarehouses() {
        return userWarehouseRepository.findAll();
    }

    public UserWarehouse getUserWarehouseById(Long id) {
        return userWarehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserWarehouse not found"));
    }

    public UserWarehouse createUserWarehouse(CreateUserWarehouseRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        UserWarehouse userWarehouse = new UserWarehouse();
        userWarehouse.setUser(user);
        userWarehouse.setWarehouse(warehouse);
        userWarehouse.setRoleInWarehouse(request.getRole());
        userWarehouse.setCreateDate(LocalDateTime.now());

        return userWarehouseRepository.save(userWarehouse);
    }

    public UserWarehouse updateUserWarehouse(Long id, UpdateUserWarehouseRequest request) {
        UserWarehouse userWarehouse = userWarehouseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("UserWarehouse not found"));

        userWarehouse.setRoleInWarehouse(request.getRole());
        userWarehouse.setModifyDate(LocalDateTime.now());

        return userWarehouseRepository.save(userWarehouse);
    }

    public List<WarehouseResponse> getWarehousesByUserId(Long userId) {
        return userWarehouseRepository.findWarehouseDTOsByUserId(userId);
    }
}
