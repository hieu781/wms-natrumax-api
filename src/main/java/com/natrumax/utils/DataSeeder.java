package com.natrumax.utils;

<<<<<<< HEAD
import com.natrumax.models.Enum.ERole;
import com.natrumax.models.Province;
import com.natrumax.models.Role;
import com.natrumax.models.User;
import com.natrumax.repository.ProvinceRepository;
import com.natrumax.repository.RoleRepository;
import com.natrumax.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;

@Component
public class DataSeeder {

    private final RoleRepository roleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    private ProvinceRepository provinceRepository;

    public DataSeeder(RoleRepository roleRepository, UserRepository userRepository, ProvinceRepository provinceRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.provinceRepository = provinceRepository;
    }

    @PostConstruct
    @Transactional
    public void init() {
        seedRoles();
//        seedProvince();
//        seedUsers();
    }

//    private void seedProvince(){
//        if (provinceRepository.count() == 0){
//            Province province1 = new Province();
//            province1.setName("Hải Dương");
//            province1.setProvinceCode("HD");
//            province1.setCreateDate(LocalDate.now());
//            province1.setRegion("Miền Bắc");
//
//            provinceRepository.save(province1);
//
//            Province province2 = new Province();
//            province2.setName("Hà Nội");
//            province2.setProvinceCode("HN");
//            province2.setCreateDate(LocalDate.now());
//            province2.setRegion("Miền Bắc");
//
//            provinceRepository.save(province2);
//
//            Province province3 = new Province();
//            province3.setName("Hưng Yên");
//            province3.setProvinceCode("HY");
//            province3.setCreateDate(LocalDate.now());
//            province3.setRegion("Miền Bắc");
//
//            provinceRepository.save(province3);
//        }
//    }

