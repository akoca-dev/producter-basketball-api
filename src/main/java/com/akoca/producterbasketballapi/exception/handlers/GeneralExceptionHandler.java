package com.akoca.producterbasketballapi.exception.handlers;

import com.akoca.producterbasketballapi.exception.unchecked.UnknownErrorException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GeneralExceptionHandler {

    @ExceptionHandler({Exception.class, RuntimeException.class})
    public UnknownErrorException handleUnCaughtException() {
        return new UnknownErrorException("An unknown error happened while processing the request");
    }
}
