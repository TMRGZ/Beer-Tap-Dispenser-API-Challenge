package unit.com.rviewer.skeletons.infraestructure.persistence.dao;

import com.rviewer.skeletons.infrastructure.persistence.dao.DispenserDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Collections;

class DispenserDaoUnitTest {

    @Test
    void creationTest() {
        DispenserDao dispenserDao = new DispenserDao();
        dispenserDao.setId("ID");
        dispenserDao.setFlowVolume(1.0);
        dispenserDao.setDispenserHistory(Collections.emptyList());

        Assertions.assertNotNull(dispenserDao);
        Assertions.assertNotNull(dispenserDao.getId());
        Assertions.assertNotNull(dispenserDao.getFlowVolume());
        Assertions.assertNotNull(dispenserDao.getDispenserHistory());
    }
}
