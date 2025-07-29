package com.natrumax.models.Enum;

public enum EPaymentMethod {
    WALLET("Dùng số dư ví"), //Ko co j khac biet
    BANK_TRANSFER("Chuyển khoản"); //

    private final String label;

    EPaymentMethod(String label) {
        this.label = label;
    }

    public String getLabel() {
        return label;
    }

    public static EPaymentMethod fromLabel(String label) {
        for (EPaymentMethod status : EPaymentMethod.values()) {
            if (status.label.equalsIgnoreCase(label)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Unknown order type: " + label);
    }
}
