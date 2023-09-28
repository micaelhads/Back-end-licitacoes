package com.licitacao.exeptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class HandlerException {
    @ExceptionHandler(InvalidParameterException.class)
    public final ResponseEntity<StandardException> invalidParameter(InvalidParameterException e) {
        StandardException standardExecption = new StandardException(e.getMessage(), System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(standardExecption, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(GenericException.class)
    public final ResponseEntity<StandardException> genericException(GenericException e) {
        StandardException standardExecption = new StandardException(e.getMessage(), System.currentTimeMillis(), HttpStatus.INTERNAL_SERVER_ERROR.value());
        return new ResponseEntity<>(standardExecption, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
