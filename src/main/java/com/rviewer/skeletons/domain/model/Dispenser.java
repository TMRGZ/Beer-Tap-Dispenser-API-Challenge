package com.rviewer.skeletons.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Dispenser {

    private String id;

    private Long flowVolume;

    private List<DispenserHistory> dispenserHistory;

}
