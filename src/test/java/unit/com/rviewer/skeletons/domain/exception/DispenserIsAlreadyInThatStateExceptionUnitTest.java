package unit.com.rviewer.skeletons.domain.exception;

import com.rviewer.skeletons.domain.exception.DispenserIsAlreadyInThatStateException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class DispenserIsAlreadyInThatStateExceptionUnitTest {

    @Test
    void creationTest() {
        DispenserIsAlreadyInThatStateException exception = new DispenserIsAlreadyInThatStateException();
        Assertions.assertNotNull(exception);
    }
}
