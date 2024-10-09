package io.api.btgpactual.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.api.btgpactual.domain.dto.command.CreateCustomerDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


import static io.api.btgpactual.mocks.CustomerMock.CreateCustomerDTOMock;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

//@SpringBootTest
//@AutoConfigureMockMvc
public class CustomerControllerTest {
    @Autowired
    private MockMvc mockMvc;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Test
    public void createNewCustomerTest() throws Exception {
        CreateCustomerDTO createCustomerDTO = CreateCustomerDTOMock();

        this.mockMvc.perform(MockMvcRequestBuilders
                .post("/customers")
                .contentType("application/json")
                .content(objectMapper.writeValueAsString(createCustomerDTO))
                .accept("application/json"))
                .andExpect(status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("$.message").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.createdId").isNumber());
    }
}
