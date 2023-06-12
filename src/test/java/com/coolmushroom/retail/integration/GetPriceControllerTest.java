package com.coolmushroom.retail.integration;

import com.coolmushroom.retail.BaseTest;
import com.coolmushroom.retail.controller.request.GetPriceRequest;
import com.coolmushroom.retail.controller.response.GetPriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static com.coolmushroom.retail.controller.Endpoints.GET_PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GetPriceControllerTest extends BaseTest {
    @Test
    public void whenRequestIsSuccessful() throws Exception {
        GetPriceRequest request = GetPriceRequest
                .builder()
                .applicationDate(LocalDateTime.parse("2020-06-14T10:00:00"))
                .brandId(1L)
                .productId(35455L)
                .build();

        webTestClient
                .method(HttpMethod.GET)
                .uri(GET_PRICE)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isOk()
                .expectBody(GetPriceResponse.class).value(
                        response -> {
                            assertEquals(35.50, response.getPrice());
                            assertEquals(1, response.getPriceId());
                            assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), response.getStartDate());
                            assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), response.getEndDate());
                        }
                );
    }

    @Test
    public void whenRequestMessageIsNotValid() throws Exception {
        GetPriceRequest request = GetPriceRequest
                .builder()
                .applicationDate(LocalDateTime.parse("2020-06-14T10:00:00"))
                .brandId(null)
                .productId(1L)
                .build();

        webTestClient
                .method(HttpMethod.GET)
                .uri(GET_PRICE)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    public void whenPriceIsNotFoundForRequestedId() throws Exception {
        GetPriceRequest request = GetPriceRequest
                .builder()
                .applicationDate(LocalDateTime.parse("2020-06-14T10:00:00"))
                .brandId(0L)
                .productId(35455L)
                .build();

        webTestClient
                .method(HttpMethod.GET)
                .uri(GET_PRICE)
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValue(request)
                .exchange()
                .expectStatus().isNoContent();
    }
}
