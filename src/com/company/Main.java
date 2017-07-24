package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        VehicleInfo car1 = new VehicleInfo();

        System.out.println("Enter info on your car!");

        System.out.println("Enter your VIN: ");
        int userVIN = input.nextInt();
        car1.setVIN(userVIN);

        System.out.println("Enter your odometer in miles: ");
        double userOdometer = input.nextDouble();
        car1.setOdometer(userOdometer);

        System.out.println("Enter your gas consumption in gallons: ");
        double userConsumption = input.nextDouble();
        car1.setConsumption(userConsumption);

        System.out.println("Enter the miles where you last did an oil change: ");
        double userOilChange = input.nextDouble();
        car1.setLastOilChangeMiles(userOilChange);

        System.out.println("Enter your engine size: ");
        double userEngine = input.nextDouble();
        car1.setEngineSize(userEngine);

        TelematicsService.report(car1);
    }
}
