package unit.com.rviewer.skeletons.application.response;

import com.rviewer.skeletons.application.response.GetDispenserSpendingResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class GetDispenserSpendingResponseUnitTest {

    @Test
    void creationTest() {
        GetDispenserSpendingResponse response = new GetDispenserSpendingResponse();
        response.setAmount(100.0);
        response.setUsages(Collections.emptyList());

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getAmount());
        Assertions.assertNotNull(response.getUsages());
    }
}
