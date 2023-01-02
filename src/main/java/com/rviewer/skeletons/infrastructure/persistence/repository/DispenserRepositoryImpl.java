package com.rviewer.skeletons.infrastructure.persistence.repository;

import com.rviewer.skeletons.domain.model.Dispenser;
import com.rviewer.skeletons.domain.repository.DispenserRepository;
import com.rviewer.skeletons.infrastructure.mapper.DispenserMapper;
import com.rviewer.skeletons.infrastructure.persistence.dao.DispenserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DispenserRepositoryImpl implements DispenserRepository {

    @Autowired
    private JpaDispenserRepository jpaDispenserRepository;

    @Autowired
    private DispenserMapper dispenserMapper;


    @Override
    public Optional<Dispenser> findById(String id) {
        Dispenser dispenser = null;

        Optional<DispenserDao> dispenserDaoOptional = jpaDispenserRepository.findById(id);

        if (dispenserDaoOptional.isPresent()) {
            dispenser = dispenserMapper.map(dispenserDaoOptional.get());
        }

        return Optional.ofNullable(dispenser);
    }

    @Override
    public Dispenser save(Dispenser dispenser) {
        DispenserDao dispenserDao = dispenserMapper.map(dispenser);
        DispenserDao savedDispenser = jpaDispenserRepository.save(dispenserDao);
        return dispenserMapper.map(savedDispenser);
    }
}
