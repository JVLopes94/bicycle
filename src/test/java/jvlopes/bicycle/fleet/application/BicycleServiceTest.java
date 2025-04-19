package jvlopes.bicycle.fleet.application;

import jvlopes.bicycle.factory.BicycleTestFactory;
import jvlopes.bicycle.fleet.domain.entity.Bicycle;
import jvlopes.bicycle.fleet.domain.entity.BicycleID;
import jvlopes.bicycle.fleet.domain.exception.BicycleNotFoundException;
import jvlopes.bicycle.fleet.domain.exception.InvalidBicycleIdException;
import jvlopes.bicycle.fleet.domain.repository.BicycleRepository;
import jvlopes.bicycle.fleet.domain.vo.BicycleStatus;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.internal.verification.Times;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class BicycleServiceTest {

    @Mock
    private BicycleRepository bicycleRepository;

    @InjectMocks
    private BicycleService bicycleService;

    @Captor
    ArgumentCaptor<Bicycle> bicycleCaptor;

    @Captor
    ArgumentCaptor<Integer> pageCaptor;

    @Captor
    ArgumentCaptor<Integer> sizeCaptor;

    @Captor
    ArgumentCaptor<BicycleStatus> statusCaptor;

    @Nested
    class Save {

        @Test
        void shouldInvokeRepositoryWithCorrectParameters() {
            String id = "1234";
            doReturn(BicycleTestFactory.createBicycleWithID(new BicycleID(id)))
                    .when(bicycleRepository).save(any());
            bicycleService.save(new Bicycle(
                    new BicycleID(id),
                    "model",
                    BicycleStatus.AVAILABLE,
                    "Brasil",
                    LocalDateTime.now()
            ));
            verify(bicycleRepository, new Times(1)).save(bicycleCaptor.capture());
            Bicycle capturedBicycle = bicycleCaptor.getValue();
            assertEquals(id, capturedBicycle.getId().toString());
        }

    }

    @Nested
    class ListBicycles {

        @Test
        void shouldInvokeRepositoryWithCorrectParameters() {
            bicycleService.list(0, 10);
            verify(bicycleRepository, new Times(1)).findAll(pageCaptor.capture(), sizeCaptor.capture());
            int capturedPage = pageCaptor.getValue();
            int capturedSize = sizeCaptor.getValue();
            assertEquals(0, capturedPage);
            assertEquals(10, capturedSize);
        }

    }

    @Nested
    class ListByStatus {

        @Test
        void shouldInvokeRepositoryWithCorrectParameters() {
            bicycleService.listByStatus(BicycleStatus.UNDER_MAINTENANCE, 0, 10);
            verify(bicycleRepository, new Times(1))
                    .findAllByStatus(statusCaptor.capture(), pageCaptor.capture(), sizeCaptor.capture());
            int capturedPage = pageCaptor.getValue();
            int capturedSize = sizeCaptor.getValue();
            BicycleStatus capturedStatus = statusCaptor.getValue();
            assertEquals(0, capturedPage);
            assertEquals(10, capturedSize);
            assertEquals(BicycleStatus.UNDER_MAINTENANCE, capturedStatus);
        }

    }

    @Nested
    class GetByID {

        @Test
        void shouldThrowInvalidBicycleIdException() {
            assertThrows(InvalidBicycleIdException.class, () -> bicycleService.getByID(""));
            assertThrows(InvalidBicycleIdException.class, () -> bicycleService.getByID(null));
        }

        @Test
        void shouldThrowBicycleNotFoundException() {
            doReturn(null).when(bicycleRepository).findByID(any());
            assertThrows(BicycleNotFoundException.class, () -> bicycleService.getByID("1234"));
        }

    }

}