package com.rviewer.skeletons.application.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class DispenserSpendingUsage {

    private Date openedAt;

    private Date closedAt;

    private Double flowVolume;

    private Double totalSpent;

}
