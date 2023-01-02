package com.rviewer.skeletons.infrastructure.mapper;

import com.rviewer.skeletons.domain.model.Dispenser;
import com.rviewer.skeletons.domain.model.DispenserHistory;
import com.rviewer.skeletons.infrastructure.persistence.dao.DispenserDao;
import com.rviewer.skeletons.infrastructure.persistence.dao.DispenserHistoryDao;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
public class DispenserMapper {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private DispenserHistoryMapper dispenserHistoryMapper;

    @PostConstruct
    public void setup() {
        setupDomainToDao();
        setupDaoToDomain();
    }

    private void setupDaoToDomain() {
        TypeMap<DispenserDao, Dispenser> daoToDomainMapper = this.modelMapper.createTypeMap(DispenserDao.class, Dispenser.class);
        Converter<List<DispenserHistoryDao>, List<DispenserHistory>> daoToDomainConverter = c -> dispenserHistoryMapper.map(c.getSource().toArray(new DispenserHistoryDao[0]));
        daoToDomainMapper.addMappings(
                mapper -> mapper.using(daoToDomainConverter).map(DispenserDao::getDispenserHistory, Dispenser::setDispenserHistory)
        );
    }

    private void setupDomainToDao() {
        TypeMap<Dispenser, DispenserDao> domainToDaoMapper = this.modelMapper.createTypeMap(Dispenser.class, DispenserDao.class);
        Converter<List<DispenserHistory>, List<DispenserHistoryDao>> domainToDaoConverter = c -> dispenserHistoryMapper.map(c.getSource().toArray(new DispenserHistory[0]));
        domainToDaoMapper.addMappings(
                mapper -> mapper.using(domainToDaoConverter).map(Dispenser::getDispenserHistory, DispenserDao::setDispenserHistory)
        );
    }

    public DispenserDao map(Dispenser dispenser) {
        return modelMapper.map(dispenser, DispenserDao.class);
    }

    public Dispenser map(DispenserDao dispenser) {
        return modelMapper.map(dispenser, Dispenser.class);
    }

}
