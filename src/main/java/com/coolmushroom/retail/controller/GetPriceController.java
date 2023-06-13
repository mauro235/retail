package com.coolmushroom.retail.controller;

import com.coolmushroom.retail.controller.request.GetPriceRequest;
import com.coolmushroom.retail.controller.response.GetPriceResponse;
import com.coolmushroom.retail.core.exception.PriceNotFound;
import com.coolmushroom.retail.core.usecase.GetPrice;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import static com.coolmushroom.retail.controller.Endpoints.GET_PRICE;

@RestController
public class GetPriceController {
    private final GetPrice getPrice;

    public GetPriceController(GetPrice getPrice) {
        this.getPrice = getPrice;
    }

    @GetMapping(value = GET_PRICE)
    public Mono<GetPriceResponse> getPriceByDateProductAndBrand(@Valid @RequestBody GetPriceRequest getPriceRequest) {

        return Mono.fromCallable(() -> {
                return getPrice.getPriceByDateProductAndBrand(getPriceRequest)
                        .switchIfEmpty(Mono.error(new PriceNotFound("No se encontraron registros para los parámetros provistos")))
                        .map(p -> p);
            }
        ).flatMap(r -> r);
    }
}
