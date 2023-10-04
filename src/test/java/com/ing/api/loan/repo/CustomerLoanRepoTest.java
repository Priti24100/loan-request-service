package com.ing.api.loan.repo;

import com.ing.api.loan.model.CustomerEntity;
import com.ing.api.loan.model.LoanEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase.Replace;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = Replace.NONE)
class CustomerLoanRepoTest {

    @Autowired
    private CustomerLoanRepo customerLoanRepoTest;

    @AfterEach
    void tearDown() {
        customerLoanRepoTest.deleteAll();
    }


    @Test
    void isLoanExistsByIdOrNotTest() {
        //given loan request
        List<LoanEntity> loanRequestDtos = new ArrayList<>();
        LoanEntity loanRequestDto = new LoanEntity(1L, BigDecimal.valueOf(700));
        loanRequestDtos.add(loanRequestDto);
        CustomerEntity customerDto = new CustomerEntity(Long.valueOf(11), "Priti Kumari", loanRequestDtos);

        //save test loan request
        customerLoanRepoTest.save(customerDto);

        boolean existingCustomer = customerLoanRepoTest.existsById(customerDto.getCustomerId());

        assertTrue(existingCustomer);
    }

    @Test
    void isLoanSavedSuccessfullOrNotTest() {

        List<LoanEntity> loanRequestDtos = new ArrayList<>();
        LoanEntity loanRequestDto = new LoanEntity(1L, BigDecimal.valueOf(700));
        loanRequestDtos.add(loanRequestDto);
        CustomerEntity customerDto = new CustomerEntity(Long.valueOf(11), "Priti Kumari", loanRequestDtos);

        CustomerEntity customerLoanData = customerLoanRepoTest.save(customerDto);

        assertEquals(11L, customerLoanData.getCustomerId());
    }
}