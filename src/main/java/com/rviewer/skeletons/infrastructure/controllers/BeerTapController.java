package com.rviewer.skeletons.infrastructure.controllers;

import com.rviewer.skeletons.application.request.PostCreateDispenserRequest;
import com.rviewer.skeletons.application.request.PutSetDispenserStatusRequest;
import com.rviewer.skeletons.application.response.GetDispenserSpendingResponse;
import com.rviewer.skeletons.application.response.PostCreateDispenserResponse;
import com.rviewer.skeletons.application.service.DispenserApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispenser")
public class BeerTapController {

    @Autowired
    private DispenserApplicationService dispenserApplicationService;

    @GetMapping("/{id}/spending")
    public ResponseEntity<GetDispenserSpendingResponse> getSpending(@PathVariable String id) {
        return dispenserApplicationService.getSpending(id);
    }

    @PutMapping("/{id}/status")
    public ResponseEntity<Void> setDispenserStatus(@PathVariable String id, @RequestBody PutSetDispenserStatusRequest request) {
        return dispenserApplicationService.setDispenserStatus(id, request);
    }

    @PostMapping
    public ResponseEntity<PostCreateDispenserResponse> createDispenser(@RequestBody PostCreateDispenserRequest request) {
        return dispenserApplicationService.createDispenser(request);
    }
}
