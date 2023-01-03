package unit.com.rviewer.skeletons.infraestructure.controllers;

import com.rviewer.skeletons.application.request.PostCreateDispenserRequest;
import com.rviewer.skeletons.application.request.PutSetDispenserStatusRequest;
import com.rviewer.skeletons.application.service.DispenserApplicationService;
import com.rviewer.skeletons.infrastructure.controllers.BeerTapController;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BeerTapControllerUnitTest {

    @InjectMocks
    private BeerTapController beerTapController;

    @Mock
    private DispenserApplicationService dispenserApplicationService;

    @Test
    void getSpendingUnitTest() {
        beerTapController.getSpending("TEST");
        Mockito.verify(dispenserApplicationService).getSpending(Mockito.any());
    }

    @Test
    void setDispenserStatusUnitTest() {
        beerTapController.setDispenserStatus("TEST", new PutSetDispenserStatusRequest());
        Mockito.verify(dispenserApplicationService).setDispenserStatus(Mockito.any(), Mockito.any());
    }

    @Test
    void createDispenserUnitTest() {
        beerTapController.createDispenser(new PostCreateDispenserRequest());
        Mockito.verify(dispenserApplicationService).createDispenser(Mockito.any());
    }
}
