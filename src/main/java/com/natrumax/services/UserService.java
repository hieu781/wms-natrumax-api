package com.natrumax.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.natrumax.config.MisaApiClient;
import com.natrumax.dto.request.ChangePasswordRequest;
import com.natrumax.dto.request.LoginRequest;
import com.natrumax.dto.request.CreateUserRequest;
import com.natrumax.dto.request.UpdateUserRequest;
import com.natrumax.dto.response.ReferrerResponse;
import com.natrumax.dto.response.UserResponse;
import com.natrumax.models.*;
import com.natrumax.models.Enum.ERole;
import com.natrumax.models.MockAPI.MisaCustomer;
import com.natrumax.models.MockAPI.MisaWarehouse;
import com.natrumax.repository.*;
import com.natrumax.services.APIService.MisaService;
import com.natrumax.services.interfaces.IUserService;
import com.natrumax.config.SpeedSMSAPI;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.io.IOException;
import java.security.SecureRandom;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {
    @Override
    public Boolean existsByEmail(String email) {
        return null;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public Boolean existsById(Long id) {
        return null;
    }

    @Override
    public Boolean createAdminUser() {
        return null;
    }

    @Value("${wms-natrumax.app.smsAccessToken}")
    private String smsAccessToken;

    @Value("${wms-natrumax.app.senderId}")
    private String senderId;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private OtpRepository otpRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private MisaService misaService;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private UserWarehouseRepository userWarehouseRepository;

    @Autowired
    private MisaApiClient misaApiClient;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private  PromotionRepository promotionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserProductRepository userProductRepository;

    @Override
    public Boolean existsByPhoneNumber(String phoneNumber) {
        return userRepository.existsByPhoneNumber(phoneNumber);
    }

    @Override
    public Boolean loginUser(LoginRequest loginRequest) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(loginRequest.getPhoneNumber());

        if (optionalUser.isEmpty()) {
            return false; // Người dùng không tồn tại
        }

        User user = optionalUser.get();

        Optional<Otp> otpOptional = otpRepository.findByUser(user);
        Otp otp;

        if (otpOptional.isPresent()) {
            otp = otpOptional.get();

            // Reset bộ đếm nếu sang ngày mới
            if (!otp.getOtpRequestDate().equals(LocalDate.now())) {
                otp.setOtpRequestCount(0);
                otp.setOtpRequestDate(LocalDate.now());
            }

            // Kiểm tra giới hạn số lần gửi OTP trong ngày
            if (otp.getOtpRequestCount() >= 20) {
                return false; // Quá giới hạn
            }

            // Kiểm tra tần suất gửi OTP
            if (otp.getLastOtpSentTime() != null &&
                    Duration.between(otp.getLastOtpSentTime(), LocalDateTime.now()).getSeconds() < 30) {
                return false; // Gửi OTP quá nhanh
            }
        } else {
            otp = new Otp(user, 0, LocalDateTime.now().plusMinutes(5)); // Tạo OTP mới nếu chưa có
        }

        // Tạo OTP mới
        Random r = new SecureRandom();
        int randomOtp = 100000 + r.nextInt(900000);
        otp.setOtpVerification(randomOtp);
        otp.setOtpExpirationTime(LocalDateTime.now().plusMinutes(10));
        otp.setOtpAttempts(0);
        otp.setLastOtpSentTime(LocalDateTime.now());
        otp.setOtpRequestCount(otp.getOtpRequestCount() + 1);

        otpRepository.save(otp); // Lưu vào database

        // Gửi OTP qua SpeedSMS
        SpeedSMSAPI api = new SpeedSMSAPI(smsAccessToken);
        try {
            String result = api.sendSMS(
                    loginRequest.getPhoneNumber(),
                    "Chào bạn, mã xác thực đăng nhập Natrumax của bạn là " + randomOtp,
                    5, senderId
            );
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    @Override
    public Boolean verifyUser(String phoneNumber, Integer otpCode) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);

        if (optionalUser.isEmpty()) {
            return false;
        }

        User user = optionalUser.get();
        Optional<Otp> otpOptional = otpRepository.findByUser(user);

        if (otpOptional.isEmpty()) {
            return false;
        }

        Otp otp = otpOptional.get();

        // Kiểm tra số lần nhập sai
        if (otp.getOtpAttempts() >= 3) {
            return false; // Quá số lần nhập sai
        }

        if (otp.getOtpVerification() == otpCode) {
            if (otp.getOtpExpirationTime().isAfter(LocalDateTime.now())) {
                otpRepository.delete(otp); // Xóa OTP sau khi xác thực thành công
                return true;
            }
            return false; // OTP đã hết hạn
        } else {
            otp.setOtpAttempts(otp.getOtpAttempts() + 1);
            otpRepository.save(otp);
            return false; // OTP sai
        }
    }


    @Override
    public Boolean resendOtp(String phoneNumber) {
        Optional<User> optionalUser = userRepository.findByPhoneNumber(phoneNumber);
        if (optionalUser.isEmpty()) {
            return false; // Người dùng không tồn tại
        }

        User user = optionalUser.get();
        Optional<Otp> otpOptional = otpRepository.findByUser(user);
        Otp otp;

        if (otpOptional.isPresent()) {
            otp = otpOptional.get();

            // Reset bộ đếm nếu sang ngày mới
            if (!otp.getOtpRequestDate().equals(LocalDate.now())) {
                otp.setOtpRequestCount(0);
                otp.setOtpRequestDate(LocalDate.now());
            }

            // Kiểm tra giới hạn số lần gửi OTP trong ngày
            if (otp.getOtpRequestCount() >= 20) {
                return false; // Quá giới hạn số lần gửi OTP trong ngày
            }

            // Kiểm tra khoảng thời gian giữa các lần gửi OTP
            if (otp.getLastOtpSentTime() != null &&
                    Duration.between(otp.getLastOtpSentTime(), LocalDateTime.now()).getSeconds() < 30) {
                return false; // Gửi OTP quá nhanh, chưa đủ 30 giây
            }
        } else {
            otp = new Otp(user, 0, LocalDateTime.now().plusMinutes(5)); // Tạo OTP mới nếu chưa có
        }

        // Tạo OTP mới
        Random r = new SecureRandom();
        int randomOtp = 100000 + r.nextInt(900000);
        otp.setOtpVerification(randomOtp);
        otp.setOtpExpirationTime(LocalDateTime.now().plusMinutes(5)); // 5 phút hết hạn
        otp.setOtpAttempts(0); // Reset số lần nhập sai
        otp.setLastOtpSentTime(LocalDateTime.now());
        otp.setOtpRequestCount(otp.getOtpRequestCount() + 1);

        otpRepository.save(otp); // Lưu vào database

        // Gửi OTP qua SpeedSMS
        SpeedSMSAPI api = new SpeedSMSAPI(smsAccessToken);
        try {
            String result = api.sendSMS(phoneNumber, "Chào bạn, mã xác thực đăng nhập Natrumax của bạn là " + randomOtp, 5, senderId);
            System.out.println(result);
            if (result.contains("error")) {
                return false;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }


    public Optional<User> getUserById(Long id){
        return userRepository.findById(id);
    }

    @Override
    public User findUserByPhoneNumber(String phoneNumber) {
        return userRepository.findByPhoneNumber(phoneNumber).orElse(null);
    }

    @Override
    public User findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public List<UserResponse> getAllUser() {
        return userRepository.findAll().stream()
                .map(user -> {
                    UserResponse response = new UserResponse();
                    response.setId(user.getId());
                    response.setAccountName(user.getAccountName()); // or getName()
                    response.setEmail(user.getEmail());
                    response.setPhoneNumber(user.getPhoneNumber());
                    response.setAddress(user.getAddress());
                    response.setDetail(user.getDetail());
                    response.setRole(user.getRole().getName().name());
                    response.setCreateDate(user.getCreateDate());
                    response.setModifyDate(user.getModifyDate());
                    response.setStatus(user.isStatus());
                    return response;
                })
                .collect(Collectors.toList());
    }

    public Page<User> getAllUserByPaging(int page, int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        return userRepository.findAll(pageable);
    }

    public User ToggleUserActiveStatus(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new RuntimeException("User not found"));
        user.setStatus(!user.isStatus());
        return userRepository.save(user);
    }

//    public User createUser(CreateUserRequest request) {
//        // Check for existing phone number
//        if (userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
//            throw new RuntimeException("Phone number already exists.");
//        }
//
//        // Check for existing account name
//        if (userRepository.existsByAccountName(request.getAccountName())) {
//            throw new RuntimeException("Account name already exists.");
//        }
//
//        // Fetch the role
//        Role role = roleRepository.findById(request.getRoleId())
//                .orElseThrow(() -> new RuntimeException("Role not found"));
//
//        Province province = provinceRepository.findById(request.getProvinceId())
//                .orElseThrow(() -> new RuntimeException("Province not found"));
//
//        // Create new user
//        User user = new User();
//        user.setAccountName(request.getAccountName());
//        user.setEmail(request.getEmail());
//        user.setPassword(passwordEncoder.encode(request.getPassword())); // Encrypt password
//        user.setPhoneNumber(request.getPhoneNumber());
//        user.setAddress(request.getAddress());
//        user.setCreateDate(LocalDateTime.now());
//        user.setStatus(true);
//        user.setRole(role);
//        user.setProvince(province);
//
//        // Generate JSON based on role
//        String detailJson = "{}"; // Default empty JSON
//
//        switch (role.getName()) {
//            case ROLE_ADMIN:
//            case ROLE_CUSTOMER:
//                detailJson = "{}"; // Admin & Customer have empty detail
//                break;
//            case ROLE_ACCOUNTANT:
//                detailJson = String.format("{\"warehouse_id\": %d}", request.getWarehouseId());
//                break;
//            case ROLE_DISTRIBUTOR:
//                detailJson = String.format(
//                        "{\"warehouse_id\": %d, \"misa_code\": \"%s\"}",
//                        request.getWarehouseId(), request.getMisaCode()
//                );
//                break;
//            case ROLE_BRANCH_OWNER:
//                detailJson = String.format(
//                        "{\"retailer\": \"%s\", \"client_id\": \"%s\", \"client_secret\": \"%s\", \"misa_code\": \"%s\", \"warehouse_id\": \"%s\"}",
//                        request.getRetailer(), request.getClientId(), request.getClientSecret(), request.getMisaCode(), request.getWarehouseId()
//                );
//                break;
//            default:
//                throw new RuntimeException("Unsupported role");
//        }
//
//        user.setDetail(detailJson);
//        return userRepository.save(user);
//    }

    @Transactional
    public User createUserFromMisa(CreateUserRequest request) {
        MisaCustomer customer = misaService.getMisaCustomerByCode(request.getMisaCode());

        if (customer == null) {
            throw new RuntimeException("Customer not found from MISA");
        }

        if (userRepository.existsByPhoneNumber(customer.getPhoneNumber())) {
            throw new RuntimeException("User with this phone number already exists.");
        }

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        // Lấy warehouse trong DB
        Warehouse warehouse = warehouseRepository.findById(request.getWarehouseId())
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        Province province = provinceRepository.findByProvinceCode(customer.getCustomerGroup()); // customer_group = provinceCode

        if (province == null) {
            throw new RuntimeException("Province not found with code: " + customer.getCustomerGroup());
        }

        User user = new User();
        user.setAccountName(customer.getCustomerName());
        user.setPhoneNumber(customer.getPhoneNumber());
        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> detailMap = new HashMap<>();
        detailMap.put("misa_code", request.getMisaCode());

        try {
            String detailJson = objectMapper.writeValueAsString(detailMap);
            user.setDetail(detailJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error while setting detail JSON", e);
        }
        user.setPassword(passwordEncoder.encode("12345678"));
        user.setAddress(customer.getAddress());
        user.setProvince(province);
        user.setRole(role);
        user.setStatus(true);
        user.setCreateDate(LocalDateTime.now());

        user = userRepository.save(user);

        // Liên kết tất cả Product trong DB với User mới
        List<Product> allProducts = productRepository.findAll();

        List<UserProduct> userProducts = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();

        for (Product product : allProducts) {
            UserProduct userProduct = new UserProduct();
            userProduct.setUser(user);
            userProduct.setProduct(product);
            userProduct.setCreateDate(now);
            userProduct.setModifyDate(now);
            userProducts.add(userProduct);
        }

        userProductRepository.saveAll(userProducts);

        // Tạo Wallet cho user
        Wallet wallet = new Wallet();
        wallet.setUser(user);
        wallet.setBalance(Double.parseDouble(customer.getLiability())*(-1));
        wallet.setCurrency("VND");
        wallet.setCreateDate(LocalDateTime.now());
        wallet.setModifyDate(LocalDateTime.now());
        walletRepository.save(wallet);

        // Gán lại vào user
        user.setWallet(wallet);

        // Tạo Promotion cho user
        Promotion promotion = new Promotion();
        promotion.setUser(user);
        promotion.setSameProduct(true);
        promotion.setQuantityToGetPromotion(0);
        promotion.setBonusQuantity(0);
        promotionRepository.save(promotion);

        // Gán lại vào user
        user.setPromotion(promotion);

        // Gọi MISA API lấy danh sách kho
        List<MisaWarehouse> misaWarehouses = misaApiClient.getAllMisaWarehouses().getData(); // .getData() từ response

        // Tìm MISA warehouse trùng với warehouse_id trong customer
        Optional<MisaWarehouse> matchedMisaWarehouse = misaWarehouses.stream()
                .filter(mw -> mw.getId().equals(customer.getWarehouseId()))
                .findFirst();

        if (matchedMisaWarehouse.isEmpty()) {
            throw new RuntimeException("No matching MISA warehouse found for customer");
        }

        // So sánh accessCode trong DB với clientSecret của MISA
        String misaClientSecret = matchedMisaWarehouse.get().getClientSecret();
        if (!warehouse.getAccessCode().equals(misaClientSecret)) {
            throw new RuntimeException("Access code mismatch between local warehouse and MISA warehouse");
        }

        // Nếu trùng thì tạo UserWarehouse như bình thường
        UserWarehouse userWarehouse = new UserWarehouse();
        userWarehouse.setUser(user);
        userWarehouse.setWarehouse(warehouse);
        userWarehouse.setRoleInWarehouse("Member");
        userWarehouse.setCreateDate(LocalDateTime.now());

        userWarehouseRepository.save(userWarehouse);

        return user;
    }

    public User updateUser(Long id, UpdateUserRequest request) {
        Optional<User> optionalUser = userRepository.findById(id);

        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found with ID: " + id);
        }

        User user = optionalUser.get();

        // Kiểm tra nếu số điện thoại đã tồn tại nhưng không phải của user hiện tại
        if (!user.getPhoneNumber().equals(request.getPhoneNumber()) &&
                userRepository.existsByPhoneNumber(request.getPhoneNumber())) {
            throw new RuntimeException("Phone number already exists.");
        }

        // Kiểm tra nếu username đã tồn tại nhưng không phải của user hiện tại
        if (!user.getAccountName().equals(request.getAccountName()) &&
                userRepository.existsByAccountName(request.getAccountName())) {
            throw new RuntimeException("Account name already exists.");
        }

        Role role = roleRepository.findById(request.getRoleId())
                .orElseThrow(() -> new RuntimeException("Role not found"));

        Province province = provinceRepository.findById(request.getProvinceId())
                .orElseThrow(() -> new RuntimeException("Province not found"));

        user.setPhoneNumber(request.getPhoneNumber());
        user.setAccountName(request.getAccountName());
        user.setEmail(request.getEmail());
        user.setAddress(request.getAddress());
        user.setModifyDate(LocalDateTime.now());
        user.setRole(role);
        user.setProvince(province);

        ObjectMapper objectMapper = new ObjectMapper();

        Map<String, Object> detailMap = new HashMap<>();
        try {
            if (user.getDetail() != null && !user.getDetail().isEmpty()) {
                detailMap = objectMapper.readValue(user.getDetail(), new TypeReference<>() {});
            }
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to parse existing detail JSON", e);
        }

        Object misaCode = detailMap.get("misa_code");

        switch (user.getRole().getName()) {
            case ROLE_ADMIN:
            case ROLE_CUSTOMER:
            case ROLE_ACCOUNTANT:
                detailMap.clear();
                break;
            case ROLE_DISTRIBUTOR:
                detailMap.clear();
                if (misaCode != null) {
                    detailMap.put("misa_code", misaCode);
                }
                break;
            case ROLE_BRANCH_OWNER:
                detailMap.put("retailer", request.getRetailer());
                detailMap.put("client_id", request.getClientId());
                detailMap.put("client_secret", request.getClientSecret());
                break;
            default:
                throw new RuntimeException("Unsupported role");
        }

        try {
            String updatedDetailJson = objectMapper.writeValueAsString(detailMap);
            user.setDetail(updatedDetailJson);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Failed to serialize detail JSON", e);
        }

        return userRepository.save(user);
    }

    public void changePassword(Long userId, ChangePasswordRequest request) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if (optionalUser.isEmpty()) {
            throw new RuntimeException("User not found");
        }

        User user = optionalUser.get();

        // Check if old password matches the stored password
        if (!passwordEncoder.matches(request.getOldPassword(), user.getPassword())) {
            throw new RuntimeException("Old password is incorrect");
        }

        // Check if new password matches confirm password
        if (!request.getNewPassword().equals(request.getConfirmPassword())) {
            throw new RuntimeException("New password and confirm password do not match");
        }

        // Encode and save the new password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
        userRepository.save(user);
    }


    @Override
    public List<ReferrerResponse> getReferrerList() {
        List<User> referrers = userRepository.findDistinctReferrers();
        return referrers.stream().map(user -> new ReferrerResponse(
                user.getId(),
                user.getAccountName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getRole().getName().name(),
                user.getAddress(),
                user.getProvince().getName()
        )).collect(Collectors.toList());
    }

    public List<User> getUsersByRole(ERole role) {
        return userRepository.findByRole_Name(role);
    }
}
