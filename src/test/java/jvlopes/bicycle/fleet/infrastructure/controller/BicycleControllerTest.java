package jvlopes.bicycle.fleet.infrastructure.controller;

import jvlopes.bicycle.factory.BicycleTestFactory;
import jvlopes.bicycle.fleet.application.BicycleService;
import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.domain.entity.BicycleID;
import jvlopes.bicycle.fleet.infrastructure.controller.dto.BicycleCreatedDTO;
import jvlopes.bicycle.fleet.infrastructure.controller.dto.CreateBicycleDTO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class BicycleControllerTest {

    @Mock
    private BicycleService bicycleService;

    @InjectMocks
    private BicycleController bicycleController;

    @Nested
    class Create {

        @Test
        void shouldReturnHttpOk() {
            doReturn(BicycleTestFactory.createBicycle()).when(bicycleService).save(any());
            var response = bicycleController.create(new CreateBicycleDTO(
                    "113ec296-9048-4f87-9aab-d075fae6e767",
                    "model",
                    "AVAILABLE",
                    "SP",
                    "2025-04-10T10:15:30"
            ));
            assertEquals(HttpStatus.CREATED, response.getStatusCode());
        }

        @Test
        void shouldReturnCorrectResponse() {
            String bicycleId = "113ec296-9048-4f87-9aab-d075fae6e767";
            Bicycle createdBicycle = BicycleTestFactory.createBicycleWithID(new BicycleID(bicycleId));
            doReturn(createdBicycle).when(bicycleService).save(any());
            ResponseEntity<BicycleCreatedDTO> response = bicycleController.create(new CreateBicycleDTO(
                    bicycleId,
                    "model",
                    "AVAILABLE",
                    "SP",
                    "2025-04-10T10:15:30"
            ));
            BicycleCreatedDTO responseBody = response.getBody();
            assertNotNull(responseBody);
            assertEquals(responseBody.id(), createdBicycle.getId().toString());
        }

        @Test
        void shouldReturnLocationHeader() {
            String bicycleId = "113e-c296-9048";
            Bicycle createdBicycle = BicycleTestFactory.createBicycleWithID(new BicycleID(bicycleId));
            doReturn(createdBicycle).when(bicycleService).save(any());
            ResponseEntity<BicycleCreatedDTO> response = bicycleController.create(new CreateBicycleDTO(
                    bicycleId,
                    "model",
                    "AVAILABLE",
                    "SP",
                    "2025-04-10T10:15:30"
            ));
            HttpHeaders responseHeaders = response.getHeaders();
            assertNotNull(responseHeaders);
            assertTrue(responseHeaders.containsKey("Location"));
            assertNotNull(responseHeaders.getLocation());
            assertTrue(responseHeaders.getLocation().toString().contains(bicycleId));
        }
    }
}