package unit.com.rviewer.skeletons.application.response;

import com.rviewer.skeletons.application.response.DispenserSpendingUsage;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

class DispenserSpendingUsageUnitTest {

    @Test
    void creationTest() {
        DispenserSpendingUsage dispenserSpendingUsage = new DispenserSpendingUsage();
        dispenserSpendingUsage.setOpenedAt(new Date());
        dispenserSpendingUsage.setClosedAt(new Date());
        dispenserSpendingUsage.setFlowVolume(100.0);
        dispenserSpendingUsage.setTotalSpent(100.0);

        Assertions.assertNotNull(dispenserSpendingUsage);
        Assertions.assertNotNull(dispenserSpendingUsage.getOpenedAt());
        Assertions.assertNotNull(dispenserSpendingUsage.getClosedAt());
        Assertions.assertNotNull(dispenserSpendingUsage.getFlowVolume());
        Assertions.assertNotNull(dispenserSpendingUsage.getTotalSpent());
    }
}
