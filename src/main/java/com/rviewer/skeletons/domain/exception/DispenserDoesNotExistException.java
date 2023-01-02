package com.rviewer.skeletons.domain.exception;

public class DispenserDoesNotExistException extends BeerTapDispenserException {

    public DispenserDoesNotExistException() {
        super("The requested dispenser does not exist");
    }
}
