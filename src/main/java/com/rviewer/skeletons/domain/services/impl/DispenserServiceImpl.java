package com.rviewer.skeletons.domain.services.impl;

import com.rviewer.skeletons.domain.exception.BeerTapDispenserException;
import com.rviewer.skeletons.domain.exception.DispenserDoesNotExistException;
import com.rviewer.skeletons.domain.exception.DispenserIsAlreadyInThatStateException;
import com.rviewer.skeletons.domain.model.Dispenser;
import com.rviewer.skeletons.domain.model.DispenserAction;
import com.rviewer.skeletons.domain.model.DispenserHistory;
import com.rviewer.skeletons.domain.repository.DispenserRepository;
import com.rviewer.skeletons.domain.services.DispenserService;
import com.rviewer.skeletons.utils.Constants;
import com.rviewer.skeletons.utils.Utilities;

import java.util.Comparator;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;

public class DispenserServiceImpl implements DispenserService {

    private final DispenserRepository dispenserRepository;

    public DispenserServiceImpl(DispenserRepository dispenserRepository) {
        this.dispenserRepository = dispenserRepository;
    }

    @Override
    public Dispenser getSpending(String id) {
        return dispenserRepository.findById(id).orElseThrow(DispenserDoesNotExistException::new);
    }

    @Override
    public Dispenser createDispenser(Dispenser newDispenser) {
        return dispenserRepository.save(newDispenser);
    }

    @Override
    public void setDispenserStatus(String id, DispenserAction dispenserAction) {
        Dispenser dispenser = dispenserRepository.findById(id).orElseThrow(DispenserDoesNotExistException::new);

        switch (dispenserAction.getStatus().toUpperCase()) {
            case Constants.DISPENSER_OPENED_STATUS -> openDispenser(dispenser, dispenserAction.getUpdatedAt());
            case Constants.DISPENSER_CLOSED_STATUS -> closeDispenser(dispenser, dispenserAction.getUpdatedAt());
            default -> {}
        }
    }

    private void closeDispenser(Dispenser dispenser, Date closeDate) {
        Optional<DispenserHistory> dispenserHistoryOptional = dispenser.getDispenserHistory().stream()
                .max(Comparator.comparing(DispenserHistory::getOpenedAt));

        DispenserHistory dispenserHistory = dispenserHistoryOptional.orElseThrow(BeerTapDispenserException::new);

        if (dispenserHistory.getClosedAt() != null) {
            throw new DispenserIsAlreadyInThatStateException();
        }

        dispenserHistory.setClosedAt(closeDate);
        dispenserHistory.setTotalSpent(
                Utilities.calculateTotalSpent(dispenser.getFlowVolume(), dispenserHistory.getOpenedAt(), dispenserHistory.getClosedAt()));

        dispenserRepository.save(dispenser);
    }

    private void openDispenser(Dispenser dispenser, Date openDate) {
        Optional<DispenserHistory> dispenserHistoryOptional = dispenser.getDispenserHistory().stream()
                .filter(history -> Objects.isNull(history.getClosedAt()))
                .max(Comparator.comparing(DispenserHistory::getOpenedAt));

        if (dispenserHistoryOptional.isPresent()) {
            throw new DispenserIsAlreadyInThatStateException();
        }

        DispenserHistory openDispenserHistory = new DispenserHistory();
        openDispenserHistory.setOpenedAt(openDate);

        dispenser.getDispenserHistory().add(openDispenserHistory);

        dispenserRepository.save(dispenser);
    }
}
