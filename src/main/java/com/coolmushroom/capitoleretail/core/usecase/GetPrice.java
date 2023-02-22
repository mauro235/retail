package com.coolmushroom.capitoleretail.core.usecase;

import com.coolmushroom.capitoleretail.controller.request.GetPriceRequest;
import com.coolmushroom.capitoleretail.controller.response.GetPriceResponse;
import com.coolmushroom.capitoleretail.core.entity.Brand;
import com.coolmushroom.capitoleretail.core.entity.Price;
import com.coolmushroom.capitoleretail.core.exception.InvalidModel;
import com.coolmushroom.capitoleretail.repository.PriceRepo;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class GetPrice {
    private final PriceRepo priceRepo;

    public GetPrice(PriceRepo priceRepo) {
        this.priceRepo = priceRepo;
    }

    public Optional<GetPriceResponse> getPriceByDateProductAndBrand(GetPriceRequest request) {

        Optional<Price> price = priceRepo.findPriceByApplicationDateProductAndBrand(request.getApplicationDate(), request.getProductId(), request.getBrandId());

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
