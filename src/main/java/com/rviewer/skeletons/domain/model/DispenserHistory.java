package com.rviewer.skeletons.domain.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DispenserHistory {

    private Long id;

    private Double totalSpent;

    private Date openedAt;

    private Date closedAt;
}
