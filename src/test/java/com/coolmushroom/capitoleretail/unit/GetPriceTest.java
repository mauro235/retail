package com.coolmushroom.capitoleretail.unit;

import com.coolmushroom.capitoleretail.controller.request.GetPriceRequest;
import com.coolmushroom.capitoleretail.core.entity.Brand;
import com.coolmushroom.capitoleretail.core.entity.Price;
import com.coolmushroom.capitoleretail.core.exception.InvalidModel;
import com.coolmushroom.capitoleretail.core.usecase.GetPrice;
import com.coolmushroom.capitoleretail.repository.PriceRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class GetPriceTest {
    private PriceRepo priceRepo;
    private GetPrice getPrice;

    @BeforeEach
    public void setUp() {
        priceRepo = mock(PriceRepo.class);
        getPrice = new GetPrice(priceRepo);
    }

    @Test
    public void whenPriceRepoFindsAResult() {

        // any could also be explicitly set to strict types, but here
        when(priceRepo.findPriceByApplicationDateProductAndBrand(any(), any(), any()))
                .thenReturn(Optional.of(Price.builder().brand(Brand.builder().id(1L).build()).build()));

        assertTrue(getPrice.getPriceByDateProductAndBrand(GetPriceRequest.builder().build()).isPresent());
    }

    @Test
    public void whenPriceRepoDoesNotFindAnyResult() {

        // any could also be explicitly set to strict types, but here
        when(priceRepo.findPriceByApplicationDateProductAndBrand(any(), any(), any()))
                .thenReturn(Optional.empty());

        assertTrue(getPrice.getPriceByDateProductAndBrand(GetPriceRequest.builder().build()).isEmpty());
    }

    @Test
    public void whenPriceModelIsNotValid() {

        // any could also be explicitly set to strict types, but here
        when(priceRepo.findPriceByApplicationDateProductAndBrand(any(), any(), any()))
                .thenReturn(Optional.of(Price.builder().build()));

        assertThrows(InvalidModel.class,
                () -> getPrice.getPriceByDateProductAndBrand(GetPriceRequest.builder().build()));
    }

}
