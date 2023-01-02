package com.rviewer.skeletons.infrastructure.controllers;

import com.rviewer.skeletons.domain.exception.BeerTapDispenserException;
import com.rviewer.skeletons.domain.exception.DispenserDoesNotExistException;
import com.rviewer.skeletons.domain.exception.DispenserIsAlreadyInThatStateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionHandlerController {

    @ExceptionHandler(BeerTapDispenserException.class)
    public ResponseEntity<Void> handleGenericException() {
        return ResponseEntity.internalServerError().build();
    }

    @ExceptionHandler(DispenserDoesNotExistException.class)
    public ResponseEntity<Void> handleNotFoundException() {
        return ResponseEntity.notFound().build();
    }

    @ExceptionHandler(DispenserIsAlreadyInThatStateException.class)
    public ResponseEntity<Void> handleBadStateException() {
        return ResponseEntity.status(HttpStatus.CONFLICT).build();
    }
}
