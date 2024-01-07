package com.geektrust.backend.services;

import java.util.HashMap;

public class BalanceService {
    private static HashMap<String, Integer> cardBalanceMap = new HashMap<>();

    public void setMap(String cardNumber, int balance){
        cardBalanceMap.put(cardNumber, balance);
    }

    public int getMap(String cardNumber){
        return cardBalanceMap.get(cardNumber);
    }
}
