package com.natrumax.repository;

import com.natrumax.models.Enum.ETransactionStatus;
import com.natrumax.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transactions, Integer> {
    List<Transactions> findByWallet_WalletId(Integer walletId);

    List<Transactions> findByStatusNot(ETransactionStatus status);

    List<Transactions> findByStatus(ETransactionStatus status);
}
