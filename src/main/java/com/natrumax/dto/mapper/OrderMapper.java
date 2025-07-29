package com.natrumax.dto.mapper;

import com.natrumax.dto.response.*;
import com.natrumax.models.*;

import java.util.List;
import java.util.stream.Collectors;


public class OrderMapper {
//    public static OrderResponse toOrderResponse(Order order) {
//        OrderResponse response = new OrderResponse();
//        response.setOrderId(order.getOrderId());
//        response.setOrderDate(order.getOrderDate());
//        response.setSaleCode(order.getSaleCode());
//        response.setInventoryOutCode(order.getInventoryOutCode());
//        response.setCreatedDate(order.getCreatedDate());
//        response.setModifiedDate(order.getModifiedDate());
//        response.setStatus(order.getEStatus().name());
//
//        // Map OrderDetails
//        List<OrderDetailResponse> detailResponses = order.getOrderDetails().stream()
//                .map(OrderMapper::toOrderDetailResponse)
//                .collect(Collectors.toList());
//        response.setOrderDetails(detailResponses);
//
//        // Map Invoice
//        if (order.getOrderInvoice() != null) {
//            response.setOrderInvoice(toInvoiceResponse(order.getOrderInvoice()));
//        }
//
//        return response;
//    }

//    private static OrderDetailResponse toOrderDetailResponse(OrderDetail detail) {
//        OrderDetailResponse response = new OrderDetailResponse();
//        response.setOrderDetailId(detail.getOrderDetailId());
//        response.setBonus(detail.isBonus());
//        response.setQuantity(detail.getQuantity());
//        response.setPrice(detail.getPrice());
//        response.setBonusQuantity(detail.getBonusQuantity());
//        response.setCreateDate(detail.getCreateDate());
//        response.setModifyDate(detail.getModifyDate());
//        response.setProduct(toProductResponse(detail.getProduct()));
//        return response;
//    }

//    private static OrderInvoiceResponse toInvoiceResponse(Invoices invoice) {
//        OrderInvoiceResponse response = new OrderInvoiceResponse();
//        response.setInvoiceId(invoice.getInvoiceId());
//        response.setPaymentDate(invoice.getPaymentDate());
//        response.setPaymentMethod(invoice.getPaymentMethod());
//        response.setTotalAmount(invoice.getTotalAmount());
//        response.setStatus(invoice.getStatus());
//        response.setCreateDate(invoice.getCreateDate());
//        response.setModifyDate(invoice.getModifyDate());
//        if (invoice.getDiscount() != null) {
//            response.setDiscount(toDiscountResponse(invoice.getDiscount()));
//        }
//        return response;
//    }

//    private static DiscountResponse toDiscountResponse(Discount discount) {
//        DiscountResponse response = new DiscountResponse();
//        response.setDiscountId(discount.getDiscountId());
//        response.setDiscountPercentage(discount.getDiscountPercent());
//        response.setMinimumAmount(discount.getMinimumAmount());
//        response.setActiveDate(discount.getActiveDate());
//        response.setExpiryDate(discount.getExpiryDate());
//        response.setDescription(discount.getDescription());
//        response.setStatus(discount.isStatus());
//        response.setCreateDate(discount.getCreateDate());
//        response.setModifyDate(discount.getModifyDate());
//        return response;
//    }

//    private static ProductResponse toProductResponse(Product product) {
//        ProductResponse response = new ProductResponse();
//        response.setProductId(product.getProductId());
//        response.setBarcode(product.getBarcode());
//        response.setName(product.getName());
//        response.setDescription(product.getDescription());
//        response.setDiscount(product.getDiscount());
//        response.setBasePrice(product.getBasePrice());
//        response.setImage(product.getImage());
//        response.setMisaCode(product.getMisaCode());
//        response.setQuantityToGetPromotion(product.getQuantityToGetPromotion());
//        response.setStatus(product.isStatus());
//        response.setUnit(product.getUnit());
//        response.setCreateDate(product.getCreateDate());
//        response.setModifyDate(product.getModifyDate());
//        return response;
//    }
}
