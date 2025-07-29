package com.natrumax.models.Enum;

public enum EOrderType {
    NORMAL("Giao ngay"),
    PREORDER("Đặt trước");

    private final String label;

    EOrderType(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static EOrderType fromLabel(String label) {
        for (EOrderType status : EOrderType.values()) {
            if (status.label.equalsIgnoreCase(label)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown order type: " + label);
    }
}
