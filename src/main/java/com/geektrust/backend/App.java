package com.geektrust.backend;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import com.geektrust.backend.services.BalanceService;
import com.geektrust.backend.services.CheckInService;
import com.geektrust.backend.services.PrintService;

public class App {

	public static void main(String[] args) {
		String file = args[0];
		try (BufferedReader reader = new BufferedReader(new FileReader(file))){
			String line;
			while((line = reader.readLine()) != null){
				String[] tokens = line.split("\\s+");
				switch(tokens[0]){
					case "BALANCE":
						String cardNumber = tokens[1];
						int balance = Integer.parseInt(tokens[2]);
						BalanceService balanceService = new BalanceService();
						balanceService.setMap(cardNumber, balance);
						break;
					case "CHECK_IN":
						cardNumber = tokens[1];
						String passengerType = tokens[2];
						String station = tokens[3];
						CheckInService checkInService = new CheckInService();
						if(station.equals("CENTRAL")){
							checkInService.setCentralCollectionMap(cardNumber, passengerType, station);
						}else if(station.equals("AIRPORT")){
							checkInService.setAirportCollectionMap(cardNumber, passengerType, station);
						}
						break;
					case "PRINT_SUMMARY":
						PrintService printService = new PrintService();
						System.out.println(printService.getPrintSummary());
						break;
					default:
						System.out.println("Invalid command: " + line);
				}
			}
		} catch (IOException e) {
			System.out.println("Error reading file: " + e.getMessage());
		}
	}

}
