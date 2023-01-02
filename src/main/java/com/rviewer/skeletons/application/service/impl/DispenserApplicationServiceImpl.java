package com.rviewer.skeletons.application.service.impl;

import com.rviewer.skeletons.application.request.PostCreateDispenserRequest;
import com.rviewer.skeletons.application.request.PutSetDispenserStatusRequest;
import com.rviewer.skeletons.application.response.DispenserSpendingUsage;
import com.rviewer.skeletons.application.response.GetDispenserSpendingResponse;
import com.rviewer.skeletons.application.response.PostCreateDispenserResponse;
import com.rviewer.skeletons.application.service.DispenserApplicationService;
import com.rviewer.skeletons.domain.model.Dispenser;
import com.rviewer.skeletons.domain.model.DispenserAction;
import com.rviewer.skeletons.domain.services.DispenserService;
import com.rviewer.skeletons.utils.Utilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DispenserApplicationServiceImpl implements DispenserApplicationService {

    @Autowired
    private DispenserService dispenserService;

    @Override
    public ResponseEntity<GetDispenserSpendingResponse> getSpending(String id) {

        Dispenser spending = dispenserService.getSpending(id);
        GetDispenserSpendingResponse response = new GetDispenserSpendingResponse();

        List<DispenserSpendingUsage> usages = spending.getDispenserHistory().stream()
                .map(dispenserHistory -> {
                    DispenserSpendingUsage usage = new DispenserSpendingUsage();
                    usage.setOpenedAt(dispenserHistory.getOpenedAt());
                    usage.setClosedAt(dispenserHistory.getClosedAt());
                    usage.setFlowVolume(spending.getFlowVolume());
                    usage.setTotalSpent(
                            dispenserHistory.getTotalSpent() != null
                                    ? dispenserHistory.getTotalSpent()
                                    : Utilities.calculateTotalSpent(spending.getFlowVolume(), dispenserHistory.getOpenedAt(), new Date()));

                    return usage;
                }).collect(Collectors.toList());

        double amount = usages.stream()
                .mapToDouble(DispenserSpendingUsage::getTotalSpent)
                .sum();

        response.setAmount(amount);
        response.setUsages(usages);

        return ResponseEntity.ok(response);
    }

    @Override
    @Transactional
    public ResponseEntity<Void> setDispenserStatus(String id, PutSetDispenserStatusRequest request) {
        DispenserAction dispenserAction = new DispenserAction();
        dispenserAction.setStatus(request.getStatus());
        dispenserAction.setUpdatedAt(request.getUpdatedAt());

        dispenserService.setDispenserStatus(id, dispenserAction);
        return ResponseEntity.accepted().build();
    }

    @Override
    @Transactional
    public ResponseEntity<PostCreateDispenserResponse> createDispenser(PostCreateDispenserRequest request) {
        Dispenser newDispenser = new Dispenser();
        newDispenser.setFlowVolume(request.getFlowVolume());

        Dispenser savedDispenser = dispenserService.createDispenser(newDispenser);

        PostCreateDispenserResponse response = new PostCreateDispenserResponse();
        response.setId(savedDispenser.getId());
        response.setFlowVolume(savedDispenser.getFlowVolume());

        return ResponseEntity.ok(response);
    }
}
