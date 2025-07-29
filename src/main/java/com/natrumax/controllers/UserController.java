package com.natrumax.controllers;

import com.natrumax.dto.request.ChangePasswordRequest;
import com.natrumax.dto.request.CreateUserRequest;
import com.natrumax.dto.request.UpdateUserRequest;
import com.natrumax.dto.response.UserResponse;
import com.natrumax.models.Enum.ERole;
import com.natrumax.models.User;
import com.natrumax.repository.RoleRepository;
import com.natrumax.repository.UserRepository;
import com.natrumax.security.jwt.JwtUtils;
import com.natrumax.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @Autowired
    UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserResponse>> getUsers() {
        List<UserResponse> users = userService.getAllUser();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/list-users-paging")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<User>> getUsersByPaging(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Page<User> users = userService.getAllUserByPaging(page, size);
        return ResponseEntity.ok(users);
    }


    @GetMapping("/{id}")
//    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> getUserById(@PathVariable Long id){
        return userService.getUserById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("User not found"));
    }


    @PutMapping("/{id}/change-status")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> toggleStatus(@PathVariable Long id){
        try{
            User user = userService.ToggleUserActiveStatus(id);
            return ResponseEntity.ok("User is "+(user.isStatus() ? "activated" : "deactivated"));
        }catch (RuntimeException e){
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

//    @PostMapping()
//    @PreAuthorize("hasRole('ADMIN')")
//    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserRequest request) {
//        try {
//            User user = userService.createUser(request);
//            return ResponseEntity.status(HttpStatus.CREATED).body("User created with ID: " + user.getId());
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
//        }
//    }

    @PostMapping()
    public ResponseEntity<User> createUser(@RequestBody CreateUserRequest request) {
        User user = userService.createUserFromMisa(request);
        return ResponseEntity.ok(user);
    }

    @PutMapping("/{id}")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateUser(
            @PathVariable Long id,
            @Valid @RequestBody UpdateUserRequest request) {
        try {
            User updatedUser = userService.updateUser(id, request);
            return ResponseEntity.ok("User updated successfully: " + updatedUser.getId());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @PutMapping("/{id}/change-password")
    @PreAuthorize("hasRole('CUSTOMER') or hasRole('ADMIN')")
    public ResponseEntity<?> changePassword(
            @PathVariable Long id,
            @Valid @RequestBody ChangePasswordRequest request) {
        try {
            userService.changePassword(id, request);
            return ResponseEntity.ok("Password changed successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<User>> getUsersByRole(@PathVariable("role") ERole role) {
        List<User> users = userService.getUsersByRole(role);
        return ResponseEntity.ok(users);
    }
}
