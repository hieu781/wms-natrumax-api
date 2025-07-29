package com.natrumax.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class UpdateProvinceRequest {

    @NotBlank(message = "Province name is required")
    private String name;

    @NotBlank(message = "Province code is required")
    @Size(max = 50, message = "Province code must be at most 50 characters")
    private String provinceCode;

    @NotBlank(message = "Province side is required")
    @Size(max = 50, message = "Province side must be at most 50 characters")
    private String provinceSide;

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProvinceCode() {
        return provinceCode;
    }

    public void setProvinceCode(String provinceCode) {
        this.provinceCode = provinceCode;
    }

    public String getProvinceSide() {
        return provinceSide;
    }

    public void setProvinceSide(String provinceSide) {
        this.provinceSide = provinceSide;
    }
}
