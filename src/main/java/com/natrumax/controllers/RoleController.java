package com.natrumax.controllers;

import com.natrumax.dto.request.CreateRoleRequest;
import com.natrumax.dto.request.UpdateRoleRequest;
import com.natrumax.models.Role;
import com.natrumax.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @GetMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Role>> getAllRoles() {
        List<Role> roles = roleService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

    @GetMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getRoleById(@PathVariable Integer id) {
        return roleService.getRoleById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Role not found"));
    }

    @PostMapping
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> createRole(@RequestBody CreateRoleRequest request) {
        try {
            Role role = roleService.createRole(request);
            return ResponseEntity.status(HttpStatus.CREATED).body("Role created with ID: " + role.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    //@PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateRole(@PathVariable Integer id, @RequestBody UpdateRoleRequest request) {
        Role updatedRole = roleService.updateRole(id, request);
        if (updatedRole != null) {
            return ResponseEntity.ok(updatedRole);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Role not found");
        }
    }

}
