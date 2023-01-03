package unit.com.rviewer.skeletons.infraestructure.controllers;

import com.rviewer.skeletons.infrastructure.controllers.ExceptionHandlerController;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@ExtendWith(MockitoExtension.class)
class ExceptionHandlerControllerUnitTest {

    @InjectMocks
    private ExceptionHandlerController exceptionHandlerController;

    @Test
    void handleGenericExceptionUnitTest() {
        ResponseEntity<Void> response = exceptionHandlerController.handleGenericException();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getStatusCode());
        Assertions.assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    void handleNotFoundExceptionUnitTest() {
        ResponseEntity<Void> response = exceptionHandlerController.handleNotFoundException();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getStatusCode());
        Assertions.assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    void handleBadStateExceptionUnitTest() {
        ResponseEntity<Void> response = exceptionHandlerController.handleBadStateException();

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getStatusCode());
        Assertions.assertEquals(HttpStatus.CONFLICT, response.getStatusCode());
    }
}
