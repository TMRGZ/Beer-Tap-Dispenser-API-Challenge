package com.rviewer.skeletons.domain.services;

import com.rviewer.skeletons.domain.model.Dispenser;
import com.rviewer.skeletons.domain.model.DispenserAction;

public interface DispenserService {

    Dispenser getSpending(String id);

    Dispenser createDispenser(Dispenser newDispenser);

    void setDispenserStatus(String id, DispenserAction dispenserAction);

}
