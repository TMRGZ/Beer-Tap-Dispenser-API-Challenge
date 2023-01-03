package unit.com.rviewer.skeletons.infraestructure.persistence.dao;

import com.rviewer.skeletons.infrastructure.persistence.dao.DispenserHistoryDao;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

class DispenserHistoryDaoUnitTest {

    @Test
    void creationTest() {
        DispenserHistoryDao dispenserHistoryDao = new DispenserHistoryDao();
        dispenserHistoryDao.setId("ID");
        dispenserHistoryDao.setTotalSpent(1.0);
        dispenserHistoryDao.setOpenedAt(new Date());
        dispenserHistoryDao.setClosedAt(new Date());

        Assertions.assertNotNull(dispenserHistoryDao);
        Assertions.assertNotNull(dispenserHistoryDao.getId());
        Assertions.assertNotNull(dispenserHistoryDao.getTotalSpent());
        Assertions.assertNotNull(dispenserHistoryDao.getOpenedAt());
        Assertions.assertNotNull(dispenserHistoryDao.getClosedAt());
    }
}
