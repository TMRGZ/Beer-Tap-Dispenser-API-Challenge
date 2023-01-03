package unit.com.rviewer.skeletons.application.request;

import com.rviewer.skeletons.application.request.PutSetDispenserStatusRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

class PutSetDispenserStatusRequestUnitTest {

    @Test
    void creationTest() {
        PutSetDispenserStatusRequest request = new PutSetDispenserStatusRequest();
        request.setStatus("TEST");
        request.setUpdatedAt(new Date());

        Assertions.assertNotNull(request);
        Assertions.assertNotNull(request.getStatus());
        Assertions.assertNotNull(request.getUpdatedAt());
    }
}
