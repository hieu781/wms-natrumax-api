package com.natrumax.services;

import com.natrumax.dto.request.CreateBankRequest;
import com.natrumax.dto.request.UpdateBankRequest;
import com.natrumax.models.Banks;
import com.natrumax.models.User;
import com.natrumax.repository.BankRepository;
import com.natrumax.repository.UserRepository;
import com.natrumax.services.interfaces.IBankService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class BankService implements IBankService {
    @Autowired
    private BankRepository bankRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Banks getBankById(Long id) {
        return bankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank not found with id: " + id));
    }

    @Override
    public Banks getBankByUserId(Long userId) {
        return bankRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Bank not found for user id: " + userId));
    }

    @Override
    public Banks createBank(CreateBankRequest request) {
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found with ID: " + request.getUserId()));

        Banks bank = new Banks();
        bank.setBankName(request.getBankName());
        bank.setAccountName(request.getAccountName());
        bank.setBankCode(request.getBankCode());
        bank.setAccountNo(request.getAccountNo());
        bank.setCreateDate(LocalDateTime.now());
        bank.setUser(user);

        return bankRepository.save(bank);
    }

    @Override
    public Banks updateBank(Long id, UpdateBankRequest request) {
        Banks bank = bankRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bank not found with ID: " + id));

        bank.setBankName(request.getBankName());
        bank.setAccountName(request.getAccountName());
        bank.setAccountNo(request.getAccountNo());
        bank.setBankCode(request.getBankCode());
        bank.setModifyDate(LocalDateTime.now());

        return bankRepository.save(bank);
    }
}
