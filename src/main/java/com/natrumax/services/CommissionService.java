package com.natrumax.services;

import com.natrumax.config.KiotVietApiClient;
import com.natrumax.config.MisaApiClient;
import com.natrumax.dto.request.*;
import com.natrumax.dto.response.*;
import com.natrumax.models.*;
import com.natrumax.models.Enum.EOrderStatus;
import com.natrumax.models.Enum.ERole;
import com.natrumax.models.MockAPI.KiotVietProduct;
import com.natrumax.models.MockAPI.MisaGood;
import com.natrumax.repository.*;
import com.natrumax.services.APIService.MisaService;
import com.natrumax.services.interfaces.ICommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CommissionService implements ICommissionService {
    @Autowired
    private CommissionRepository commissionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CommissionHistoryRepository commissionHistoriesRepository;

    @Autowired
    private CommissionHistoriesDetailRepository commissionHistoriesDetailRepository;

    @Autowired
    private CommissionPolicyRepository commissionPoliciesRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private UserProductRepository userProductRepository;

    @Autowired
    private MisaApiClient misaApiClient;

    @Autowired
    private KiotVietApiClient kiotVietApiClient;

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private MisaService misaService;

    public Commission updateCommission(int id, UpdateCommissionRequest request) {
        Commission commission = commissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commission not found"));

        commission.setModifyDate(LocalDate.now());

        return commissionRepository.save(commission);
    }

    public CommissionShortResponse getCommissionById(int id) {
        Commission commission = commissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commission not found"));

        return new CommissionShortResponse(commission);
    }

    public List<CommissionShortResponse> getAllCommissions() {
        List<Commission> commissions = commissionRepository.findAll();
        return commissions.stream().map(CommissionShortResponse::new).collect(Collectors.toList());
    }

    public void processCommission(CommissionRequest request) {
        User referrer = userRepository.findById(request.getReferrerId())
                .orElseThrow(() -> new RuntimeException("Referrer not found"));

        // Lấy chính sách hoa hồng hiện tại
        CommissionPolicies policy = commissionPoliciesRepository.findActivePolicy();

        // Tính toán số tiền hoa hồng
        double commissionAmount = (request.getTransactionAmount() * policy.getPercentage()) / 100;

        // Lưu vào bảng CommissionHistories
        CommissionHistories history = new CommissionHistories();
        history.setTotalAmount(commissionAmount);
        history.setMonth(LocalDate.now().getMonthValue());
        history.setYear(LocalDate.now().getYear());
        history.setCreateDate(LocalDate.now());
        commissionHistoriesRepository.save(history);


        // Lưu chi tiết hoa hồng vào bảng CommissionHistoriesDetail
        CommissionHistoriesDetail detail = new CommissionHistoriesDetail();
        detail.setPercentage(policy.getPercentage());
        detail.setAmount(commissionAmount);
        detail.setCreateDate(LocalDateTime.now());
        detail.setCommissionHistory(history);
        commissionHistoriesDetailRepository.save(detail);
    }

    @Override
    public CommissionResponse getCommissionListByReferrerId(Long referrerId) {
        List<Commission> commissions = commissionRepository.getCommissionListByReferrerId(referrerId);

        if (commissions.isEmpty()) {
            throw new IllegalArgumentException("User don't have any");
        }

        User referrer = commissions.get(0).getReferrer();
        UserShortResponse referrerDto = new UserShortResponse(
                referrer.getId(),
                referrer.getAccountName(),
                referrer.getRole().getName().name(),
                referrer.getProvince().getName()
        );

        List<CommissionDetailResponse> detailResponses = commissions.stream().map(commission -> {
            User referral = commission.getReferral();
            UserShortResponse referralDto = new UserShortResponse(
                    referral.getId(),
                    referral.getAccountName(),
                    referral.getRole().getName().name(),
                    referral.getProvince().getName()
            );

            List<CommissionPolicyResponse> policyDtos = commission.getCommissionPolicies().stream().map(policy ->
                    new CommissionPolicyResponse(
                            policy.getCommissionPoliciesId(),
                            policy.getCategory().getCategoryName(),
                            policy.getPercentage()
                    )
            ).collect(Collectors.toList());

            return new CommissionDetailResponse(
                    commission.getCommissionId(),
                    referralDto,
                    policyDtos
            );
        }).collect(Collectors.toList());

        return new CommissionResponse(referrerDto, detailResponses);
    }


    public Commission createCommissions(Long referrerId, Long referralId) {
        User referrer = userRepository.findById(referrerId)
                .orElseThrow(() -> new RuntimeException("Referrer not found"));
        User referral = userRepository.findById(referralId)
                .orElseThrow(() -> new RuntimeException("Referral not found"));

        Optional<Commission> existingCommission = commissionRepository
                .findByReferrerAndReferral(referrer, referral);
        if (existingCommission.isPresent()) {
            throw new RuntimeException("Commission between these users already exists");
        }

        Commission commission = new Commission();
        commission.setReferrer(referrer);
        commission.setReferral(referral);
        commission.setCreateDate(LocalDate.now());
        return commissionRepository.save(commission);
    }

    public void createCommissionPolicies(int commissionId, List<CommissionPoliciesRequest.PolicyRequest> policies) {
        Commission commission = commissionRepository.findById(commissionId)
                .orElseThrow(() -> new RuntimeException("Commission not found"));

        for (CommissionPoliciesRequest.PolicyRequest req : policies) {
            Category category = categoryRepository.findById(req.getCategoryId())
                    .orElseThrow(() -> new RuntimeException("Category not found"));

            CommissionPolicies policy = new CommissionPolicies();
            policy.setCommission(commission);
            policy.setCategory(category);
            policy.setCreateDate(LocalDate.now());
            policy.setPercentage(req.getPercentage());

            commissionPoliciesRepository.save(policy);
        }
    }

    public void updateCommissionPolicies(UpdateCommissionPoliciesRequest request) {
        for (CommissionDetailRequest detail : request.getCommissionDetails()) {
            CommissionPolicies policy = commissionPoliciesRepository.findById(detail.getCommissionPolicyId())
                    .orElseThrow(() -> new RuntimeException("Policy not found"));

            policy.setPercentage(detail.getPercentage());
            policy.setModifyDate(LocalDate.now());

            commissionPoliciesRepository.save(policy);
        }
    }

    @Override
    public void createReport(int month, int year) {
        List<UserProduct> userProductList = userProductRepository.findAll();

        for (UserProduct userProduct : userProductList) {
            int quantityImported = 0;
            int endingInventory = 0;

            if (userProduct.getUser().getRole().equals(ERole.ROLE_DISTRIBUTOR)) {
                MisaGood good = misaService.getGoodByCode(userProduct.getProduct().getMisaCode());
                if (good != null) {
                    endingInventory = Integer.parseInt(good.getInventory_account());
                }
            } else if (userProduct.getUser().getRole().equals(ERole.ROLE_BRANCH_OWNER)) {
                KiotVietProduct product = kiotVietApiClient.getKiotVietProductByBarCode(userProduct.getProduct().getBarcode()).getBody().getData().get(0);
                if (product != null && !product.getInventories().isEmpty()) {
                    endingInventory = product.getInventories().get(0).getOnHand();
                }
            }

            List<OrderDetail> orderDetails = orderDetailRepository.findByUserProductAndMonthAndYear(
                    userProduct.getUser().getId(),
                    userProduct.getProduct().getProductId(),
                    month,
                    year
            );

            for (OrderDetail orderDetail : orderDetails) {
                if (orderDetail.getOrder().getStatus() == EOrderStatus.DELIVERED) {
                    quantityImported += orderDetail.getQuantity();
                }
            }

            Optional<Report> existingReportOpt = reportRepository
                    .findByUserProduct_User_IdAndUserProduct_Product_ProductIdAndMonthAndYear(
                            userProduct.getUser().getId(),
                            userProduct.getProduct().getProductId(),
                            month,
                            year
                    );

            Report report = existingReportOpt.orElse(new Report());
            report.setUserProduct(userProduct);
            report.setQuantityImported(quantityImported);
            report.setEndingInventory(endingInventory);
            report.setMonth(month);
            report.setYear(year);
            report.setModifyDate(LocalDateTime.now());
            if (report.getCreateDate() == null) {
                report.setCreateDate(LocalDateTime.now());
            }

            reportRepository.save(report);
        }
    }

    @Override
    public void createCommissionHistory(int month, int year) {
        List<Commission> commissions = commissionRepository.findAll();

        for (Commission commission : commissions) {
            CommissionHistories history = commissionHistoriesRepository
                    .findByCommission_CommissionIdAndMonthAndYear(
                            commission.getCommissionId(), month, year)
                    .orElseGet(() -> {
                        CommissionHistories newHistory = new CommissionHistories();
                        newHistory.setCommission(commission);
                        newHistory.setMonth(month);
                        newHistory.setYear(year);
                        newHistory.setCreateDate(LocalDate.now());
                        return newHistory;
                    });

            // Clear details nếu đã tồn tại để cập nhật lại
            if (history.getCommissionHistoriesDetails() != null) {
                history.getCommissionHistoriesDetails().clear();
            } else {
                history.setCommissionHistoriesDetails(new ArrayList<>());
            }

            double totalAmount = 0;

            List<Category> categories = categoryRepository.findAll();
            for (Category category : categories) {
                List<Product> products = productRepository.findByCategoryCategoryId(category.getCategoryId());

                double categoryAmount = 0;

                for (Product product : products) {
                    Report report = reportRepository.findByUserProductAndMonthAndYear(
                            commission.getReferral().getId(),
                            product.getProductId(),
                            month,
                            year
                    ).orElse(null);

                    if (report != null) {
                        double price = product.getBasePrice();
                        double discount = product.getDiscount();
                        categoryAmount += Math.round(report.getQuantityImported() * price * (1 - discount / 100));
                    }
                }

                CommissionPolicies policy = commissionPoliciesRepository.findByCommissionCommissionIdAndCategoryCategoryId(
                        commission.getCommissionId(),
                        category.getCategoryId()
                ).orElse(null);

                if (policy != null) {
                    CommissionHistoriesDetail detail = new CommissionHistoriesDetail();
                    detail.setCommissionHistory(history);
                    detail.setCommissionPolicy(policy);
                    detail.setCategoryName(category.getCategoryName());
                    detail.setPercentage(policy.getPercentage());
                    detail.setAmount(categoryAmount);
                    detail.setCreateDate(LocalDateTime.now());

                    // Add vào list, KHÔNG dùng save riêng lẻ
                    history.getCommissionHistoriesDetails().add(detail);

                    totalAmount += (categoryAmount * (policy.getPercentage() / 100));
                }
            }

            history.setTotalAmount(totalAmount);
            history.setModifyDate(LocalDate.now());

            // Save lại history kèm detail (cascade)
            commissionHistoriesRepository.save(history);
        }
    }
}
