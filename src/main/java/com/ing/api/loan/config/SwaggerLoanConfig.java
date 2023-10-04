package com.ing.api.loan.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

/**
 * @author Priti Kumari
 */
@Configuration
public class SwaggerLoanConfig {
    @Bean
    public OpenAPI api() {
        return new OpenAPI()
                .servers(Collections.singletonList(new Server().url("")))
                .info(new Info().title("Api for loan Service")
                        .version("1.0"));
    }
}
