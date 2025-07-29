package com.natrumax.services;

import com.natrumax.dto.request.CreateRoleRequest;
import com.natrumax.dto.request.UpdateRoleRequest;
import com.natrumax.models.Enum.ERole;
import com.natrumax.models.Role;
import com.natrumax.repository.RoleRepository;
import com.natrumax.services.interfaces.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class RoleService implements IRoleService {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role findByRoleName(ERole roleName) {
        Optional<Role> role = roleRepository.findByName(roleName);
        return role.orElse(null);
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Integer id) {
        return roleRepository.findById(id);
    }

    public Role createRole(CreateRoleRequest request) {
        Role role = new Role();
        role.setName(request.getName());
        role.setDescription(request.getDescription());
        role.setCreateDate(LocalDateTime.now());
        return roleRepository.save(role);
    }


    public Role updateRole(Integer id, UpdateRoleRequest request) {
        Optional<Role> optionalRole = roleRepository.findById(id);
        if (optionalRole.isPresent()) {
            Role role = optionalRole.get();
            role.setDescription(request.getDescription());
            role.setModifyDate(LocalDateTime.now());
            return roleRepository.save(role);
        }
        return null; // Return null if role not found
    }

}
