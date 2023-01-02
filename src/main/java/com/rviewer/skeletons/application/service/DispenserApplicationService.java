package com.rviewer.skeletons.application.service;

import com.rviewer.skeletons.application.request.PostCreateDispenserRequest;
import com.rviewer.skeletons.application.request.PutSetDispenserStatusRequest;
import com.rviewer.skeletons.application.response.GetDispenserSpendingResponse;
import com.rviewer.skeletons.application.response.PostCreateDispenserResponse;
import org.springframework.http.ResponseEntity;

public interface DispenserApplicationService {

    ResponseEntity<GetDispenserSpendingResponse> getSpending(String id);

    ResponseEntity<Void> setDispenserStatus(String id, PutSetDispenserStatusRequest request);

    ResponseEntity<PostCreateDispenserResponse> createDispenser(PostCreateDispenserRequest request);

}
