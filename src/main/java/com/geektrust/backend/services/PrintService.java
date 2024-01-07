package com.geektrust.backend.services;

public class PrintService {
    CheckInService checkInService = new CheckInService();

    public String getPrintSummary(){
        return "TOTAL_COLLECTION CENTRAL " + checkInService.getCentralCollectionMap() + " " + checkInService.getCentralDiscountMap() +
        "\nPASSENGER_TYPE_SUMMARY\n" + checkInService.getCentralPassengerCount() +
        "TOTAL_COLLECTION AIRPORT " + checkInService.getAirportCollectionMap() + " " +checkInService.getAirportDiscountMap() +
        "\nPASSENGER_TYPE_SUMMARY\n" + checkInService.getAirportPassengerCount();
    }
}
