package com.ing.api.loan.service;

import com.ing.api.loan.dto.LoanRequest;
import com.ing.api.loan.dto.LoanResponse;
import com.ing.api.loan.exception.CustomerNotFoundException;
import com.ing.api.loan.model.CustomerEntity;
import com.ing.api.loan.model.LoanEntity;
import com.ing.api.loan.repo.CustomerLoanRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class CustomerLoanService {

    @Autowired
    private CustomerLoanRepo customerLoanRepo;

    public CustomerEntity saveCustomerLoan(LoanRequest loanRequest) {
        CustomerEntity customer = createLoanRequest(loanRequest);
        log.info("Save loan details for  id {}", customer.getCustomerId());
        return customerLoanRepo.save(customer);
    }

    public LoanResponse getCustomerLoan(Long customerId) {
        log.info("get loan details for id {} ", customerId);
        Optional<CustomerEntity> customerLoanInfo = customerLoanRepo.findCustomerByCustomerId(customerId);
        if (customerLoanInfo.isEmpty()) {
            throw new CustomerNotFoundException("Customer Loan Details not found by Customer Id :" + customerId);
        }
        //Adding loan amount by customer
        BigDecimal loanAmount = customerLoanInfo.get().getLoanRequestDtos().stream().map(i -> i.getLoanAmount()).reduce(BigDecimal.ZERO, BigDecimal::add);
        return new LoanResponse(customerLoanInfo.get().getCustomerId(), customerLoanInfo.get().getCustomerFullName(), loanAmount);
    }

    private CustomerEntity createLoanRequest(LoanRequest loanRequest) {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setCustomerId(loanRequest.getCustomerId());
        customerEntity.setCustomerFullName(loanRequest.getCustomerFullName());
        List<LoanEntity> loanEntities = new ArrayList<>();
        LoanEntity loanEntity = new LoanEntity();
        loanEntity.setLoanAmount(loanRequest.getLoanAmount());
        loanEntities.add(loanEntity);
        customerEntity.setLoanRequestDtos(loanEntities);
        return customerEntity;
    }
}
