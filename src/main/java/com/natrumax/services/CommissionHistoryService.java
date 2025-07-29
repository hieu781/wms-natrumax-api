package com.natrumax.services;

import com.natrumax.dto.request.CreateCommissionHistoryRequest;
import com.natrumax.dto.request.UpdateCommissionHistoryRequest;
import com.natrumax.dto.response.CommissionHistoriesResponse;
import com.natrumax.dto.response.CommissionHistoryShortResponse;
import com.natrumax.models.Commission;
import com.natrumax.models.CommissionHistories;
import com.natrumax.models.CommissionPolicies;
import com.natrumax.models.User;
import com.natrumax.repository.CommissionHistoryRepository;
import com.natrumax.repository.CommissionPolicyRepository;
import com.natrumax.services.interfaces.ICommissionHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommissionHistoryService implements ICommissionHistoryService {
    @Autowired
    private CommissionHistoryRepository commissionHistoryRepository;

    @Autowired
    private CommissionPolicyRepository commissionPolicyRepository;

    public CommissionHistories createCommissionHistory(CreateCommissionHistoryRequest request) {
        CommissionPolicies policy = commissionPolicyRepository.findById(request.getCommissionPolicyId())
                .orElseThrow(() -> new RuntimeException("Commission policy not found"));

        CommissionHistories history = new CommissionHistories();
        history.setTotalAmount(request.getAmount());
        history.setCreateDate(LocalDate.now());
//        history.setCommissionPolicy(policy);

        return commissionHistoryRepository.save(history);
    }

    public CommissionHistories updateCommissionHistory(Long id, UpdateCommissionHistoryRequest request) {
        CommissionHistories history = commissionHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commission history not found"));

        history.setTotalAmount(request.getAmount());
        history.setModifyDate(LocalDate.now());

        return commissionHistoryRepository.save(history);
    }

    public CommissionHistoryShortResponse getCommissionHistoryById(Long id) {
        CommissionHistories history = commissionHistoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commission history not found"));

        return new CommissionHistoryShortResponse(history);
    }

    public List<CommissionHistoryShortResponse> getAllCommissionHistories() {
        List<CommissionHistories> histories = commissionHistoryRepository.findAll();
        return histories.stream().map(CommissionHistoryShortResponse::new).collect(Collectors.toList());
    }

    public List<CommissionHistories> getCommissionHistoriesByUserId(Long userId) {
        return commissionHistoryRepository.findByCommissionReferrerId(userId);
    }

    public CommissionHistoriesResponse getCommissionListByReferrerIdAndMonthAndYear(Long referrerId, int month, int year) {
        List<CommissionHistories> histories = commissionHistoryRepository
                .getCommissionListByReferrerIdAndMonthAndYear(referrerId, month, year);

        if (histories.isEmpty()) {
            throw new IllegalArgumentException("No commission history found.");
        }

        CommissionHistoriesResponse response = new CommissionHistoriesResponse();
        CommissionHistoriesResponse.UserDTO referrerDto = mapUser(histories.get(0).getCommission().getReferrer());
        response.setReferrer(referrerDto);

        List<CommissionHistoriesResponse.CommissionDTO> commissionDTOs = histories.stream().map(ch -> {
            Commission commission = ch.getCommission();
            CommissionHistoriesResponse.CommissionDTO dto = new CommissionHistoriesResponse.CommissionDTO();
            dto.setCommissionId(commission.getCommissionId());
            dto.setReferral(mapUser(commission.getReferral()));

            CommissionHistoriesResponse.CommissionHistoryDTO historyDTO = new CommissionHistoriesResponse.CommissionHistoryDTO();
            historyDTO.setCommissionHistoryId(ch.getCommissionHistoriesId());
            historyDTO.setMonth(ch.getMonth());
            historyDTO.setYear(ch.getYear());
            historyDTO.setTotalAmount(ch.getTotalAmount());

            List<CommissionHistoriesResponse.CommissionHistoryDetailDTO> detailDTOs = ch.getCommissionHistoriesDetails().stream().map(detail -> {
                CommissionHistoriesResponse.CommissionHistoryDetailDTO d = new CommissionHistoriesResponse.CommissionHistoryDetailDTO();
                d.setCommissionHistoryDetailId(detail.getCommissionHistoriesId());
                d.setCategoryName(detail.getCommissionPolicy().getCategory().getCategoryName());
                d.setPercentage(detail.getCommissionPolicy().getPercentage());
                d.setAmount(detail.getAmount());
                return d;
            }).collect(Collectors.toList());

            historyDTO.setDetails(detailDTOs);
            dto.setCommissionHistory(historyDTO);
            return dto;
        }).collect(Collectors.toList());

        response.setCommissions(commissionDTOs);
        return response;
    }

    private CommissionHistoriesResponse.UserDTO mapUser(User user) {
        CommissionHistoriesResponse.UserDTO dto = new CommissionHistoriesResponse.UserDTO();
        dto.setUserId(user.getId());
        dto.setAccountName(user.getAccountName());
        dto.setRole(user.getRole().getName().name());
        dto.setProvince(user.getProvince().getName());
        return dto;
    }

}
