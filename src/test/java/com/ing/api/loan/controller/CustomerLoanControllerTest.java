package com.ing.api.loan.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ing.api.loan.dto.LoanRequest;
import com.ing.api.loan.exception.CustomerNotFoundException;
import com.ing.api.loan.model.CustomerEntity;
import com.ing.api.loan.service.CustomerLoanService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.math.BigDecimal;

import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerLoanControllerTest {

    public static String createLoanUrl = "/loan/createLoan";
    public static String getLoanUrl = "/loan/";
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private ObjectMapper objectMapper;
    @MockBean
    private CustomerLoanService customerLoanService;

    /**
     * Create loan request
     *
     * @throws Exception
     */
    @Test
    void addCustomerLoanTest() throws Exception {
        CustomerEntity customer = CustomerEntity.builder().customerId(1L).customerFullName("Test").build();
        LoanRequest loanRequest = LoanRequest.builder().customerId(1L).customerFullName("Test").loanAmount(BigDecimal.valueOf(2000)).build();
        when(this.customerLoanService.saveCustomerLoan(Mockito.any())).thenReturn(customer);

        this.mockMvc.perform(post(createLoanUrl)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(loanRequest)))
                .andExpect(status().isCreated());

    }

    /**
     * Checking if customer Id is available ot not
     *
     * @throws Exception
     */
    @Test
    void customerLoanAmountNotFoundTest() throws Exception {
        long customerId = 1L;
        when(this.customerLoanService.getCustomerLoan(customerId)).thenThrow(new CustomerNotFoundException("Loan Details of Customer not found by Customer Id :" + customerId));

        this.mockMvc.perform(get(getLoanUrl + customerId)
                        .accept(APPLICATION_JSON))
                .andExpect(status().isNotFound());

    }


    /**
     * Checking Bad request if something is not provided which is required for request
     *
     * @throws Exception
     */
    @Test
    void validationCheckExceptionTest() throws Exception {
        CustomerEntity customer = CustomerEntity.builder().customerId(null).customerFullName("Test").build();

        this.mockMvc.perform(post(createLoanUrl)
                        .contentType(APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customer)))
                .andExpect(status().isBadRequest());
    }


}
