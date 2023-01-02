package com.rviewer.skeletons.application.response;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class GetDispenserSpendingResponse {

    private Double amount;

    private List<DispenserSpendingUsage> usages;

}
