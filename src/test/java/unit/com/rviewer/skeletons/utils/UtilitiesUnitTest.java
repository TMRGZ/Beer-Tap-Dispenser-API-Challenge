package unit.com.rviewer.skeletons.utils;

import com.rviewer.skeletons.utils.Utilities;
import org.awaitility.Awaitility;
import org.awaitility.Durations;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

class UtilitiesUnitTest {

    @Test
    void calculateTotalSpentUnitTest() {
        Date open = new Date();
        Awaitility.await().pollDelay(Durations.FIVE_SECONDS).until(() -> true);
        Date close = new Date();

        double totalSpent = Utilities.calculateTotalSpent(1.0, open, close);
        Assertions.assertNotEquals(0, totalSpent);
    }
}