    private void seedRoles() {
        if (roleRepository.count() == 0) {
            Role adminRole = new Role(ERole.ROLE_ADMIN);
            adminRole.setCreateDate(LocalDateTime.now());
            roleRepository.save(adminRole);

            Role userRole = new Role(ERole.ROLE_CUSTOMER);
            userRole.setCreateDate(LocalDateTime.now());
            roleRepository.save(userRole);

            Role distributorRole = new Role(ERole.ROLE_DISTRIBUTOR);
            distributorRole.setCreateDate(LocalDateTime.now());
            roleRepository.save(distributorRole);

            Role branchOwnerRole = new Role(ERole.ROLE_BRANCH_OWNER);
            branchOwnerRole.setCreateDate(LocalDateTime.now());
            roleRepository.save(branchOwnerRole);

            Role accountantRole = new Role(ERole.ROLE_ACCOUNTANT);
            accountantRole.setCreateDate(LocalDateTime.now());
            roleRepository.save(accountantRole);
        }
    }

//    private void seedUsers() {
//        if (userRepository.count() == 0) {
//            Optional<Role> adminRole = roleRepository.findByName(ERole.ROLE_ADMIN);
//            Optional<Role> userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER);
//            Optional<Role> distributorRole = roleRepository.findByName(ERole.ROLE_DISTRIBUTOR);
//            Optional<Role> branchOwnerRole = roleRepository.findByName(ERole.ROLE_BRANCH_OWNER);
//            Optional<Role> accountantRole = roleRepository.findByName(ERole.ROLE_ACCOUNTANT);
//            Optional<Province> province1 = provinceRepository.findById(1L);
//            Optional<Province> province2 = provinceRepository.findById(2L);
//            Optional<Province> province3 = provinceRepository.findById(3L);
//
//            if (adminRole.isPresent() && userRole.isPresent() && distributorRole.isPresent()
//                    && branchOwnerRole.isPresent() && accountantRole.isPresent()) {
//                User admin = new User();//1
//                admin.setAccountName("admin");
//                admin.setEmail("admin@example.com");
//                admin.setPassword(passwordEncoder.encode("12345678"));
//                admin.setPhoneNumber("0812497838");
//                admin.setAddress("Admin Street, City");
//                admin.setDetail("{}"); // JSON field (empty)
//                admin.setCreateDate(LocalDateTime.now());
//                admin.setStatus(true);
//                admin.setRole(adminRole.get());
//                admin.setProvince(province1.get());
//
//                userRepository.save(admin);
//
//
//                //Create distributor 1 2
//                User distributor1 = new User();
//                distributor1.setAccountName("NPP Hải Dương  - Nguyễn Thị Phương");
//                distributor1.setEmail("distributor1@example.com");
//                distributor1.setPassword(passwordEncoder.encode("12345678"));
//                distributor1.setPhoneNumber("0962178164");
//                distributor1.setAddress("Hải Dương");
//                int warehouseId1 = 1;
//                String misaCode1 = "MB.HD.PHUONG";
//                distributor1.setDetail(String.format("{\"warehouse_id\": %d, \"misa_code\": \"%s\"}", warehouseId1, misaCode1)); // JSON field (empty)
//                distributor1.setCreateDate(LocalDateTime.now());
//                distributor1.setStatus(true);
//                distributor1.setRole(distributorRole.get());
//                distributor1.setProvince(province1.get());
//
//                userRepository.save(distributor1);
//
//                //Create distributor 2 3
//                User distributor2 = new User();
//                distributor2.setAccountName("NPP Mê Linh Hà Nội - Nguyễn Thị Thúy Hằng");
//                distributor2.setEmail("distributor2@example.com");
//                distributor2.setPassword(passwordEncoder.encode("12345678"));
//                distributor2.setPhoneNumber("0965878989");
//                distributor2.setAddress("Thôn 4 Hạ Lôi, xã Mê Linh, huyện Mê Linh, thành phố Hà Nội");
//                int warehouseId2 = 2;
//                String misaCode2 = "MB.HN.HANG";
//                distributor2.setDetail(String.format("{\"warehouse_id\": %d, \"misa_code\": \"%s\"}", warehouseId2, misaCode2));
//                distributor2.setCreateDate(LocalDateTime.now());
//                distributor2.setStatus(true);
//                distributor2.setRole(distributorRole.get());
//                distributor2.setProvince(province2.get());
//
//                userRepository.save(distributor2);
//
//                //create branch owner 1 4
//                User branchOwner1 = new User();
//                branchOwner1.setAccountName("Chi nhánh 107");
//                branchOwner1.setEmail("branchOwner1@example.com");
//                branchOwner1.setPassword(passwordEncoder.encode("12345678"));
//                branchOwner1.setPhoneNumber("0812497838");
//                branchOwner1.setAddress("263, Nguyễn Hữu Cầu, Ngọc Châu, Hải Dương");
//                String misaCode3 = "MB.HD.107";
//                String clientId1 = "your-client-id";
//                String clientSecret1 = "your-client-secret";
//                String retailer1 = "your-retailer-name";
//                branchOwner1.setDetail(String.format(
//                        "{\"retailer\": \"%s\", \"client_id\": \"%s\", \"client_secret\": \"%s\", \"misa_code\": \"%s\", \"warehouse_id\": \"%s\"}",
//                        retailer1, clientId1, clientSecret1, misaCode3, warehouseId1
//                ));
//                branchOwner1.setCreateDate(LocalDateTime.now());
//                branchOwner1.setStatus(true);
//                branchOwner1.setRole(branchOwnerRole.get());
//                branchOwner1.setProvince(province1.get());
//
//                userRepository.save(branchOwner1);
//
//                //create branch owner 5
//                User branchOwner2 = new User();
//                branchOwner2.setAccountName("Chi nhánh 259");
//                branchOwner2.setEmail("branchOwner2@example.com");
//                branchOwner2.setPassword(passwordEncoder.encode("12345678"));
//                branchOwner2.setPhoneNumber("0979210138");
//                branchOwner2.setAddress("Số 04 ngõ 4, đường bến Phà, khu Hà Trì 1, phường Hà Cầu, quận Hà Đông, TP Hà Nội");
//                String misaCode4 = "MB.HN.259";
//                String clientId2 = "your-client-id";
//                String clientSecret2 = "your-client-secret";
//                String retailer2 = "your-retailer-name";
//                branchOwner2.setDetail(String.format(
//                        "{\"retailer\": \"%s\", \"client_id\": \"%s\", \"client_secret\": \"%s\", \"misa_code\": \"%s\", \"warehouse_id\": \"%s\"}",
//                        retailer2, clientId2, clientSecret2, misaCode4, warehouseId2
//                ));
//                branchOwner2.setCreateDate(LocalDateTime.now());
//                branchOwner2.setStatus(true);
//                branchOwner2.setRole(branchOwnerRole.get());
//                branchOwner2.setProvince(province2.get());
//
//                userRepository.save(branchOwner2);
//
//                //create branch owner 6
//                User branchOwner3 = new User();
//                branchOwner3.setAccountName("Chi nhánh 101");
//                branchOwner3.setEmail("branchOwner3@example.com");
//                branchOwner3.setPassword(passwordEncoder.encode("12345678"));
//                branchOwner3.setPhoneNumber("0976688003");
//                branchOwner3.setAddress("Xã Trung Hoà - Huyện Yên Mĩ- Hưng Yên");
//                String misaCode5 = "MB.HY.101";
//                String clientId3 = "your-client-id";
//                String clientSecret3 = "your-client-secret";
//                String retailer3 = "your-retailer-name";
//                branchOwner3.setDetail(String.format(
//                        "{\"retailer\": \"%s\", \"client_id\": \"%s\", \"client_secret\": \"%s\", \"misa_code\": \"%s\", \"warehouse_id\": \"%s\"}",
//                        retailer3, clientId3, clientSecret3, misaCode5, warehouseId1
//                ));
//                branchOwner3.setCreateDate(LocalDateTime.now());
//                branchOwner3.setStatus(true);
//                branchOwner3.setRole(branchOwnerRole.get());
//                branchOwner3.setProvince(province3.get());
//
//                userRepository.save(branchOwner3);
//
//                //create accountant 7
//                User accountant = new User();
//                accountant.setAccountName("Kế toán Kho miền Bắc");
//                accountant.setEmail("accountant@example.com");
//                accountant.setPassword(passwordEncoder.encode("12345678"));
//                accountant.setPhoneNumber("0812497838");
//                accountant.setAddress("Hải Dương");
//                accountant.setDetail(String.format("{\"warehouse_id\": %d}", warehouseId1));
//                accountant.setCreateDate(LocalDateTime.now());
//                accountant.setStatus(true);
//                accountant.setRole(accountantRole.get());
//                accountant.setProvince(province1.get());
//
//                userRepository.save(accountant);
//            }
//        }
//    }
=======
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
>>>>>>> d7518d23dffcd1f22a4f928625d441902c8edfe6
}
