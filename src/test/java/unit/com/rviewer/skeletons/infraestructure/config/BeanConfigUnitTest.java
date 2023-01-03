package unit.com.rviewer.skeletons.infraestructure.config;

import com.rviewer.skeletons.domain.repository.DispenserRepository;
import com.rviewer.skeletons.domain.services.DispenserService;
import com.rviewer.skeletons.infrastructure.config.BeanConfig;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class BeanConfigUnitTest {

    @InjectMocks
    private BeanConfig beanConfig;

    @Test
    void dispenserServiceUnitTest() {
        DispenserService dispenserService = beanConfig.dispenserService(Mockito.mock(DispenserRepository.class));
        Assertions.assertNotNull(dispenserService);
    }
}
