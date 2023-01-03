package unit.com.rviewer.skeletons.domain.model;

import com.rviewer.skeletons.domain.model.DispenserAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

class DispenserActionUnitTest {

    @Test
    void creationTest() {
        DispenserAction dispenserAction = new DispenserAction();
        dispenserAction.setStatus("STATUS");
        dispenserAction.setUpdatedAt(new Date());

        Assertions.assertNotNull(dispenserAction);
        Assertions.assertNotNull(dispenserAction.getStatus());
        Assertions.assertNotNull(dispenserAction.getUpdatedAt());
    }
}
