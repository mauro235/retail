package com.coolmushroom.retail.unit;

import com.coolmushroom.retail.controller.request.GetPriceRequest;
import com.coolmushroom.retail.core.entity.Brand;
import com.coolmushroom.retail.core.entity.Price;
import com.coolmushroom.retail.core.usecase.GetPrice;
import com.coolmushroom.retail.service.PriceService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetPriceTest {
    private PriceService priceService;
    private GetPrice getPrice;

    @BeforeEach
    public void setUp() {
        priceService = mock(PriceService.class);
        getPrice = new GetPrice(priceService);
    }

    @Test
    public void whenPriceRepoFindsAResult() {

        var mockedPrice = Price.builder().brand(Brand.builder().id(1L).build()).build();
        var mockedResponse = Mono.just(mockedPrice);

        // any could also be explicitly set to strict types, but here
        when(priceService.findPriceByApplicationDateProductAndBrand(any(), any(), any()))
                .thenReturn(mockedResponse);

        getPrice.getPriceByDateProductAndBrand(GetPriceRequest.builder().build())
                .subscribe(res -> assertEquals(res.getPriceId(), mockedPrice.getId()));
    }

    @Test
    public void whenPriceRepoDoesNotFindAnyResult() {

        // any could also be explicitly set to strict types, but here
        when(priceService.findPriceByApplicationDateProductAndBrand(any(), any(), any()))
                .thenReturn(Mono.empty());

        var serviceResult = getPrice.getPriceByDateProductAndBrand(GetPriceRequest.builder().build()).hasElement();

        StepVerifier
                .create(serviceResult)
                .expectNext(false)
                .verifyComplete();

    }
}
