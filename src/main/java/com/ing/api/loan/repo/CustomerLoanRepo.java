package com.ing.api.loan.repo;

import com.ing.api.loan.model.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerLoanRepo extends JpaRepository<CustomerEntity, Long> {
    Optional<CustomerEntity> findCustomerByCustomerId(Long customerId);
}
