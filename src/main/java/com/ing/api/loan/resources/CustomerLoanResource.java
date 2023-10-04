package com.ing.api.loan.resources;

import com.ing.api.loan.dto.LoanRequest;
import com.ing.api.loan.dto.LoanResponse;
import com.ing.api.loan.model.CustomerEntity;
import com.ing.api.loan.service.CustomerLoanService;
import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/loan")
public class CustomerLoanResource {

    @Autowired
    private CustomerLoanService customerLoanService;

    @PostMapping(value = "/createLoan", consumes = MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Save loan request")
    public ResponseEntity<String> saveLoanRequest(@RequestBody @Valid LoanRequest loanRequest) {
        CustomerEntity saveCustomerLoan = customerLoanService.saveCustomerLoan(loanRequest);
        return new ResponseEntity<>("Loan created successfully for Customer ID - " + saveCustomerLoan.getCustomerId(), HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    @Operation(summary = "Get Customer loan amount")
    public ResponseEntity<LoanResponse> getLoanByCustomerId(@PathVariable("customerId") Long customerId) {
        return new ResponseEntity<>(customerLoanService.getCustomerLoan(customerId), HttpStatus.OK);
    }
}
