package com.natrumax.models.Enum;

public enum EInvoiceStatus {
    PENDING("Đang chờ thanh toán"),//Chuyen khoan thi la dang cho thanh toan
    SUCCESS("Đã thanh toán"),//Wallet thi success luon
                                    //Update transfer image thanh cong thi pending thanh success
    CONFIRMED("Đã xác nhận"),//Lam luon transaction service
    CANCELED("Đã hủy"),
    REFUNDED("Đã hoàn tiền");//Update REFUND image thanh cong thi CANCELED thanh REFUNDED
    private final String label;

    EInvoiceStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static EInvoiceStatus fromLabel(String label) {
        for (EInvoiceStatus status : EInvoiceStatus.values()) {
            if (status.label.equalsIgnoreCase(label)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown invoice status: " + label);
    }
}
