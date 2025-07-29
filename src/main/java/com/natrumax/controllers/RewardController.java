package com.natrumax.controllers;

import com.natrumax.dto.request.CreateRewardRequest;
import com.natrumax.dto.request.UpdateRewardRequest;
import com.natrumax.models.Reward;
import com.natrumax.services.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/v1/rewards")
public class RewardController {

    @Autowired
    private RewardService rewardService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Reward> createReward(
            @RequestPart("request") CreateRewardRequest request,
            @RequestPart("image") MultipartFile imageFile) {

        Reward newReward = rewardService.createReward(request, imageFile);
        return ResponseEntity.ok(newReward);
    }

    @GetMapping
    public ResponseEntity<List<Reward>> getAllRewards() {
        List<Reward> rewards = rewardService.getAllRewards();
        return ResponseEntity.ok(rewards);
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Reward> updateReward(
            @PathVariable Long id,
            @RequestPart("request") UpdateRewardRequest request,
            @RequestPart(value = "image", required = false) MultipartFile imageFile) {

        Reward updatedReward = rewardService.updateReward(id, request, imageFile);
        return ResponseEntity.ok(updatedReward);
    }

    @PutMapping("/{id}/change-status")
    public ResponseEntity<?> toggleStatus(@PathVariable Long id) {
        try {
            Reward reward = rewardService.toggleRewardStatus(id);
            return ResponseEntity.ok("Reward is " + (reward.isStatus() ? "activated" : "deactivated"));
        } catch (RuntimeException e) {
            return ResponseEntity.status(404).body(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getRewardById(@PathVariable Long id){
        return rewardService.getRewardById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(404).body("Reward not found"));
    }
}
