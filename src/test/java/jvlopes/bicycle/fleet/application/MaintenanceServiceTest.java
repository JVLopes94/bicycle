package jvlopes.bicycle.fleet.application;

import jvlopes.bicycle.factory.BicycleTestFactory;
import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.domain.entity.BicycleID;
import jvlopes.bicycle.fleet.domain.exception.BicycleNotFoundException;
import jvlopes.bicycle.fleet.domain.exception.InvalidBicycleIdException;
import jvlopes.bicycle.fleet.domain.vo.BicycleStatus;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MaintenanceServiceTest {

    @Mock
    private BicycleService bicycleService;

    @InjectMocks
    private MaintenanceService maintenanceService;

    @Nested
    class PutBicycleUnderMaintenance {

        @Captor
        ArgumentCaptor<String> bicycleIDCaptor;

        @Captor
        ArgumentCaptor<Bicycle> bicycleCaptor;

        @Test
        void shouldThrowInvalidBicycleIdException() {
            doThrow(InvalidBicycleIdException.class).when(bicycleService).getByID(any());
            assertThrows(InvalidBicycleIdException.class, () -> maintenanceService.putBicycleUnderMaintenance(""));
            assertThrows(InvalidBicycleIdException.class, () -> maintenanceService.putBicycleUnderMaintenance(null));
        }

        @Test
        void shouldThrowBicycleNotFoundException() {
            doThrow(BicycleNotFoundException.class).when(bicycleService).getByID(any());
            assertThrows(BicycleNotFoundException.class, () -> maintenanceService.putBicycleUnderMaintenance("1234"));
        }

        @Test
        void shouldPassCorrectParametersToBicycleService() {
            String expectedBicycleID = "abc-123";
            Bicycle expectedBicycle = BicycleTestFactory.createBicycleWithID(new BicycleID(expectedBicycleID));
            doReturn(expectedBicycle).when(bicycleService).getByID(any());

            Bicycle returnedBicycle = maintenanceService.putBicycleUnderMaintenance("abc-123");

            verify(bicycleService).getByID(bicycleIDCaptor.capture());
            String captured = bicycleIDCaptor.getValue();
            assertEquals(expectedBicycleID, captured);
            assertEquals(expectedBicycle, returnedBicycle);
            assertEquals(BicycleStatus.UNDER_MAINTENANCE, returnedBicycle.getStatus());

            verify(bicycleService).save(bicycleCaptor.capture());
            Bicycle capturedBicycle = bicycleCaptor.getValue();
            assertEquals(expectedBicycle, capturedBicycle);

            verifyNoMoreInteractions(bicycleService);
        }

    }

    @Nested
    class PutBicycleAvailable {

        @Captor
        ArgumentCaptor<String> bicycleIDCaptor;

        @Captor
        ArgumentCaptor<Bicycle> bicycleCaptor;

        @Test
        void shouldThrowInvalidBicycleIdException() {
            doThrow(InvalidBicycleIdException.class).when(bicycleService).getByID(any());
            assertThrows(InvalidBicycleIdException.class, () -> maintenanceService.putBicycleAvailable(""));
            assertThrows(InvalidBicycleIdException.class, () -> maintenanceService.putBicycleAvailable(null));
        }

        @Test
        void shouldThrowBicycleNotFoundException() {
            doThrow(BicycleNotFoundException.class).when(bicycleService).getByID(any());
            assertThrows(BicycleNotFoundException.class, () -> maintenanceService.putBicycleAvailable("1234"));
        }

        @Test
        void shouldPassCorrectParametersToBicycleService() {
            String expectedBicycleID = "abc-123";
            Bicycle expectedBicycle = BicycleTestFactory.createBicycleUnderMaintenanceWithID(new BicycleID(expectedBicycleID));
            doReturn(expectedBicycle).when(bicycleService).getByID(any());

            Bicycle returnedBicycle = maintenanceService.putBicycleAvailable("abc-123");

            verify(bicycleService).getByID(bicycleIDCaptor.capture());
            String captured = bicycleIDCaptor.getValue();
            assertEquals(expectedBicycleID, captured);
            assertEquals(expectedBicycle, returnedBicycle);
            assertEquals(BicycleStatus.AVAILABLE, returnedBicycle.getStatus());

            verify(bicycleService).save(bicycleCaptor.capture());
            Bicycle capturedBicycle = bicycleCaptor.getValue();
            assertEquals(expectedBicycle, capturedBicycle);

            verifyNoMoreInteractions(bicycleService);
        }

    }

}