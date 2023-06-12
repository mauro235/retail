package com.coolmushroom.retail.service;

import com.coolmushroom.retail.core.entity.Price;
import com.coolmushroom.retail.service.repository.PriceRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class PriceService {
    @Autowired
    private PriceRepo priceRepo;

    public Mono<Price> findPriceByApplicationDateProductAndBrand(LocalDateTime date, Long productId, Long brandId) {
        return priceRepo.findPriceByApplicationDateProductAndBrand(date, productId, brandId);
    }
}
