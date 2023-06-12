package com.coolmushroom.retail.core.usecase;

import com.coolmushroom.retail.controller.request.GetPriceRequest;
import com.coolmushroom.retail.controller.response.GetPriceResponse;
import com.coolmushroom.retail.core.entity.Brand;
import com.coolmushroom.retail.core.entity.Price;
import com.coolmushroom.retail.core.exception.InvalidModel;
import com.coolmushroom.retail.service.PriceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Optional;
@Service
public class GetPrice {

    @Autowired
    private final PriceService priceService;

    public GetPrice(PriceService priceService) {
        this.priceService = priceService;
    }

    public Mono<GetPriceResponse> getPriceByDateProductAndBrand(GetPriceRequest request) {

        Mono<Price> price = priceService.findPriceByApplicationDateProductAndBrand(request.getApplicationDate(), request.getProductId(), request.getBrandId());

        return price.map(
                p -> GetPriceResponse
                        .builder()
                        .productId(p.getProductId())
                        // Avoid NPE on composite Brand class
                        .brandId(Optional.ofNullable(p.getBrand()).map(Brand::getId).orElseThrow(() -> new InvalidModel("Brand can't be null")))
                        .priceId(p.getId())
                        .startDate(p.getStartDate())
                        .endDate(p.getEndDate())
                        .price(p.getPrice())
                        .currency(p.getCurr())
                        .build()
        );
    }
}
