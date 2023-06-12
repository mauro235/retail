package com.coolmushroom.retail.controller.response;

import com.coolmushroom.retail.Constants;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import lombok.extern.jackson.Jacksonized;

import java.time.LocalDateTime;

@Data
@Builder(toBuilder = true)
@Jacksonized
public class GetPriceResponse {
    private Long productId;

    private Long brandId;

    private Long priceId;

    @JsonFormat(pattern = Constants.LOCAL_DATE_TIME_FORMAT_PATTERN)
    private LocalDateTime startDate;

    @JsonFormat(pattern = Constants.LOCAL_DATE_TIME_FORMAT_PATTERN)
    private LocalDateTime endDate;

    private Double price;

    private String currency;
}
