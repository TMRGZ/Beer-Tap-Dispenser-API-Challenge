package unit.com.rviewer.skeletons.application.request;

import com.rviewer.skeletons.application.request.PostCreateDispenserRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PostCreateDispenserRequestUnitTest {

    @Test
    void creationTest() {
        PostCreateDispenserRequest request = new PostCreateDispenserRequest();
        request.setFlowVolume(100.0);

        Assertions.assertNotNull(request);
        Assertions.assertNotNull(request.getFlowVolume());
    }
}
