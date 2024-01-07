package com.geektrust.backend.services;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class CheckInService {
    private static HashMap<String, Integer> CentralCollectionMap = new HashMap<>();
    private static HashMap<String, Integer> AirportCollectionMap = new HashMap<>();
    private static HashMap<String, Integer> CentralDiscountMap = new HashMap<>();
    private static HashMap<String, Integer> AirportDiscountMap = new HashMap<>();
    private static TreeMap<String, Integer> CentralPassengerCountMap = new TreeMap<>();
    private static TreeMap<String, Integer> AirportPassengerCountMap = new TreeMap<>();

    public BalanceService balanceService = new BalanceService();

    private static int getTravelCharge(String passengerType){
        switch(passengerType){
            case "ADULT":
              return 200;
            case "SENIOR_CITIZEN":
              return 100;
            case "KID":
              return 50;
            default:
              return 0;
        }
    }

    public void setCentralCollectionMap(String cardNumber, String passengerType, String Station){
        int travelCharge = getTravelCharge(passengerType);
        CentralPassengerCount(passengerType);
        
        if(AirportCollectionMap.containsKey(cardNumber) && !AirportDiscountMap.containsKey(cardNumber)){
            travelCharge /= 2;
            if(CentralDiscountMap.containsKey(cardNumber)){
                CentralDiscountMap.put(cardNumber, CentralDiscountMap.get(cardNumber)+travelCharge);
            }else{
                CentralDiscountMap.put(cardNumber, travelCharge);
            }
        }

        if(balanceService.getMap(cardNumber) < travelCharge){
            int requiredBalance = travelCharge - balanceService.getMap(cardNumber);
            rechargeCard(cardNumber, Station, requiredBalance);
        }

        balanceService.setMap(cardNumber, balanceService.getMap(cardNumber) - travelCharge);

        if(CentralCollectionMap.containsKey(cardNumber)){
            CentralCollectionMap.put(cardNumber, CentralCollectionMap.get(cardNumber)+travelCharge);
        }else{
            CentralCollectionMap.put(cardNumber, travelCharge);
        }

    }

    public int getCentralCollectionMap(){
        int sum = 0;
        for(int val : CentralCollectionMap.values()){
            sum += val;
        }
        return sum;
    }

    public int getCentralDiscountMap(){
        int sum = 0;
        for(int val : CentralDiscountMap.values()){
            sum += val;
        }
        return sum;
    }

    

    private void rechargeCard(String cardNumber, String station, int requiredBalance) {
        int serviceCharge = (int) (requiredBalance * 0.02);
        if(station.equals("CENTRAL")){
            if(CentralCollectionMap.containsKey(cardNumber)){
                CentralCollectionMap.put(cardNumber, CentralCollectionMap.get(cardNumber)+serviceCharge);
            }else{
                CentralCollectionMap.put(cardNumber, serviceCharge);
            }
        }else{
            if(AirportCollectionMap.containsKey(cardNumber)){
                AirportCollectionMap.put(cardNumber, AirportCollectionMap.get(cardNumber)+serviceCharge);
            }else{
                AirportCollectionMap.put(cardNumber, serviceCharge);
            }
        }
        balanceService.setMap(cardNumber, balanceService.getMap(cardNumber) + requiredBalance - serviceCharge);
    }

    public void setAirportCollectionMap(String cardNumber, String passengerType, String Station){
        int travelCharge = getTravelCharge(passengerType);
        AirportPassengerCount(passengerType);
        
        if(CentralCollectionMap.containsKey(cardNumber) && !CentralDiscountMap.containsKey(cardNumber)){
            travelCharge /= 2;
            if(AirportDiscountMap.containsKey(cardNumber)){
                AirportDiscountMap.put(cardNumber, AirportDiscountMap.get(cardNumber)+travelCharge);
            }else{
                AirportDiscountMap.put(cardNumber, travelCharge);
            }
        }

        if(balanceService.getMap(cardNumber) < travelCharge){
            int requiredBalance = travelCharge - balanceService.getMap(cardNumber);
            rechargeCard(cardNumber, Station, requiredBalance);
        }

        balanceService.setMap(cardNumber, balanceService.getMap(cardNumber) - travelCharge);

        if(AirportCollectionMap.containsKey(cardNumber)){
            AirportCollectionMap.put(cardNumber, AirportCollectionMap.get(cardNumber)+travelCharge);
        }else{
            AirportCollectionMap.put(cardNumber, travelCharge);
        }
    }

    public int getAirportCollectionMap(){
        int sum = 0;
        for(int val : AirportCollectionMap.values()){
            sum += val;
        }
        return sum;
    }

    public int getAirportDiscountMap(){
        int sum = 0;
        for(int val : AirportDiscountMap.values()){
            sum += val;
        }
        return sum;
    }

    public void CentralPassengerCount(String PassengerType){
        if(CentralPassengerCountMap.containsKey(PassengerType)){
            CentralPassengerCountMap.put(PassengerType, CentralPassengerCountMap.get(PassengerType)+1);
        }else{
            CentralPassengerCountMap.put(PassengerType, 1);
        }
    }

    public String getCentralPassengerCount(){
        StringBuilder result = new StringBuilder();
        for(Map.Entry<String,Integer> entry : CentralPassengerCountMap.entrySet()){
            result.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }
        return result.toString();
    }

    public void AirportPassengerCount(String PassengerType){
        if(AirportPassengerCountMap.containsKey(PassengerType)){
            AirportPassengerCountMap.put(PassengerType, AirportPassengerCountMap.get(PassengerType)+1);
        }else{
            AirportPassengerCountMap.put(PassengerType, 1);
        }
    }

    public String getAirportPassengerCount(){
        StringBuilder result = new StringBuilder();
        for(Map.Entry<String,Integer> entry : AirportPassengerCountMap.entrySet()){
            result.append(entry.getKey()).append(" ").append(entry.getValue()).append("\n");
        }
        return result.toString();
    }
}
