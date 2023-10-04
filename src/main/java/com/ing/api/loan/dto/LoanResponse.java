package com.ing.api.loan.dto;

import lombok.*;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoanResponse {

    private Long customerId;
    private String customerFullName;
    private BigDecimal loanAmount;
}
