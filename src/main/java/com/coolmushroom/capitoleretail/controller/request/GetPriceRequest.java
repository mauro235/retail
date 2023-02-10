package com.coolmushroom.capitoleretail.controller.request;

import com.coolmushroom.capitoleretail.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@Jacksonized
public class GetPriceRequest {
    @JsonFormat(pattern = Constants.LOCAL_DATE_TIME_FORMAT_PATTERN)
    @NotNull
    private LocalDateTime applicationDate;

    @NotNull
    private Long productId;

    @NotNull
    private Long brandId;
}
