package com.rviewer.skeletons.infrastructure.controllers;

import com.rviewer.skeletons.domain.exception.BeerTapDispenserException;
import com.rviewer.skeletons.domain.exception.DispenserDoesNotExistException;
import com.rviewer.skeletons.domain.exception.DispenserIsAlreadyInThatStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BeerTapDispenserException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<Void> handleGenericException() {
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(DispenserDoesNotExistException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Void> handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DispenserIsAlreadyInThatStateException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public ResponseEntity<Void> handleBadStateException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
