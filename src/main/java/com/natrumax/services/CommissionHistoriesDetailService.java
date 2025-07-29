package com.natrumax.services;

import com.natrumax.dto.request.CommissionHistoriesDetailCreateRequest;
import com.natrumax.dto.request.CommissionHistoriesDetailUpdateRequest;
import com.natrumax.models.Category;
import com.natrumax.models.CommissionHistories;
import com.natrumax.models.CommissionHistoriesDetail;
import com.natrumax.models.CommissionPolicies;
import com.natrumax.repository.CategoryRepository;
import com.natrumax.repository.CommissionHistoriesDetailRepository;
import com.natrumax.repository.CommissionHistoryRepository;
import com.natrumax.repository.CommissionPolicyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommissionHistoriesDetailService {

    @Autowired
    private CommissionHistoriesDetailRepository repository;

    @Autowired
    private CommissionHistoryRepository commissionHistoryRepository;

    @Autowired
    private CommissionPolicyRepository commissionPoliciesRepository;

    @Autowired
    private CategoryRepository categoriesRepository;

    public List<CommissionHistoriesDetail> getAll() {
        return repository.findAll();
    }

    public Optional<CommissionHistoriesDetail> getById(Long id) {
        return repository.findById(id);
    }

    public CommissionHistoriesDetail create(CommissionHistoriesDetailCreateRequest request) {
        CommissionHistoriesDetail detail = new CommissionHistoriesDetail();
        detail.setPercentage(request.getPercentage());
        detail.setAmount(request.getAmount());
        detail.setCreateDate(request.getCreateDate());

        // Fetch related entities
        CommissionHistories commissionHistory = commissionHistoryRepository.findById(request.getCommissionHistoryId())
                .orElseThrow(() -> new RuntimeException("CommissionHistory not found"));

        CommissionPolicies commissionPolicy = commissionPoliciesRepository.findById(request.getCommissionPolicyId())
                .orElseThrow(() -> new RuntimeException("CommissionPolicy not found"));

        detail.setCommissionHistory(commissionHistory);
        detail.setCommissionPolicy(commissionPolicy);

        return repository.save(detail);
    }

    public CommissionHistoriesDetail update(Long id, CommissionHistoriesDetailUpdateRequest request) {
        return repository.findById(id).map(existingDetail -> {
            existingDetail.setCategoryName(request.getCategoryName());
            existingDetail.setPercentage(request.getPercentage());
            existingDetail.setAmount(request.getAmount());
            existingDetail.setModifyDate(request.getModifyDate());
            return repository.save(existingDetail);
        }).orElseThrow(() -> new RuntimeException("CommissionHistoriesDetail not found"));
    }
}
