package com.ing.api.loan;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Priti Kumari
 */
@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Customer Loan API", description = "Customer and Loan Request information"))
public class LoanRequestApplication {

    public static void main(String[] args) {
        SpringApplication.run(LoanRequestApplication.class, args);
    }

}
