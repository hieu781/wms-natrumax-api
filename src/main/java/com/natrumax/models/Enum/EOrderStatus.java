package com.natrumax.models.Enum;

public enum EOrderStatus {
    // Order statuses
    PENDING("Đang chờ xác nhận"),//Van tach don (LOCK)
                                        //Hoi Phong cach lay product detail
    CONFIRMED("Đã xác nhận"), //Create misa-sale
                                    //Sua invoice status thanh confirm
    PACKED("Đã đóng gói"),
    SHIPPED("Đang được giao"),
    DELIVERED("Đã được giao"), //Check misa-inventory-out (Nho update inventory out code vao order)
                                    // if Role == DISTRIBUTOR --> Create misa-inventory-in
                                    // if Role == BRANCH-OWNER --> Create kiotviet-purchase-order
    CANCELED("Đã hủy"), //Cancelled order if inventoryOut == null
                                // invoice status --> CANCELED
    RETURNED("Hoàn trả"),
    FAILED("Thất bại");


    private final String label;

    EOrderStatus(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static EOrderStatus fromLabel(String label) {
        for (EOrderStatus status : EOrderStatus.values()) {
            if (status.label.equalsIgnoreCase(label)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown order status: " + label);
    }
}

