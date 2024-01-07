package com.geektrust.backend;

import com.geektrust.backend.services.BalanceService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class BalanceServiceTest {
    private BalanceService balanceService = new BalanceService();

    @Test
    void testSetAndGetMap() {
        // Arrange
        String cardNumber = "1234";
        int balance = 50;

        // Act
        balanceService.setMap(cardNumber, balance);
        int retrievedBalance = balanceService.getMap(cardNumber);

        // Assert
        assertEquals(balance, retrievedBalance);
    }

    @Test
    void testGetMapForNonexistentCard() {
        // Arrange
        String nonExistentCardNumber = "5678";

        // Act and Assert
        assertThrows(NullPointerException.class, () -> {
            balanceService.getMap(nonExistentCardNumber);
        });

    }
}

