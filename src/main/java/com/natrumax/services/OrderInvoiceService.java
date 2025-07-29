package com.natrumax.services;

import com.cloudinary.utils.StringUtils;
import com.natrumax.dto.request.CreateOrderInvoiceRequest;
import com.natrumax.dto.request.UpdateOrderInvoiceRequest;
import com.natrumax.dto.response.CloudinaryResponse;
import com.natrumax.models.Discount;
import com.natrumax.models.Enum.EInvoiceStatus;
import com.natrumax.models.Enum.ETransactionStatus;
import com.natrumax.models.Invoices;
import com.natrumax.models.Order;
import com.natrumax.repository.DiscountRepository;
import com.natrumax.repository.OrderInvoiceRepository;
import com.natrumax.repository.OrderRepository;
import com.natrumax.services.interfaces.IOrderInvoiceService;
import com.natrumax.utils.FileUploadUtil;
import jakarta.persistence.EntityNotFoundException;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;

@Service
public class OrderInvoiceService implements IOrderInvoiceService {
    @Autowired
    private OrderInvoiceRepository orderInvoiceRepository;

    @Autowired
    private DiscountRepository discountRepository;

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CloudinaryService cloudinaryService;
    public Invoices createInvoice(CreateOrderInvoiceRequest request) {
        Invoices invoice = new Invoices();
        invoice.setTotalAmount(request.getTotalAmount());
        invoice.setDiscount(null);

        if (request.getDiscountId() != 0) {
            Discount discount = discountRepository.findById(request.getDiscountId())
                    .orElseThrow(() -> new EntityNotFoundException("Discount not found"));
            invoice.setDiscount(discount);
        }

        invoice.setPaymentMethod(request.getPaymentMethod());
        invoice.setCreateDate(LocalDateTime.now());

        if ("Chuyển khoản".equalsIgnoreCase(request.getPaymentMethod().getLabel())) {
            invoice.setStatus(EInvoiceStatus.PENDING);
        }
        else if ("Dùng số dư ví".equalsIgnoreCase(request.getPaymentMethod().getLabel())) {
            invoice.setStatus(EInvoiceStatus.SUCCESS);
        }
        return orderInvoiceRepository.save(invoice);
    }


    public Invoices getOrderInvoiceById(int id) {
        return orderInvoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderInvoice not found with id: " + id));
    }

    public Invoices updateOrderInvoice(int id, UpdateOrderInvoiceRequest request) {
        Invoices invoices = orderInvoiceRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("OrderInvoice not found with id: " + id));

        if (request.getPaymentDate() != null) {
            invoices.setPaymentDate(request.getPaymentDate());
        }
        if (request.getPaymentMethod() != null) {
            invoices.setPaymentMethod(request.getPaymentMethod());
        }
        if (request.getTotalAmount() != null) {
            invoices.setTotalAmount(request.getTotalAmount());
        }
        if (request.getStatus() != null) {
            invoices.setStatus(request.getStatus());
        }
        if (request.getDiscountId() != null) {
            Discount discount = discountRepository.findById(request.getDiscountId())
                    .orElseThrow(() -> new EntityNotFoundException("Discount not found with id: " + request.getDiscountId()));
            invoices.setDiscount(discount);
        }

        invoices.setModifyDate(LocalDateTime.now());

        return orderInvoiceRepository.save(invoices);
    }

    @Override
    public void updateInvoiceStatus(Long orderId, EInvoiceStatus status) {
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));
        Invoices invoices = orderInvoiceRepository.findByOrder_OrderId(orderId);
        if (invoices == null) {
            throw new EntityNotFoundException("Invoice for this order not found");
        }
        if (status == EInvoiceStatus.PENDING && order.getInvoices().getPaymentMethod().getLabel() == "CASH") {
            invoices.setStatus(EInvoiceStatus.SUCCESS);
        }

    }
    public void uploadTransferImage(final Long orderId, final MultipartFile file){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        Invoices invoices = order.getInvoices();

        final String cloudinaryImageId = invoices.getCloudinaryTransferImageId();
        if (StringUtils.isNotBlank(cloudinaryImageId)) {
            cloudinaryService.deleteFile(cloudinaryImageId);
        }

        FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);
        final String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());
        final CloudinaryResponse response = cloudinaryService.uploadFile(file, fileName);

        invoices.setTransferImage(response.getUrl());
        invoices.setCloudinaryTransferImageId(response.getPublicId());

        invoices.setStatus(EInvoiceStatus.SUCCESS);

        invoices.setModifyDate(LocalDateTime.now());

        orderInvoiceRepository.save(invoices);
    }

    public void uploadRefundImage(final Long orderId, final MultipartFile file){
        Order order = orderRepository.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order not found"));

        Invoices invoices = order.getInvoices();

        final String cloudinaryImageId = invoices.getCloudinaryRefundImageId();
        if (StringUtils.isNotBlank(cloudinaryImageId)) {
            cloudinaryService.deleteFile(cloudinaryImageId);
        }

        FileUploadUtil.assertAllowed(file, FileUploadUtil.IMAGE_PATTERN);
        final String fileName = FileUploadUtil.getFileName(file.getOriginalFilename());
        final CloudinaryResponse response = cloudinaryService.uploadFile(file, fileName);

        invoices.setRefundImage(response.getUrl());
        invoices.setCloudinaryRefundImageId(response.getPublicId());

        invoices.setStatus(EInvoiceStatus.REFUNDED);

        invoices.setModifyDate(LocalDateTime.now());

        orderInvoiceRepository.save(invoices);
    }
    public void confirmOrderInvoice(Order order){
        Invoices invoices = order.getInvoices();
        invoices.setStatus(EInvoiceStatus.CONFIRMED);
    }
}
