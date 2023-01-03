package unit.com.rviewer.skeletons.domain.exception;

import com.rviewer.skeletons.domain.exception.BeerTapDispenserException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class BeerTapDispenserExceptionUnitTest {

    @Test
    void creationTest() {
        BeerTapDispenserException exception = new BeerTapDispenserException();
        Assertions.assertNotNull(exception);

        exception = new BeerTapDispenserException("TEST");
        Assertions.assertNotNull(exception);
    }
}
