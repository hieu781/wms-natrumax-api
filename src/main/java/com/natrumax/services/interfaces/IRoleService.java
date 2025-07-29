package com.natrumax.services.interfaces;

import com.natrumax.dto.request.CreateRoleRequest;
import com.natrumax.dto.request.UpdateRoleRequest;
import com.natrumax.models.Enum.ERole;
import com.natrumax.models.Role;

import java.util.List;
import java.util.Optional;

public interface IRoleService {
    Role findByRoleName(ERole roleName);

    List<Role> getAllRoles();

    Optional<Role> getRoleById(Integer id);

    Role createRole(CreateRoleRequest request);

    Role updateRole(Integer id, UpdateRoleRequest request);
}
