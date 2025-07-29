package com.natrumax.services;

import com.natrumax.dto.request.CreateLotteryCodeRequest;
import com.natrumax.dto.request.UpdateLotteryCodeRequest;
import com.natrumax.models.*;
import com.natrumax.models.Enum.ERole;
import com.natrumax.repository.*;
import com.natrumax.services.interfaces.ILotteryCodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class LotteryCodeService implements ILotteryCodeService {
    @Autowired
    private LotteryCodeRepository lotteryCodeRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProvinceRepository provinceRepository;

    @Autowired
    private RoleRepository roleRepository;

    public LotteryCode getLotteryCodeById(Integer id) {
        return lotteryCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("LotteryCode not found with id: " + id));
    }

    public List<LotteryCode> getAllLotteryCodes() {
        return lotteryCodeRepository.findAll();
    }

    public LotteryCode createLotteryCode(CreateLotteryCodeRequest request) {

        if (lotteryCodeRepository.existsByCode(request.getCode())) {
            throw new RuntimeException("Lottery code already exists");
        }

        LotteryCode lotteryCode = new LotteryCode();
        lotteryCode.setCode(request.getCode());
        lotteryCode.setPlace("");
        lotteryCode.setProductName("");
        lotteryCode.setCreateDate(LocalDate.now());
        lotteryCode.setStatus(true);

        // Check product
        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            lotteryCode.setProduct(product);
        }

        // Check reward
        if (request.getRewardId() != null) {
            Reward reward = rewardRepository.findById(request.getRewardId())
                    .orElseThrow(() -> new RuntimeException("Reward not found"));
            lotteryCode.setReward(reward);
        }

        // Check or create user
        if (request.getUserId() != null) {
            Optional<User> optionalUser = userRepository.findById(request.getUserId());
            User user;
            if (optionalUser.isPresent()) {
                user = optionalUser.get();
            } else {
                // Lấy Role từ DB thay vì new
                Role userRole = roleRepository.findByName(ERole.ROLE_CUSTOMER)
                        .orElseThrow(() -> new RuntimeException("Role not found"));

                user = new User();
                user.setAccountName(request.getAccountName());
                user.setStatus(true);
                user.setCreateDate(LocalDateTime.now());
                user.setRole(userRole);

                if (request.getProvinceId() != null) {
                    Province province = provinceRepository.findById(request.getProvinceId())
                            .orElseThrow(() -> new RuntimeException("Province not found"));
                    user.setProvince(province);
                }

                user = userRepository.save(user);
            }
            lotteryCode.setUser(user);
        }

        return lotteryCodeRepository.save(lotteryCode);
    }



    public LotteryCode updateLotteryCode(int id, UpdateLotteryCodeRequest request) {
        // Fetch the existing lottery code
        LotteryCode lotteryCode = lotteryCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lottery code not found"));

        // Update fields
        lotteryCode.setModifyDate(LocalDate.now());
        lotteryCode.setPrice(request.getPrice());
        lotteryCode.setProductName(request.getProductName());
        lotteryCode.setPlace(request.getPlace());

        // Fetch related entities if IDs are provided
        if (request.getProductId() != null) {
            Product product = productRepository.findById(request.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            lotteryCode.setProduct(product);
        }

        if (request.getRewardId() != null) {
            Reward reward = rewardRepository.findById(request.getRewardId())
                    .orElseThrow(() -> new RuntimeException("Reward not found"));
            lotteryCode.setReward(reward);
        }

        if (request.getUserId() != null) {
            User user = userRepository.findById(request.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            lotteryCode.setUser(user);
        }

        return lotteryCodeRepository.save(lotteryCode);
    }

    public List<LotteryCode> getLotteryCodesByUserId(Long userId) {
        return lotteryCodeRepository.findByUserId(userId);
    }

    public LotteryCode changeStatus(int id) {
        LotteryCode lotteryCode = lotteryCodeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lottery code not found"));
        lotteryCode.setStatus(!lotteryCode.isStatus());
        return lotteryCodeRepository.save(lotteryCode);
    }
}
