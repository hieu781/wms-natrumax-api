package com.natrumax.services;

import com.cloudinary.utils.StringUtils;
import com.natrumax.config.MisaApiClient;
import com.natrumax.dto.request.CreateTransactionRequest;
import com.natrumax.dto.request.UpdateTransactionRequest;
import com.natrumax.dto.response.BankResponse;
import com.natrumax.dto.response.CloudinaryResponse;
import com.natrumax.dto.response.TransactionResponse;
import com.natrumax.dto.response.WalletResponse;
import com.natrumax.models.*;
import com.natrumax.models.Enum.ETransactionStatus;
import com.natrumax.models.Enum.ETransactionType;
import com.natrumax.repository.BankRepository;
import com.natrumax.repository.DiscountRepository;
import com.natrumax.repository.TransactionRepository;
import com.natrumax.repository.WalletRepository;
import com.natrumax.services.APIService.MisaService;
import com.natrumax.services.interfaces.ITransactionsService;
import com.natrumax.utils.FileUploadUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TransactionsService implements ITransactionsService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private WalletRepository walletRepository;
    @Autowired
    private DiscountRepository discountRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    @Autowired
    private MisaApiClient misaApiClient;
    @Autowired
    private MisaService misaService;
    @Autowired
    private WalletService walletService;
    @Autowired
    private BankRepository bankRepository;

    public List<Transactions> getAllTransactions() {
        return transactionRepository.findAll();
    }

    public List<Transactions> getTransactionsByWalletId(int walletId) {
        return transactionRepository.findByWallet_WalletId(walletId);
    }

    @Transactional
    public Transactions createTransaction(CreateTransactionRequest request) {
        Wallet wallet = walletRepository.findById(request.getWalletId())
                .orElseThrow(() -> new EntityNotFoundException("Wallet not found with id: " + request.getWalletId()));

        User member = wallet.getUser();

        // Tìm warehouse mà user này là "Member"
        Warehouse memberWarehouse = member.getUserWarehouses()
                .stream()
                .filter(uw -> "Member".equalsIgnoreCase(uw.getRoleInWarehouse()))
                .map(UserWarehouse::getWarehouse)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("User is not a Member in any warehouse"));

        // Tìm Owner của warehouse đó
        boolean hasOwner = memberWarehouse.getUserWarehouses()
                .stream()
                .anyMatch(uw -> "Owner".equalsIgnoreCase(uw.getRoleInWarehouse()));

        if (!hasOwner) {
            throw new RuntimeException("Warehouse does not have an Owner. Cannot create transaction.");
        }

        Discount discount = null;
        if (request.getDiscountId() != null) {
            discount = discountRepository.findById(request.getDiscountId())
                    .orElseThrow(() -> new EntityNotFoundException("Discount not found with id: " + request.getDiscountId()));
        }

        Transactions transaction = new Transactions();
        transaction.setPaymentDate(LocalDateTime.now());
        transaction.setTotalAmount(request.getTotalAmount());
        transaction.setReceiptCode("");
        transaction.setStatus(ETransactionStatus.PENDING);
        transaction.setTransaction_type(request.getTransactionType());
        transaction.setCreateDate(LocalDateTime.now());
        transaction.setWallet(wallet);
        transaction.setDiscount(discount);

        return transactionRepository.save(transaction);
    }


    @Transactional
    public Transactions updateTransaction(int id, UpdateTransactionRequest request) {
        Transactions transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: " + id));

        if (request.getTotalAmount() != null) {
            transaction.setTotalAmount(request.getTotalAmount());
        }
        if (request.getReceiptCode() != null) {
            transaction.setReceiptCode(request.getReceiptCode());
        }
        if (request.getStatus() != null) {
            transaction.setStatus(request.getStatus());
        }
        if (request.getTransactionType() != null) {
            transaction.setTransaction_type(request.getTransactionType());
        }
        if (request.getDiscountId() != null) {
            Discount discount = discountRepository.findById(request.getDiscountId())
                    .orElseThrow(() -> new EntityNotFoundException("Discount not found with id: " + request.getDiscountId()));
            transaction.setDiscount(discount);
        }

        transaction.setModifyDate(LocalDateTime.now());

        return transactionRepository.save(transaction);
    }

    @Transactional
    public void uploadTransferImage(final int transactionsId, final MultipartFile file) {
        Transactions transaction = transactionRepository.findById(transactionsId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: " + transactionsId));

        final String cloudinaryImageId = transaction.getCloudinaryTransferImageId();
        if (StringUtils.isNotBlank(cloudinaryImageId)) {
            cloudinaryService.deleteFile(cloudinaryImageId);
        }

        FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);
        final String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());
        final CloudinaryResponse response = cloudinaryService.uploadFile(file, fileName);

        transaction.setTransferImage(response.getUrl());
        transaction.setCloudinaryTransferImageId(response.getPublicId());

        transaction.setStatus(ETransactionStatus.SUCCESS);

        transaction.setModifyDate(LocalDateTime.now());

        transactionRepository.save(transaction);
    }

    @Transactional
    public void uploadRefundImage(final int transactionsId, final MultipartFile file) {
        Transactions transaction = transactionRepository.findById(transactionsId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: " + transactionsId));

        final String cloudinaryImageId = transaction.getCloudinaryRefundImageId();
        if (StringUtils.isNotBlank(cloudinaryImageId)) {
            cloudinaryService.deleteFile(cloudinaryImageId);
        }

        FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);
        final String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());
        final CloudinaryResponse response = cloudinaryService.uploadFile(file, fileName);

        transaction.setRefundImage(response.getUrl());
        transaction.setCloudinaryRefundImageId(response.getPublicId());

        transaction.setStatus(ETransactionStatus.REFUNDED);

        transaction.setModifyDate(LocalDateTime.now());

        transactionRepository.save(transaction);
    }

    @Transactional
    public Transactions updateTransactionStatus(int transactionsId, String newStatus) {
        Transactions transaction = transactionRepository.findById(transactionsId)
                .orElseThrow(() -> new EntityNotFoundException("Transaction not found with id: " + transactionsId));

        ETransactionStatus statusEnum;
        try {
            statusEnum = ETransactionStatus.valueOf(newStatus.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid status value: " + newStatus);
        }

        if (statusEnum == ETransactionStatus.CANCELED && transaction.getReceiptCode() != null && !transaction.getReceiptCode().isEmpty()) {
            throw new RuntimeException("Không thể hủy giao dịch vì đã tạo chứng từ trong MISA (receiptCode tồn tại)");
        }

        transaction.setStatus(statusEnum);
        transaction.setModifyDate(LocalDateTime.now());

        if (statusEnum == ETransactionStatus.CONFIRMED) {
            User member = transaction.getWallet().getUser();

            Warehouse warehouse = member.getUserWarehouses()
                    .stream()
                    .filter(uw -> "Member".equalsIgnoreCase(uw.getRoleInWarehouse()))
                    .map(UserWarehouse::getWarehouse)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("User is not a Member in any warehouse"));

            User owner = warehouse.getUserWarehouses()
                    .stream()
                    .filter(uw -> "Owner".equalsIgnoreCase(uw.getRoleInWarehouse()))
                    .map(UserWarehouse::getUser)
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Warehouse does not have an Owner"));

            String receiptCode = misaService.createReceipt(member, owner, transaction.getTotalAmount());
            transaction.setReceiptCode(receiptCode);

//            double amount = transaction.getTotalAmount();
//
//            walletService.transferBetweenUsers(member, owner, amount);
        }

        return transactionRepository.save(transaction);
    }


    public List<Transactions> getTransactionsExcludingPending() {
        return transactionRepository.findByStatusNot(ETransactionStatus.PENDING);
    }

    public List<TransactionResponse> getTransactionsByStatus(ETransactionStatus status) {
        List<Transactions> transactions = transactionRepository.findByStatus(status);
        return transactions.stream().map(tx -> {
            Wallet wallet = tx.getWallet();
            User user = wallet.getUser();
            Banks bank = bankRepository.findByUser(user); // tạo method này trong `BanksRepository`

            BankResponse bankResponse = null;
            if (bank != null) {
                bankResponse = new BankResponse();
                bankResponse.setBankId(bank.getBankId());
                bankResponse.setBankName(bank.getBankName());
                bankResponse.setAccountName(bank.getAccountName());
                bankResponse.setAccountNo(bank.getAccountNo());
            }

            WalletResponse walletResponse = new WalletResponse();
            walletResponse.setWalletId(wallet.getWalletId());
            walletResponse.setBalance(wallet.getBalance());
            walletResponse.setCurrency(wallet.getCurrency());
            walletResponse.setCreateDate(wallet.getCreateDate());
            walletResponse.setModifyDate(wallet.getModifyDate());

            TransactionResponse transactionResponse = new TransactionResponse();
            transactionResponse.setTransactionsId(tx.getTransactionsId());
            transactionResponse.setPaymentDate(tx.getPaymentDate());
            transactionResponse.setTotalAmount(tx.getTotalAmount());
            transactionResponse.setReceiptCode(tx.getReceiptCode());
            transactionResponse.setTransferImage(tx.getTransferImage());
            transactionResponse.setRefundImage(tx.getRefundImage());
            transactionResponse.setStatus(tx.getStatus());
            transactionResponse.setTransaction_type(tx.getTransaction_type());
            transactionResponse.setCreateDate(tx.getCreateDate());
            transactionResponse.setModifyDate(tx.getModifyDate());
            transactionResponse.setWallet(walletResponse);
            transactionResponse.setBank(bankResponse);
            return transactionResponse;
        }).collect(Collectors.toList());
    }
}
