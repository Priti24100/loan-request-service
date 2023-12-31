package com.ing.api.loan.exception;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Setter
@Getter
public class ApiError {

    private int statusCode;
    private String errorMessage;
}
