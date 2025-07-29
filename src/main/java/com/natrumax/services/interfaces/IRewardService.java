package com.natrumax.services.interfaces;

import com.natrumax.dto.request.CreateRewardRequest;
import com.natrumax.dto.request.UpdateRewardRequest;
import com.natrumax.models.Reward;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IRewardService {
    Reward createReward(CreateRewardRequest request, MultipartFile file);
    List<Reward> getAllRewards();
    Reward updateReward(Long id, UpdateRewardRequest request, MultipartFile file);
    Reward toggleRewardStatus(Long id);
}
