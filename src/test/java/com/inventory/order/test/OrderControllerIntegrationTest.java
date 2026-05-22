package com.inventory.order.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
public class OrderControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldCreateOrder()
            throws Exception {

        String body = """
                {
                  "userId": 1,
                  "items": [
                    {
                      "productId": 1,
                      "quantity": 2,
                      "price": 100
                    }
                  ]
                }
                """;

        mockMvc.perform(

                post("/api/v1/orders")

                        .contentType(
                                MediaType.APPLICATION_JSON)

                        .content(body))

                .andExpect(status().isOk());
    }
}