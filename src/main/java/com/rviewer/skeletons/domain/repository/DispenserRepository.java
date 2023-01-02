package com.rviewer.skeletons.domain.repository;

import com.rviewer.skeletons.domain.model.Dispenser;

import java.util.Optional;

public interface DispenserRepository {

    Optional<Dispenser> findById(String id);

    Dispenser save(Dispenser dispenser);

}
