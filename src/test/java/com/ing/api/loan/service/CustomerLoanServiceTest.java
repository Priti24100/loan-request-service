package com.ing.api.loan.service;


import com.ing.api.loan.dto.LoanRequest;
import com.ing.api.loan.dto.LoanResponse;
import com.ing.api.loan.exception.CustomerNotFoundException;
import com.ing.api.loan.model.CustomerEntity;
import com.ing.api.loan.model.LoanEntity;
import com.ing.api.loan.repo.CustomerLoanRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CustomerLoanServiceTest {

    @InjectMocks
    private CustomerLoanService customerLoanService;

    @Mock
    private CustomerLoanRepo loanRepositoryMock;


    private CustomerEntity getloanRequest() {
        return CustomerEntity.builder().customerId(1L).customerFullName("Test").loanRequestDtos(List.of(LoanEntity.builder().loanAmount(BigDecimal.valueOf(600L)).build())).build();
    }


    @Test
    void getCustomerLoanSuccessTest() {
        CustomerEntity customerRequest = getloanRequest();

        when(this.loanRepositoryMock.findCustomerByCustomerId(Mockito.any())).thenReturn(Optional.ofNullable(customerRequest));

        LoanResponse CustLoanRequestResponse = customerLoanService.getCustomerLoan(Mockito.any());

        assertNotNull(CustLoanRequestResponse);
        assertEquals(customerRequest.getCustomerId(), CustLoanRequestResponse.getCustomerId());
    }

    @Test
    void getCustomerLoanByCustomerNotFoundTest() {
        long customerId = 1L;
        CustomerEntity customer = null;
        when(this.loanRepositoryMock.findCustomerByCustomerId(customerId)).thenReturn(Optional.ofNullable(customer));

        CustomerNotFoundException customerNotFoundException = assertThrows(CustomerNotFoundException.class, () -> customerLoanService.getCustomerLoan(customerId));

        assertEquals("Customer Loan Details not found by Customer Id :" + customerId, customerNotFoundException.getMessage());
    }

    @Test
    void createCustomerLoanSuccessTest() {
        LoanRequest loanRequest = LoanRequest.builder().customerId(1L).customerFullName("Test").loanAmount(BigDecimal.valueOf(2000)).build();
        CustomerEntity customer = getloanRequest();
        when(loanRepositoryMock.save(Mockito.any())).thenReturn(customer);

        CustomerEntity CustLoanRequestResponse = customerLoanService.saveCustomerLoan(loanRequest);

        assertNotNull(CustLoanRequestResponse);
        assertEquals(loanRequest.getCustomerId(), CustLoanRequestResponse.getCustomerId());
    }

    @Test
    void saveCustomerLoanFailedTest() {
        LoanRequest loanRequest = LoanRequest.builder().customerId(1L).customerFullName("Test").loanAmount(BigDecimal.valueOf(2000)).build();
        when(loanRepositoryMock.save(Mockito.any())).thenThrow(HttpClientErrorException.BadRequest.class);
        assertThrows(HttpClientErrorException.BadRequest.class, () -> customerLoanService.saveCustomerLoan(loanRequest));
    }

}
