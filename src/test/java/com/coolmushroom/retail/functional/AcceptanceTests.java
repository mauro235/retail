package com.coolmushroom.retail.functional;

import com.coolmushroom.retail.BaseTest;
import com.coolmushroom.retail.controller.request.GetPriceRequest;
import com.coolmushroom.retail.controller.response.GetPriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.time.LocalDateTime;

import static com.coolmushroom.retail.controller.Endpoints.GET_PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class AcceptanceTests extends BaseTest {

    /**
     * Test 1: petición a las 10:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void whenThereIsOnlyOnePriceToBeApplied() throws Exception {
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

    /**
     * Test 2: petición a las 16:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void whenThereAreTwoPricesToBeAppliedButHigherPriorityIsSelected() throws Exception {
        GetPriceRequest request = GetPriceRequest
                .builder()
                .applicationDate(LocalDateTime.parse("2020-06-14T16:00:00"))
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
                .expectBody(GetPriceResponse.class)
                .value(
                        response -> {
                            assertEquals(25.45, response.getPrice());
                            assertEquals(2, response.getPriceId());
                            assertEquals(LocalDateTime.parse("2020-06-14T15:00:00"), response.getStartDate());
                            assertEquals(LocalDateTime.parse("2020-06-14T18:30:00"), response.getEndDate());
                        }
                );
    }

    /**
     * Test 3: petición a las 21:00 del día 14 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void testThree() throws Exception {
        GetPriceRequest request = GetPriceRequest
                .builder()
                .applicationDate(LocalDateTime.parse("2020-06-14T21:00:00"))
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

    /**
     * Test 4: petición a las 10:00 del día 15 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void testFour() throws Exception {
        GetPriceRequest request = GetPriceRequest
                .builder()
                .applicationDate(LocalDateTime.parse("2020-06-15T10:00:00"))
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
                            assertEquals(30.50, response.getPrice());
                            assertEquals(3, response.getPriceId());
                            assertEquals(LocalDateTime.parse("2020-06-15T00:00:00"), response.getStartDate());
                            assertEquals(LocalDateTime.parse("2020-06-15T11:00:00"), response.getEndDate());
                        }
                );
    }

    /**
     * Test 5: petición a las 21:00 del día 16 del producto 35455 para la brand 1 (ZARA)
     */
    @Test
    public void testFive() throws Exception {
        GetPriceRequest request = GetPriceRequest
                .builder()
                .applicationDate(LocalDateTime.parse("2020-06-16T21:00:00"))
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
                            assertEquals(38.95, response.getPrice());
                            assertEquals(4, response.getPriceId());
                            assertEquals(LocalDateTime.parse("2020-06-15T16:00:00"), response.getStartDate());
                            assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), response.getEndDate());
                        }
                );
    }
}
