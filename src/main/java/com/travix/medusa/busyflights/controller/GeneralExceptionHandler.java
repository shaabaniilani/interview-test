package com.travix.medusa.busyflights.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class GeneralExceptionHandler {
    @ExceptionHandler({ConstraintViolationException.class})
    @ResponseBody
    public ResponseEntity<String> handleEntityException(ConstraintViolationException ex) {
        List<String> messages = new ArrayList<>();
        if(ex.getConstraintViolations() != null) {
            for(ConstraintViolation cve : ex.getConstraintViolations()) {
                messages.add(cve.getMessage());
            }
        }
        return new ResponseEntity(messages, HttpStatus.BAD_REQUEST);
    }
}
