package com.ing.api.loan.dto;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class LoanRequest {

    @NotNull(message = "Customer Id should not be empty")
    @Min(value = 1, message = "Please enter valid Customer Id")
    private Long customerId;

    @NotBlank(message = "Customer Full Name should not be empty")
    private String customerFullName;

    @NotNull(message = "Loan Amount should not be empty")
    @Min(value = 500, message = "Minimum value should not be less than from 500")
    @DecimalMax(value = "12000.50", message = "Maximum value should not exceed from 12000.5")
    private BigDecimal loanAmount;
}
