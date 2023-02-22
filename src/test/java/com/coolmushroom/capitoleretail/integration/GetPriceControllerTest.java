package com.coolmushroom.capitoleretail.integration;

import com.coolmushroom.capitoleretail.BaseTest;
import com.coolmushroom.capitoleretail.controller.request.GetPriceRequest;
import com.coolmushroom.capitoleretail.controller.response.GetPriceResponse;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDateTime;

import static com.coolmushroom.capitoleretail.controller.Endpoints.GET_PRICE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class GetPriceControllerTest extends BaseTest {
    @Test
    public void whenRequestIsSuccessful() throws Exception {
        GetPriceRequest request = GetPriceRequest
                .builder()
                .applicationDate(LocalDateTime.parse("2020-06-14T10:00:00"))
                .brandId(1L)
                .productId(35455L)
                .build();

        MvcResult result = mockMvc
                .perform(get(GET_PRICE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializer.writeValueAsString(request))
                )
                .andExpect(status().isOk())
                .andReturn();

        GetPriceResponse response = serializer.readValue(result.getResponse().getContentAsString(), GetPriceResponse.class);
        assertEquals(35.50, response.getPrice());
        assertEquals(1, response.getPriceId());
        assertEquals(LocalDateTime.parse("2020-06-14T00:00:00"), response.getStartDate());
        assertEquals(LocalDateTime.parse("2020-12-31T23:59:59"), response.getEndDate());
    }

    @Test
    public void whenRequestMessageIsNotValid() throws Exception {
        GetPriceRequest request = GetPriceRequest
                .builder()
                .applicationDate(LocalDateTime.parse("2020-06-14T10:00:00"))
                .brandId(null)
                .productId(1L)
                .build();

        mockMvc
                .perform(get(GET_PRICE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializer.writeValueAsString(request))
                )
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenPriceIsNotFoundForRequestedId() throws Exception {
        GetPriceRequest request = GetPriceRequest
                .builder()
                .applicationDate(LocalDateTime.parse("2020-06-14T10:00:00"))
                .brandId(0L)
                .productId(35455L)
                .build();

        mockMvc
                .perform(get(GET_PRICE)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(serializer.writeValueAsString(request))
                )
                .andExpect(status().isNoContent());
    }
}
