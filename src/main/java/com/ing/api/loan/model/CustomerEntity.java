package com.ing.api.loan.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "ING_CUSTOMER")
public class CustomerEntity {

    @Id
    @Column(name = "customer_id", nullable = false, updatable = false)
    private Long customerId;

    @Column(name = "customer_full_name")
    private String customerFullName;

    @OneToMany(targetEntity = LoanEntity.class, cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id", nullable = false, referencedColumnName = "customer_id", updatable = false)
    private List<LoanEntity> loanRequestDtos;


}
