package unit.com.rviewer.skeletons.domain.services.impl;

import com.rviewer.skeletons.domain.exception.BeerTapDispenserException;
import com.rviewer.skeletons.domain.exception.DispenserDoesNotExistException;
import com.rviewer.skeletons.domain.exception.DispenserIsAlreadyInThatStateException;
import com.rviewer.skeletons.domain.model.Dispenser;
import com.rviewer.skeletons.domain.model.DispenserAction;
import com.rviewer.skeletons.domain.model.DispenserHistory;
import com.rviewer.skeletons.domain.repository.DispenserRepository;
import com.rviewer.skeletons.domain.services.impl.DispenserServiceImpl;
import com.rviewer.skeletons.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DispenserServiceImplUnitTest {

    private DispenserServiceImpl dispenserService;

    private DispenserRepository dispenserRepository;

    @BeforeEach
    void setup() {
        this.dispenserRepository = Mockito.mock(DispenserRepository.class);
        this.dispenserService = new DispenserServiceImpl(this.dispenserRepository);
    }

    @Test
    void getSpendingUnitTest() {
        Mockito.when(dispenserRepository.findById(Mockito.anyString())).thenReturn(Optional.of(new Dispenser()));
        dispenserService.getSpending("TEST");
        Mockito.verify(dispenserRepository).findById(Mockito.anyString());
    }

    @Test
    void getSpendingButDoesNotExistExceptionUnitTest() {
        Assertions.assertThrows(DispenserDoesNotExistException.class, () -> dispenserService.getSpending("TEST"));
        Mockito.verify(dispenserRepository).findById(Mockito.anyString());
    }

    @Test
    void createDispenserUnitTest() {
        dispenserService.createDispenser(new Dispenser());
        Mockito.verify(dispenserRepository).save(Mockito.any());
    }

    @Test
    void setDispenserStatusToOpenUnitTest() {
        Dispenser dispenser = new Dispenser();
        dispenser.setDispenserHistory(new ArrayList<>());

        Mockito.when(dispenserRepository.findById(Mockito.anyString())).thenReturn(Optional.of(dispenser));

        DispenserAction dispenserAction = new DispenserAction();
        dispenserAction.setStatus(Constants.DISPENSER_OPENED_STATUS);

        dispenserService.setDispenserStatus("TEST", dispenserAction);

        Mockito.verify(dispenserRepository).save(Mockito.any());
    }

    @Test
    void setDispenserStatusToCloseUnitTest() {
        Dispenser dispenser = new Dispenser();
        ArrayList<DispenserHistory> dispenserHistory = new ArrayList<>();
        DispenserHistory history = new DispenserHistory();
        history.setOpenedAt(new Date());

        dispenserHistory.add(history);

        dispenser.setFlowVolume(1.0);
        dispenser.setDispenserHistory(dispenserHistory);

        Mockito.when(dispenserRepository.findById(Mockito.anyString())).thenReturn(Optional.of(dispenser));

        DispenserAction dispenserAction = new DispenserAction();
        dispenserAction.setStatus(Constants.DISPENSER_CLOSED_STATUS);
        dispenserAction.setUpdatedAt(new Date());

        dispenserService.setDispenserStatus("TEST", dispenserAction);

        Mockito.verify(dispenserRepository).save(Mockito.any());
    }

    @Test
    void setDispenserStatusToCloseButIsNotOpenedUnitTest() {
        Dispenser dispenser = new Dispenser();
        dispenser.setDispenserHistory(new ArrayList<>());

        Mockito.when(dispenserRepository.findById(Mockito.anyString())).thenReturn(Optional.of(dispenser));

        DispenserAction dispenserAction = new DispenserAction();
        dispenserAction.setStatus(Constants.DISPENSER_CLOSED_STATUS);

        Assertions.assertThrows(BeerTapDispenserException.class, () -> dispenserService.setDispenserStatus("TEST", dispenserAction));

        Mockito.verify(dispenserRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void setDispenserStatusToOpenButIsAlreadyOpenedExceptionUnitTest() {
        Dispenser dispenser = new Dispenser();
        List<DispenserHistory> dispenserHistory = new ArrayList<>();
        dispenserHistory.add(new DispenserHistory());

        dispenser.setDispenserHistory(dispenserHistory);

        Mockito.when(dispenserRepository.findById(Mockito.anyString())).thenReturn(Optional.of(dispenser));

        DispenserAction dispenserAction = new DispenserAction();
        dispenserAction.setStatus(Constants.DISPENSER_OPENED_STATUS);

        Assertions.assertThrows(DispenserIsAlreadyInThatStateException.class, () -> dispenserService.setDispenserStatus("TEST", dispenserAction));

        Mockito.verify(dispenserRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void setDispenserStatusToCloseButIsAlreadyClosedExceptionUnitTest() {
        Dispenser dispenser = new Dispenser();
        List<DispenserHistory> dispenserHistory = new ArrayList<>();
        DispenserHistory history = new DispenserHistory();
        history.setClosedAt(new Date());
        dispenserHistory.add(history);

        dispenser.setDispenserHistory(dispenserHistory);

        Mockito.when(dispenserRepository.findById(Mockito.anyString())).thenReturn(Optional.of(dispenser));

        DispenserAction dispenserAction = new DispenserAction();
        dispenserAction.setStatus(Constants.DISPENSER_CLOSED_STATUS);

        Assertions.assertThrows(DispenserIsAlreadyInThatStateException.class, () -> dispenserService.setDispenserStatus("TEST", dispenserAction));

        Mockito.verify(dispenserRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void setDispenserStatusButDispenserDoesNotExistUnitTest() {
        DispenserAction dispenserAction = new DispenserAction();

        Assertions.assertThrows(DispenserDoesNotExistException.class, () -> dispenserService.setDispenserStatus("TEST", dispenserAction));

        Mockito.verify(dispenserRepository).findById(Mockito.anyString());
        Mockito.verify(dispenserRepository, Mockito.never()).save(Mockito.any());
    }

}
