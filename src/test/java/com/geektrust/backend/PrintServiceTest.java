package com.geektrust.backend;

import com.geektrust.backend.services.CheckInService;
import com.geektrust.backend.services.PrintService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class PrintServiceTest {
    private PrintService printService;

    @Mock
    private CheckInService checkInServiceMock;

    @BeforeEach
    void setUp() {
        checkInServiceMock = mock(CheckInService.class);
        printService = new PrintService();
        printService.checkInService = checkInServiceMock;
    }

    @Test
    void testGetPrintSummary() {
        // Mocking behavior of the CheckInService
        when(checkInServiceMock.getCentralCollectionMap()).thenReturn(200);
        when(checkInServiceMock.getCentralDiscountMap()).thenReturn(100);
        when(checkInServiceMock.getCentralPassengerCount()).thenReturn("ADULT 2\nKID 1\n");

        when(checkInServiceMock.getAirportCollectionMap()).thenReturn(150);
        when(checkInServiceMock.getAirportDiscountMap()).thenReturn(75);
        when(checkInServiceMock.getAirportPassengerCount()).thenReturn("SENIOR_CITIZEN 1\nADULT 3\n");

        // Act
        String printSummary = printService.getPrintSummary();

        // Assert
        String expectedSummary = "TOTAL_COLLECTION CENTRAL 200 100\n" +
                "PASSENGER_TYPE_SUMMARY\nADULT 2\nKID 1\n" +
                "TOTAL_COLLECTION AIRPORT 150 75\n" +
                "PASSENGER_TYPE_SUMMARY\nSENIOR_CITIZEN 1\nADULT 3\n";

        assertEquals(expectedSummary, printSummary);
    }
}

