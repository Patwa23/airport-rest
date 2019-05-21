package com.travix.medusa.busyflights.handler;

import com.travix.medusa.busyflights.exception.BusyFlightApiError;
import com.travix.medusa.busyflights.exception.BusyFlightIllegalException;
import com.travix.medusa.busyflights.exception.BusyFlightNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

/**
 * Busy Flight Exception Handler holds all the Custom exception thrown by Service Class
 *
 * Created by Prakash Patwa on 20/05/2019
 */
@RestControllerAdvice
public class BusyFlightExceptionalHandler {

    @ExceptionHandler(BusyFlightNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final BusyFlightApiError handleNotFound(Exception ex) {
        return new BusyFlightApiError(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BusyFlightIllegalException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final BusyFlightApiError handleIllegalException(BusyFlightApiError ex) {
        return new BusyFlightApiError(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler({ ConstraintViolationException.class })
    public BusyFlightApiError handleConstraintViolation(ConstraintViolationException ex) {
        return new BusyFlightApiError(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
