package com.coolmushroom.retail.config;

import com.coolmushroom.retail.core.exception.InvalidModel;
import com.coolmushroom.retail.core.exception.PriceNotFound;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.bind.support.WebExchangeBindException;

@RestControllerAdvice
public class ErrorConf {

    private static final Logger LOGGER = LoggerFactory.getLogger(ErrorConf.class);

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String serverExceptionHandler(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ex.getMessage();
    }

    @ExceptionHandler(PriceNotFound.class)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public String priceNotFoundHandler(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ex.getMessage();
    }

    @ExceptionHandler(InvalidModel.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidModelHandler(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ex.getMessage();
    }

    @ExceptionHandler(WebExchangeBindException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String bindExceptionHandler(Exception ex) {
        LOGGER.error(ex.getMessage(), ex);
        return ex.getMessage();
    }

}
