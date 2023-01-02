package com.rviewer.skeletons.infrastructure.mapper;

import com.rviewer.skeletons.domain.model.DispenserHistory;
import com.rviewer.skeletons.infrastructure.persistence.dao.DispenserHistoryDao;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DispenserHistoryMapper {

    @Autowired
    private ModelMapper modelMapper;

    public DispenserHistoryDao map(DispenserHistory dispenserHistory) {
        return modelMapper.map(dispenserHistory, DispenserHistoryDao.class);
    }

    public List<DispenserHistoryDao> map(DispenserHistory[] dispenserHistory) {
        return Arrays.stream(dispenserHistory).map(this::map).collect(Collectors.toList());
    }


    public DispenserHistory map(DispenserHistoryDao dispenserHistory) {
        return modelMapper.map(dispenserHistory, DispenserHistory.class);
    }

    public List<DispenserHistory> map(DispenserHistoryDao[] dispenserHistory) {
        return Arrays.stream(dispenserHistory).map(this::map).collect(Collectors.toList());
    }


}
