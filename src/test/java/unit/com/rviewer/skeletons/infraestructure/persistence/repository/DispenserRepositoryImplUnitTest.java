package unit.com.rviewer.skeletons.infraestructure.persistence.repository;

import com.rviewer.skeletons.domain.model.Dispenser;
import com.rviewer.skeletons.infrastructure.mapper.DispenserMapper;
import com.rviewer.skeletons.infrastructure.persistence.dao.DispenserDao;
import com.rviewer.skeletons.infrastructure.persistence.repository.DispenserRepositoryImpl;
import com.rviewer.skeletons.infrastructure.persistence.repository.JpaDispenserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class DispenserRepositoryImplUnitTest {

    @InjectMocks
    private DispenserRepositoryImpl dispenserRepository;

    @Mock
    private JpaDispenserRepository jpaDispenserRepository;

    @Mock
    private DispenserMapper dispenserMapper;

    @Test
    void findByIdUnitTest() {
        Mockito.when(jpaDispenserRepository.findById(Mockito.any())).thenReturn(Optional.of(new DispenserDao()));

        dispenserRepository.findById("ID");

        Mockito.verify(dispenserMapper).map(Mockito.any(DispenserDao.class));
    }

    @Test
    void saveUnitTest() {
        Mockito.when(jpaDispenserRepository.save(Mockito.any())).thenReturn(new DispenserDao());

        dispenserRepository.save(new Dispenser());

        Mockito.verify(dispenserMapper).map(Mockito.any(Dispenser.class));
        Mockito.verify(jpaDispenserRepository).save(Mockito.any());
        Mockito.verify(dispenserMapper).map(Mockito.any(DispenserDao.class));
    }
}
