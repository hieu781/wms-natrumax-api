package com.natrumax.models.Enum;

public enum ETransactionStatus {
    PENDING("Đang chờ xác nhận"),
    SUCCESS("Thành công"),
    CONFIRMED("Đã xác nhận"),
    CANCELED("Đã hủy"),
    REFUNDED("Đã hoàn tiền");
    private final String displayName;

    ETransactionStatus(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
