package com.natrumax.services;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.natrumax.config.MisaApiClient;
import com.natrumax.dto.request.CreateWalletRequest;
import com.natrumax.dto.request.UpdateWalletRequest;
import com.natrumax.models.MockAPI.MisaCustomer;
import com.natrumax.models.MockAPI.response.MisaCustomerResponse;
import com.natrumax.models.User;
import com.natrumax.models.Wallet;
import com.natrumax.repository.UserRepository;
import com.natrumax.repository.WalletRepository;
import com.natrumax.services.interfaces.IWalletService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class WalletService implements IWalletService {

    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private MisaApiClient misaApiClient;
    private final ObjectMapper objectMapper = new ObjectMapper();

    public List<Wallet> getAllWallets() {
        return walletRepository.findAll();
    }

    public Wallet getWalletById(int id) {
        return walletRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found with id: " + id));
    }

    public Wallet getWalletByUserId(Long userId) {
        return walletRepository.findByUserId(userId)
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found for user id: " + userId));
    }

    public Wallet createWallet(CreateWalletRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + request.getUserId()));

        Wallet wallet = new Wallet();
        wallet.setBalance(request.getBalance());
        wallet.setCurrency(request.getCurrency());
        wallet.setCreateDate(LocalDateTime.now());
        wallet.setUser(user);

        return walletRepository.save(wallet);
    }

    public Wallet updateWallet(int id, UpdateWalletRequest request) {
        Wallet wallet = walletRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found with id: " + id));

        if (request.getBalance() != null) {
            wallet.setBalance(request.getBalance());
        }
        wallet.setModifyDate(LocalDateTime.now());

        return walletRepository.save(wallet);
    }

    public void transferBetweenUsers(User sender, User receiver, double amount) {
        Wallet senderWallet = sender.getWallet();
        Wallet receiverWallet = receiver.getWallet();

        senderWallet.setBalance(senderWallet.getBalance() + amount);
        receiverWallet.setBalance(receiverWallet.getBalance() + amount);

        walletRepository.save(senderWallet);
        walletRepository.save(receiverWallet);
    }

    @Transactional
    public void updateWalletBalanceFromMisa(User member) {
        try {
            // Parse misa_code từ detail JSON
            JsonNode detailJson = objectMapper.readTree(member.getDetail());
            String misaCode = detailJson.path("misa_code").asText();

            if (misaCode == null || misaCode.isEmpty()) {
                throw new RuntimeException("Missing misa_code in user detail");
            }

            // Gọi API để lấy MisaCustomer
            MisaCustomerResponse response = misaApiClient.getMisaCustomerByCustomerId(misaCode);
            if (response == null || response.getData() == null || response.getData().isEmpty()) {
                throw new RuntimeException("Misa customer not found for misa_code: " + misaCode);
            }

            MisaCustomer misaCustomer = response.getData().get(0);

            // Parse liabilities
            double liabilities = 0;
            try {
                liabilities = Double.parseDouble(misaCustomer.getLiability())*(-1);
            } catch (NumberFormatException e) {
                throw new RuntimeException("Invalid liabilities value from Misa API: " + misaCustomer.getLiability(), e);
            }

            // Update balance trong Wallet
            Wallet wallet = member.getWallet();
            if (wallet == null) {
                throw new RuntimeException("User does not have a wallet");
            }
            wallet.setBalance(liabilities);
            wallet.setModifyDate(LocalDateTime.now());

            // Nếu bạn có walletRepository thì nhớ save lại
            walletRepository.save(wallet);

        } catch (Exception e) {
            throw new RuntimeException("Failed to update wallet balance from Misa", e);
        }
    }
}
