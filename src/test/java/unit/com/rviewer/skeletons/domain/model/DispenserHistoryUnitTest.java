package unit.com.rviewer.skeletons.domain.model;

import com.rviewer.skeletons.domain.model.DispenserHistory;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

class DispenserHistoryUnitTest {

    @Test
    void creationTest() {
        DispenserHistory dispenserHistory = new DispenserHistory();
        dispenserHistory.setId("ID");
        dispenserHistory.setTotalSpent(1.0);
        dispenserHistory.setOpenedAt(new Date());
        dispenserHistory.setClosedAt(new Date());

        Assertions.assertNotNull(dispenserHistory);
        Assertions.assertNotNull(dispenserHistory.getId());
        Assertions.assertNotNull(dispenserHistory.getTotalSpent());
        Assertions.assertNotNull(dispenserHistory.getOpenedAt());
        Assertions.assertNotNull(dispenserHistory.getClosedAt());
    }
}
