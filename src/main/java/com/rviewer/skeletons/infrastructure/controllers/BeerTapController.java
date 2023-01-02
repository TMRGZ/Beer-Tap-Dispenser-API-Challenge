package com.rviewer.skeletons.infrastructure.controllers;

import com.rviewer.skeletons.application.request.PostCreateDispenserRequest;
import com.rviewer.skeletons.application.request.PutSetDispenserStatusRequest;
import com.rviewer.skeletons.application.response.GetDispenserSpendingResponse;
import com.rviewer.skeletons.application.response.PostCreateDispenserResponse;
import com.rviewer.skeletons.application.response.PutSetDispenserStatusResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/dispenser")
public class BeerTapController {

    @GetMapping("/{id}/spending")
    public GetDispenserSpendingResponse getSpending(@PathVariable String id) {
        return new GetDispenserSpendingResponse();
    }

    @PutMapping("/{id}/status")
    public PutSetDispenserStatusResponse setDispenserStatus(@PathVariable String id, @RequestBody PutSetDispenserStatusRequest request) {
        return new PutSetDispenserStatusResponse();
    }

    @PostMapping
    public PostCreateDispenserResponse createDispenser(@RequestBody PostCreateDispenserRequest request) {
        return new PostCreateDispenserResponse();
    }
}
