package integration.com.rviewer.skeletons.infrastructure.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rviewer.skeletons.RviewerSkeletonApplication;
import com.rviewer.skeletons.application.request.PostCreateDispenserRequest;
import com.rviewer.skeletons.application.request.PutSetDispenserStatusRequest;
import com.rviewer.skeletons.application.response.GetDispenserSpendingResponse;
import com.rviewer.skeletons.application.response.PostCreateDispenserResponse;
import com.rviewer.skeletons.infrastructure.persistence.dao.DispenserDao;
import com.rviewer.skeletons.infrastructure.persistence.dao.DispenserHistoryDao;
import com.rviewer.skeletons.infrastructure.persistence.repository.JpaDispenserRepository;
import com.rviewer.skeletons.utils.Constants;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.Date;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = RviewerSkeletonApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class BeerTapControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JpaDispenserRepository jpaDispenserRepository;

    @Autowired
    private ObjectMapper objectMapper;

    private static final String GET_SPENDING_URL = "/dispenser/{dispenserId}/spending";
    private static final String SET_DISPENSER_STATUS_URL = "/dispenser/{id}/status";
    private static final String CREATE_DISPENSER_URL = "/dispenser";

    @Test
    void getSpendingIntegrationTest() throws Exception {
        String dispenserId = "DISPENSER_1";

        URI uri = UriComponentsBuilder.fromUriString(GET_SPENDING_URL).build(dispenserId);

        MvcResult result = mockMvc.perform(get(uri))
                .andExpect(status().isOk())
                .andReturn();

        GetDispenserSpendingResponse response =
                objectMapper.readValue(result.getResponse().getContentAsString(), GetDispenserSpendingResponse.class);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getAmount());
        Assertions.assertEquals(0.0, response.getAmount());
        Assertions.assertNotNull(response.getUsages());
        Assertions.assertTrue(response.getUsages().isEmpty());
    }

    @Test
    void getSpendingButDispenserDoesNotExistExceptionIntegrationTest() throws Exception {
        String dispenserId = "NOT_EXISTS";

        URI uri = UriComponentsBuilder.fromUriString(GET_SPENDING_URL).build(dispenserId);

        mockMvc.perform(get(uri)).andExpect(status().isNotFound());
    }

    @Test
    void setDispenserStatusToOpenIntegrationTest() throws Exception {
        String dispenserId = "DISPENSER_2";
        URI uri = UriComponentsBuilder.fromUriString(SET_DISPENSER_STATUS_URL).build(dispenserId);

        PutSetDispenserStatusRequest request = new PutSetDispenserStatusRequest();
        request.setStatus(Constants.DISPENSER_OPENED_STATUS);
        request.setUpdatedAt(new Date());

        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isAccepted());

        Optional<DispenserDao> dispenserDaoOptional = jpaDispenserRepository.findById(dispenserId);
        Assertions.assertTrue(dispenserDaoOptional.isPresent());

        DispenserDao dispenserDao = dispenserDaoOptional.get();

        Assertions.assertNotNull(dispenserDao.getDispenserHistory());
        Assertions.assertFalse(dispenserDao.getDispenserHistory().isEmpty());
        Assertions.assertEquals(1, dispenserDao.getDispenserHistory().size());

        DispenserHistoryDao dispenserHistoryDao = dispenserDao.getDispenserHistory().get(0);
        Assertions.assertNotNull(dispenserHistoryDao.getId());
        Assertions.assertNotNull(dispenserHistoryDao.getOpenedAt());
        Assertions.assertNull(dispenserHistoryDao.getClosedAt());
        Assertions.assertNull(dispenserHistoryDao.getTotalSpent());
    }

    @Test
    void setDispenserStatusToOpenButIsAlreadyOpenedIntegrationTest() throws Exception {
        String dispenserId = "DISPENSER_3";
        URI uri = UriComponentsBuilder.fromUriString(SET_DISPENSER_STATUS_URL).build(dispenserId);

        PutSetDispenserStatusRequest request = new PutSetDispenserStatusRequest();
        request.setStatus(Constants.DISPENSER_OPENED_STATUS);
        request.setUpdatedAt(new Date());

        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isConflict());
    }

    @Test
    void setDispenserStatusToCloseIntegrationTest() throws Exception {
        String dispenserId = "DISPENSER_4";
        URI uri = UriComponentsBuilder.fromUriString(SET_DISPENSER_STATUS_URL).build(dispenserId);

        PutSetDispenserStatusRequest request = new PutSetDispenserStatusRequest();
        request.setStatus(Constants.DISPENSER_CLOSED_STATUS);
        request.setUpdatedAt(new Date());

        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isAccepted());

        Optional<DispenserDao> dispenserDaoOptional = jpaDispenserRepository.findById(dispenserId);
        Assertions.assertTrue(dispenserDaoOptional.isPresent());

        DispenserDao dispenserDao = dispenserDaoOptional.get();

        Assertions.assertNotNull(dispenserDao.getDispenserHistory());
        Assertions.assertFalse(dispenserDao.getDispenserHistory().isEmpty());
        Assertions.assertEquals(1, dispenserDao.getDispenserHistory().size());

        DispenserHistoryDao dispenserHistoryDao = dispenserDao.getDispenserHistory().get(0);
        Assertions.assertNotNull(dispenserHistoryDao.getId());
        Assertions.assertNotNull(dispenserHistoryDao.getOpenedAt());
        Assertions.assertNotNull(dispenserHistoryDao.getClosedAt());
        Assertions.assertNotNull(dispenserHistoryDao.getTotalSpent());
    }

    @Test
    void setDispenserStatusToCloseButIsUnexpectedErrorIntegrationTest() throws Exception {
        String dispenserId = "DISPENSER_2";
        URI uri = UriComponentsBuilder.fromUriString(SET_DISPENSER_STATUS_URL).build(dispenserId);

        PutSetDispenserStatusRequest request = new PutSetDispenserStatusRequest();
        request.setStatus(Constants.DISPENSER_CLOSED_STATUS);
        request.setUpdatedAt(new Date());

        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isInternalServerError());
    }

    @Test
    void setDispenserStatusToCloseButIsAlreadyClosedIntegrationTest() throws Exception {
        String dispenserId = "DISPENSER_5";
        URI uri = UriComponentsBuilder.fromUriString(SET_DISPENSER_STATUS_URL).build(dispenserId);

        PutSetDispenserStatusRequest request = new PutSetDispenserStatusRequest();
        request.setStatus(Constants.DISPENSER_CLOSED_STATUS);
        request.setUpdatedAt(new Date());

        mockMvc.perform(put(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isConflict());
    }

    @Test
    void createDispenserIntegrationTest() throws Exception {
        PostCreateDispenserRequest request = new PostCreateDispenserRequest();
        request.setFlowVolume(0.0);

        URI uri = URI.create(CREATE_DISPENSER_URL);

        MvcResult result = mockMvc.perform(post(uri)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request))
        ).andExpect(status().isOk()).andReturn();

        PostCreateDispenserResponse response =
                objectMapper.readValue(result.getResponse().getContentAsString(), PostCreateDispenserResponse.class);

        Assertions.assertNotNull(response);
        Assertions.assertNotNull(response.getId());
        Assertions.assertNotNull(response.getFlowVolume());

        Optional<DispenserDao> dispenserDaoOptional = jpaDispenserRepository.findById(response.getId());
        Assertions.assertTrue(dispenserDaoOptional.isPresent());

        DispenserDao dispenserDao = dispenserDaoOptional.get();
        Assertions.assertNotNull(dispenserDao.getId());
        Assertions.assertEquals(response.getId(), dispenserDao.getId());
        Assertions.assertNotNull(dispenserDao.getFlowVolume());
        Assertions.assertEquals(response.getFlowVolume(), dispenserDao.getFlowVolume());
        Assertions.assertNotNull(dispenserDao.getDispenserHistory());
        Assertions.assertTrue(dispenserDao.getDispenserHistory().isEmpty());
    }
}
