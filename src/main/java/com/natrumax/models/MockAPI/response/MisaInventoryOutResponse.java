package com.natrumax.models.MockAPI.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.natrumax.models.MockAPI.MisaInventoryOut;

import java.util.List;

public class MisaInventoryOutResponse {
    private String message;

    @JsonProperty("data")
    private List<MisaInventoryOut> records;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<MisaInventoryOut> getRecords() {
        return records;
    }

    public void setRecords(List<MisaInventoryOut> records) {
        this.records = records;
    }
}
