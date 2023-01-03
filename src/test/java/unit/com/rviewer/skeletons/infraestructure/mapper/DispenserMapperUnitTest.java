package unit.com.rviewer.skeletons.infraestructure.mapper;

import com.rviewer.skeletons.domain.model.Dispenser;
import com.rviewer.skeletons.infrastructure.mapper.DispenserHistoryMapper;
import com.rviewer.skeletons.infrastructure.mapper.DispenserMapper;
import com.rviewer.skeletons.infrastructure.persistence.dao.DispenserDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class DispenserMapperUnitTest {

    @InjectMocks
    private DispenserMapper dispenserMapper;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private DispenserHistoryMapper dispenserHistoryMapper;

    @Test
    void mapDomainUnitTest() {
        dispenserMapper.map(new Dispenser());
        Mockito.verify(modelMapper).map(Mockito.any(Dispenser.class), Mockito.any());
    }

    @Test
    void mapDaoUnitTest() {
        dispenserMapper.map(new DispenserDao());
        Mockito.verify(modelMapper).map(Mockito.any(DispenserDao.class), Mockito.any());
    }
}
