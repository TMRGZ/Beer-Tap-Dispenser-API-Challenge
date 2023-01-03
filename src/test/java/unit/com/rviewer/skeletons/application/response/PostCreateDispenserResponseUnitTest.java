package unit.com.rviewer.skeletons.application.response;

import com.rviewer.skeletons.application.response.PostCreateDispenserResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PostCreateDispenserResponseUnitTest {

    @Test
    void creationTest() {
        PostCreateDispenserResponse response = new PostCreateDispenserResponse();
        response.setId("TEST");
        response.setFlowVolume(10.0);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertNotNull(response.getFlowVolume());
    }
}
