package com.natrumax.services;

import com.cloudinary.utils.StringUtils;
import com.natrumax.dto.request.CreateRewardRequest;
import com.natrumax.dto.request.UpdateRewardRequest;
import com.natrumax.dto.response.CloudinaryResponse;
import com.natrumax.models.Reward;
import com.natrumax.models.User;
import com.natrumax.repository.RewardRepository;
import com.natrumax.services.interfaces.IRewardService;
import com.natrumax.utils.FileUploadUtil;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RewardService implements IRewardService {
    @Autowired
    private RewardRepository rewardRepository;

    @Autowired
    private CloudinaryService cloudinaryService;
    public Optional<Reward> getRewardById(Long id){
        return rewardRepository.findById(id);
    }

    @Transactional
    public Reward createReward(CreateRewardRequest request, MultipartFile file) {
        FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);
        final String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());
        final CloudinaryResponse response = cloudinaryService.uploadFile(file, fileName);

        Reward reward = new Reward();
        reward.setName(request.getName());
        reward.setDescription(request.getDescription());
        reward.setImage(response.getUrl());
        reward.setCreateDate(LocalDate.now());
        reward.setStatus(true);

        return rewardRepository.save(reward);
    }

    public List<Reward> getAllRewards() {
        return rewardRepository.findAll();
    }

    @Transactional
    public Reward updateReward(Long id, UpdateRewardRequest request, MultipartFile file) {
        Reward reward = rewardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reward not found"));

        reward.setName(request.getName());
        reward.setDescription(request.getDescription());
        reward.setStatus(request.isStatus());
        reward.setModifyDate(LocalDate.now());

        if (file != null && !file.isEmpty()) {
            FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);

            // Xoá ảnh cũ nếu có
            String oldImageId = reward.getCloudinaryImageId();
            if (StringUtils.isNotBlank(oldImageId)) {
                cloudinaryService.deleteFile(oldImageId);
            }

            // Upload ảnh mới
            String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());
            CloudinaryResponse response = cloudinaryService.uploadFile(file, fileName);

            reward.setImage(response.getUrl());
            reward.setCloudinaryImageId(response.getPublicId());
        }

        return rewardRepository.save(reward);
    }

    public Reward toggleRewardStatus(Long id) {
        Reward reward = rewardRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Reward not found"));

        // Toggle the status
        reward.setStatus(!reward.isStatus());
        reward.setModifyDate(java.time.LocalDate.now());

        return rewardRepository.save(reward);
    }
}
