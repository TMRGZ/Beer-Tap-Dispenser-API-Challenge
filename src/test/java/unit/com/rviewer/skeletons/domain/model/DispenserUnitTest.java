package unit.com.rviewer.skeletons.domain.model;

import com.rviewer.skeletons.domain.model.Dispenser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class DispenserUnitTest {

    @Test
    void creationTest() {
        Dispenser dispenser = new Dispenser();
        dispenser.setId("TEST");
        dispenser.setFlowVolume(1.0);
        dispenser.setDispenserHistory(Collections.emptyList());

        Assertions.assertNotNull(dispenser);
        Assertions.assertNotNull(dispenser.getId());
        Assertions.assertNotNull(dispenser.getFlowVolume());
        Assertions.assertNotNull(dispenser.getDispenserHistory());
    }
}
