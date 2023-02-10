package com.coolmushroom.capitoleretail.controller;

import com.coolmushroom.capitoleretail.controller.request.GetPriceRequest;
import com.coolmushroom.capitoleretail.controller.response.GetPriceResponse;
import com.coolmushroom.capitoleretail.core.exception.PriceNotFound;
import com.coolmushroom.capitoleretail.core.usecase.GetPrice;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

import static com.coolmushroom.capitoleretail.controller.Endpoints.GET_PRICE;

@RestController
public class GetPriceController {
    private final GetPrice getPrice;

    public GetPriceController(GetPrice getPrice) {
        this.getPrice = getPrice;
    }

    @GetMapping(value = GET_PRICE)
    public ResponseEntity<GetPriceResponse> getPriceByDateProductAndBrand(@Valid @RequestBody GetPriceRequest getPriceRequest) {

        Optional<GetPriceResponse> priceResponse = getPrice.getPriceByDateProductAndBrand(getPriceRequest);

        return priceResponse.map(
                ResponseEntity::ok
        ).orElseThrow(() -> new PriceNotFound("Price not found for the requested set of parameters"));
    }
}
