package unit.com.rviewer.skeletons.application.service.impl;

import com.rviewer.skeletons.application.request.PostCreateDispenserRequest;
import com.rviewer.skeletons.application.request.PutSetDispenserStatusRequest;
import com.rviewer.skeletons.application.response.GetDispenserSpendingResponse;
import com.rviewer.skeletons.application.response.PostCreateDispenserResponse;
import com.rviewer.skeletons.application.service.impl.DispenserApplicationServiceImpl;
import com.rviewer.skeletons.domain.model.Dispenser;
import com.rviewer.skeletons.domain.model.DispenserHistory;
import com.rviewer.skeletons.domain.services.DispenserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.Date;

@ExtendWith(MockitoExtension.class)
class DispenserApplicationServiceImplUnitTest {

    @InjectMocks
    private DispenserApplicationServiceImpl dispenserApplicationService;

    @Mock
    private DispenserService dispenserService;

    @Test
    void getSpendingUnitTest() {
        Dispenser dispenser = new Dispenser();
        dispenser.setFlowVolume(1.0);
        DispenserHistory dispenserHistory = new DispenserHistory();
        dispenserHistory.setOpenedAt(new Date());
        dispenserHistory.setClosedAt(new Date());
        dispenserHistory.setTotalSpent(1.0);
        dispenser.setDispenserHistory(Collections.singletonList(dispenserHistory));

        Mockito.when(dispenserService.getSpending(Mockito.any())).thenReturn(dispenser);

        ResponseEntity<GetDispenserSpendingResponse> response = dispenserApplicationService.getSpending("TEST");

        Assertions.assertNotNull(response);

        Assertions.assertNotNull(response.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Assertions.assertNotNull(response.getBody());
    }

    @Test
    void setSpendingUnitTest() {
        PutSetDispenserStatusRequest request = new PutSetDispenserStatusRequest();
        request.setStatus("STATUS");
        request.setUpdatedAt(new Date());

        ResponseEntity<Void> response = dispenserApplicationService.setDispenserStatus("TEST", request);

        Mockito.verify(dispenserService).setDispenserStatus(Mockito.any(), Mockito.any());

        Assertions.assertNotNull(response);

        Assertions.assertNotNull(response.getStatusCode());
        Assertions.assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());

        Assertions.assertNull(response.getBody());
    }

    @Test
    void createDispenserUnitTest() {
        PostCreateDispenserRequest request = new PostCreateDispenserRequest();
        request.setFlowVolume(10.0);

        Dispenser dispenser = new Dispenser();
        dispenser.setFlowVolume(request.getFlowVolume());
        dispenser.setId("ID");

        Mockito.when(dispenserService.createDispenser(Mockito.any())).thenReturn(dispenser);

        ResponseEntity<PostCreateDispenserResponse> response = dispenserApplicationService.createDispenser(request);

        Mockito.verify(dispenserService).createDispenser(Mockito.any());

        Assertions.assertNotNull(response);

        Assertions.assertNotNull(response.getStatusCode());
        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        Assertions.assertNotNull(response.getBody());
        PostCreateDispenserResponse createDispenserResponse = response.getBody();
        Assertions.assertEquals(request.getFlowVolume(), createDispenserResponse.getFlowVolume());
    }
}
