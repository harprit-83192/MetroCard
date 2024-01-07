package com.geektrust.backend;

import com.geektrust.backend.services.BalanceService;
import com.geektrust.backend.services.CheckInService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CheckInServiceTest {
    private CheckInService checkInService;

    @Mock
    private BalanceService balanceServiceMock;

    @BeforeEach
    void setUp() {
        balanceServiceMock = mock(BalanceService.class);
        checkInService = new CheckInService();
        checkInService.balanceService = balanceServiceMock;
    }

    @Test
    void testSetAndGetCentralCollectionMap() {
        // Arrange
        String cardNumber = "1234";
        String passengerType = "ADULT";
        String station = "CENTRAL";

        // Mocking behavior of the BalanceService
        when(balanceServiceMock.getMap(cardNumber)).thenReturn(200); // Assuming enough balance for the travel charge

        // Act
        checkInService.setCentralCollectionMap(cardNumber, passengerType, station);
        int centralCollection = checkInService.getCentralCollectionMap();

        // Assert
        assertEquals(200, centralCollection); // Assuming ADULT travel charge is 200
    }

    @Test
    void testSetAndGetAirportCollectionMap() {
        // Arrange
        String cardNumber = "5678";
        String passengerType = "SENIOR_CITIZEN";
        String station = "AIRPORT";

        // Mocking behavior of the BalanceService
        when(balanceServiceMock.getMap(cardNumber)).thenReturn(100); // Assuming enough balance for the travel charge

        // Act
        checkInService.setAirportCollectionMap(cardNumber, passengerType, station);
        int airportCollection = checkInService.getAirportCollectionMap();

        // Assert
        assertEquals(100, airportCollection); // Assuming SENIOR_CITIZEN travel charge is 100
    }

    @Test
    void testCentralPassengerCount() {
        // Arrange
        String passengerType = "ADULT";

        // Act
        checkInService.CentralPassengerCount(passengerType);
        String centralPassengerCount = checkInService.getCentralPassengerCount();

        // Assert
        assertEquals("ADULT 1\n", centralPassengerCount);
    }

    @Test
    void testAirportPassengerCount() {
        // Arrange
        String passengerType = "KID";

        // Act
        checkInService.AirportPassengerCount(passengerType);
        String airportPassengerCount = checkInService.getAirportPassengerCount();

        // Assert
        assertEquals("KID 1\n" +
                "SENIOR_CITIZEN 1\n", airportPassengerCount);
    }
}

