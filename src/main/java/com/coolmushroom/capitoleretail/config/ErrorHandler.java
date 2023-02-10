package com.coolmushroom.capitoleretail.config;

import com.coolmushroom.capitoleretail.core.exception.ApiError;
import com.coolmushroom.capitoleretail.core.exception.PriceNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class ErrorHandler {
    private static final Logger log = LoggerFactory.getLogger(ErrorHandler.class);

    @ExceptionHandler(PriceNotFound.class)
    public ResponseEntity<ApiError> priceNotFound(HttpServletRequest req, Exception ex) {
        log.error("Requested price was not found: {}, uri {}, message {}", ex.getClass().getSimpleName(), req.getRequestURI(), ex.getMessage());
        ApiError apiError = new ApiError(ex.getClass().getSimpleName(), ex.getMessage(), HttpStatus.NO_CONTENT.value());
        return ResponseEntity.status(apiError.getStatus()).body(apiError);
    }
}
