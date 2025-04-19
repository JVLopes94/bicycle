package jvlopes.bicycle.fleet.infrastructure.controller;

import jvlopes.bicycle.factory.BicycleTestFactory;
import jvlopes.bicycle.fleet.application.BicycleService;
import jvlopes.bicycle.fleet.application.dto.PageResponse;
import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.domain.entity.BicycleID;
import jvlopes.bicycle.fleet.domain.vo.BicycleStatus;
import jvlopes.bicycle.fleet.infrastructure.controller.dto.BicycleDetailsDTO;
import jvlopes.bicycle.fleet.infrastructure.controller.dto.CreateBicycleDTO;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BicycleControllerTest {

    @Mock
    private BicycleService bicycleService;

    @Captor
    ArgumentCaptor<Integer> pageCaptor;

    @Captor
    ArgumentCaptor<Integer> sizeCaptor;

    @Captor
    ArgumentCaptor<BicycleStatus> statusCaptor;

    @Captor
    ArgumentCaptor<Bicycle> bicycleCaptor;

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
            ResponseEntity<BicycleDetailsDTO> response = bicycleController.create(new CreateBicycleDTO(
                    bicycleId,
                    "model",
                    "AVAILABLE",
                    "SP",
                    "2025-04-10T10:15:30"
            ));
            BicycleDetailsDTO responseBody = response.getBody();
            assertNotNull(responseBody);
            assertEquals(responseBody.id(), createdBicycle.getId().toString());
        }

        @Test
        void shouldReturnLocationHeader() {
            String bicycleId = "113e-c296-9048";
            Bicycle createdBicycle = BicycleTestFactory.createBicycleWithID(new BicycleID(bicycleId));
            doReturn(createdBicycle).when(bicycleService).save(any());
            ResponseEntity<BicycleDetailsDTO> response = bicycleController.create(new CreateBicycleDTO(
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

        @Test
        void shouldInvokeServiceCreate() {
            String id = "113ec296-9048-4f87-9aab-d075fae6e767";
            doReturn(BicycleTestFactory.createBicycleWithID(new BicycleID(id)))
                    .when(bicycleService).save(any());
            bicycleController.create(new CreateBicycleDTO(
                    id,
                    "model",
                    "AVAILABLE",
                    "SP",
                    "2025-04-10T10:15:30"
            ));
            verify(bicycleService, new Times(1)).save(bicycleCaptor.capture());
            Bicycle capturedBicycle = bicycleCaptor.getValue();
            assertEquals(id, capturedBicycle.getId().toString());
        }
    }

    @Nested
    class ListBicycles {

        @Test
        void shouldReturnHttpOk() {
            doReturn(new PageResponse<Bicycle>(new ArrayList<>(), 0))
                    .when(bicycleService).list(anyInt(), anyInt());
            var response = bicycleController.list(null, 0, 10);
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

        @ParameterizedTest
        @MethodSource("jvlopes.bicycle.factory.BicycleTestFactory#bicycleListProvider")
        void responseShouldContainAllBicyclesReturnedByService(PageResponse<Bicycle> serviceReturn) {
            doReturn(serviceReturn).when(bicycleService).list(anyInt(), anyInt());

            ResponseEntity<Page<BicycleDetailsDTO>> response = bicycleController.list(null, 0, 10);
            Page<BicycleDetailsDTO> responseBody = response.getBody();
            assertNotNull(responseBody);

            assertEquals(serviceReturn.getContent().size(), responseBody.getContent().size());

            Set<String> bicycleIDs = serviceReturn.getContent().stream().map(b -> b.getId().toString()).collect(Collectors.toSet());
            var responseIDs = responseBody.getContent().stream().map(BicycleDetailsDTO::id).collect(Collectors.toSet());
            assertTrue(responseIDs.containsAll(bicycleIDs));
        }

        @ParameterizedTest
        @MethodSource("jvlopes.bicycle.factory.BicycleTestFactory#bicycleListProvider")
        void responseShouldContainCorrectPaginationData(PageResponse<Bicycle> serviceReturn) {
            doReturn(serviceReturn).when(bicycleService).list(anyInt(), anyInt());

            ResponseEntity<Page<BicycleDetailsDTO>> response = bicycleController.list(null, 0, 10);
            Page<BicycleDetailsDTO> responseBody = response.getBody();
            assertNotNull(responseBody);

            assertEquals(serviceReturn.getTotalElements(), responseBody.getTotalElements());
            assertEquals(serviceReturn.getContent().size(), responseBody.getNumberOfElements());
        }

        @ParameterizedTest
        @MethodSource("jvlopes.bicycle.factory.BicycleTestFactory#bicycleListProvider")
        void controllerShouldInvokeServiceWithCorrectPaginationParameters(PageResponse<Bicycle> serviceReturn) {
            doReturn(serviceReturn).when(bicycleService).list(anyInt(), anyInt());
            bicycleController.list(null, 0, 10);
            verify(bicycleService).list(pageCaptor.capture(), sizeCaptor.capture());
            int capturedPage = pageCaptor.getValue();
            int capturedSize = sizeCaptor.getValue();
            assertEquals(0, capturedPage);
            assertEquals(10, capturedSize);
        }

        @ParameterizedTest
        @MethodSource("jvlopes.bicycle.factory.BicycleTestFactory#bicycleListProvider")
        void controllerShouldInvokeServiceWithCorrectFilterParameter(PageResponse<Bicycle> serviceReturn) {
            doReturn(serviceReturn).when(bicycleService).listByStatus(any(), anyInt(), anyInt());
            bicycleController.list(BicycleStatus.AVAILABLE, 0, 10);
            verify(bicycleService).listByStatus(statusCaptor.capture(), pageCaptor.capture(), sizeCaptor.capture());
            int capturedPage = pageCaptor.getValue();
            int capturedSize = sizeCaptor.getValue();
            BicycleStatus capturedStatus = statusCaptor.getValue();
            assertEquals(0, capturedPage);
            assertEquals(10, capturedSize);
            assertEquals(BicycleStatus.AVAILABLE, capturedStatus);
        }

    }

    @Nested
    class GetBicycleByID {

        @Test
        void shouldReturnHttpOk() {
            var response = bicycleController.getByID("");
            assertEquals(HttpStatus.OK, response.getStatusCode());
        }

    }
}