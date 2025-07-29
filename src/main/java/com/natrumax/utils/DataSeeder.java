package com.natrumax.utils;

import com.natrumax.models.Role;
import com.natrumax.models.User;
import com.natrumax.repository.RoleRepository;
import com.natrumax.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.Optional;

@Component
public class DataSeeder implements CommandLineRunner {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;

    public DataSeeder(RoleRepository roleRepository, UserRepository userRepository) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public void run(String... args) {
        seedRoles();
        seedUsers();
    }

    private void seedRoles() {
        if (roleRepository.count() == 0) {
            Role adminRole = new Role();
            adminRole.setName("ADMIN");
            adminRole.setCreateDate(new Date());
            roleRepository.save(adminRole);

            Role userRole = new Role();
            userRole.setName("USER");
            userRole.setCreateDate(new Date());
            roleRepository.save(userRole);

            System.out.println("Roles seeded successfully.");
        }
    }

    private void seedUsers() {
        if (userRepository.count() == 0) {
            Optional<Role> adminRole = roleRepository.findByName("ADMIN");
            Optional<Role> userRole = roleRepository.findByName("USER");

            if (adminRole.isPresent() && userRole.isPresent()) {
                User adminUser = new User();
                adminUser.setFullName("Admin User");
                adminUser.setUsername("admin");
                adminUser.setPhoneNumber("0976688003");
                adminUser.setAddress("Admin Address");
                adminUser.setClientId("admin-client");
                adminUser.setClientSecret("admin-secret");
                adminUser.setRetailer("Retailer X");
                adminUser.setCreateDate(new Date());
                adminUser.setStatus(true);
                adminUser.setRole(adminRole.get());
                userRepository.save(adminUser);

                User normalUser = new User();
                normalUser.setFullName("Normal User");
                normalUser.setUsername("user");
                normalUser.setPhoneNumber("987654321");
                normalUser.setAddress("User Address");
                normalUser.setClientId("user-client");
                normalUser.setClientSecret("user-secret");
                normalUser.setRetailer("Retailer Y");
                normalUser.setCreateDate(new Date());
                normalUser.setStatus(true);
                normalUser.setRole(userRole.get());
                userRepository.save(normalUser);

                System.out.println("Users seeded successfully.");
            }
        }
    }
}
