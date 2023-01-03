package unit.com.rviewer.skeletons.infraestructure.mapper;

import com.rviewer.skeletons.domain.model.DispenserHistory;
import com.rviewer.skeletons.infrastructure.mapper.DispenserHistoryMapper;
import com.rviewer.skeletons.infrastructure.persistence.dao.DispenserHistoryDao;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

@ExtendWith(MockitoExtension.class)
class DispenserHistoryMapperUnitTest {

    @InjectMocks
    private DispenserHistoryMapper dispenserHistoryMapper;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void mapDomainUnitTest() {
        dispenserHistoryMapper.map(new DispenserHistory());
        Mockito.verify(modelMapper).map(Mockito.any(DispenserHistory.class), Mockito.any());
    }

    @Test
    void mapDomainListUnitTest() {
        dispenserHistoryMapper.map(new DispenserHistory[]{new DispenserHistory()});
        Mockito.verify(modelMapper).map(Mockito.any(DispenserHistory.class), Mockito.any());
    }

    @Test
    void mapDaoUnitTest() {
        dispenserHistoryMapper.map(new DispenserHistoryDao());
        Mockito.verify(modelMapper).map(Mockito.any(DispenserHistoryDao.class), Mockito.any());
    }

    @Test
    void mapDaoListUnitTest() {
        dispenserHistoryMapper.map(new DispenserHistoryDao[]{new DispenserHistoryDao()});
        Mockito.verify(modelMapper).map(Mockito.any(DispenserHistoryDao.class), Mockito.any());
    }
}
