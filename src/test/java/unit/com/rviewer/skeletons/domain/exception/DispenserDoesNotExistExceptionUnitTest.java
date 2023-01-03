package unit.com.rviewer.skeletons.domain.exception;

import com.rviewer.skeletons.domain.exception.DispenserDoesNotExistException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DispenserDoesNotExistExceptionUnitTest {

    @Test
    void creationTest() {
        DispenserDoesNotExistException exception = new DispenserDoesNotExistException();
        Assertions.assertNotNull(exception);
    }
}
