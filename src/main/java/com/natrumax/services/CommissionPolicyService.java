package com.natrumax.services;

import com.natrumax.dto.request.CreateCommissionPolicyRequest;
import com.natrumax.dto.request.UpdateCommissionPolicyRequest;
import com.natrumax.dto.response.CommissionPolicyShortResponse;
import com.natrumax.models.Category;
import com.natrumax.models.Commission;
import com.natrumax.models.CommissionPolicies;
import com.natrumax.repository.CategoryRepository;
import com.natrumax.repository.CommissionPolicyRepository;
import com.natrumax.repository.CommissionRepository;
import com.natrumax.services.interfaces.ICommissionPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommissionPolicyService implements ICommissionPolicyService {

    @Autowired
    private CommissionPolicyRepository commissionPolicyRepository;

    @Autowired
    private CommissionRepository commissionRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    public CommissionPolicies createCommissionPolicy(CreateCommissionPolicyRequest request) {
        Commission commission = commissionRepository.findById(request.getCommissionId())
                .orElseThrow(() -> new RuntimeException("Commission not found"));

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category not found"));

        CommissionPolicies policy = new CommissionPolicies();
        policy.setPercentage(request.getPercentage());
        policy.setCreateDate(LocalDate.now());
        policy.setCommission(commission);
        policy.setCategory(category);

        return commissionPolicyRepository.save(policy);
    }

    public CommissionPolicies updateCommissionPolicy(Long id, UpdateCommissionPolicyRequest request) {
        CommissionPolicies policy = commissionPolicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commission policy not found"));

        policy.setPercentage(request.getPercentage());
        policy.setModifyDate(LocalDate.now());

        return commissionPolicyRepository.save(policy);
    }

    public CommissionPolicyShortResponse getCommissionPolicyById(Long id) {
        CommissionPolicies policy = commissionPolicyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commission policy not found"));

        return new CommissionPolicyShortResponse(policy);
    }

    public List<CommissionPolicyShortResponse> getAllCommissionPolicies() {
        List<CommissionPolicies> policies = commissionPolicyRepository.findAll();
        return policies.stream().map(CommissionPolicyShortResponse::new).collect(Collectors.toList());
    }
}
