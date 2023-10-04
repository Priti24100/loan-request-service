package com.ing.api.loan.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = CustomerNotFoundException.class)
    public ResponseEntity<ApiError> customerNotFound(final CustomerNotFoundException exception) {
        ApiError apiError = new ApiError(HttpStatus.NOT_FOUND.value(), exception.getMessage());
        return new ResponseEntity<>(apiError, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> argumentTypeMismatchException(MethodArgumentTypeMismatchException ex) {
        ApiError errorMessage = new ApiError();
        errorMessage.setStatusCode(HttpStatus.BAD_REQUEST.value());
        errorMessage.setErrorMessage(ex.getMessage());
        return new ResponseEntity<>(errorMessage, HttpStatus.BAD_REQUEST);
    }


}
