package com.company;

import java.util.Scanner;

public class Main {

    // all in kJ
    double specificHeatIce = 2.108;
    double specificHeatWater = 4.184;
    double specificHeatVapor = 1.996;
    double LatentHeatFusion = .333;
    double LatentHeatVaporization = 2.257;


    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter Mass(In Grams):");
        double mass = reader.nextDouble();
        System.out.print("Enter Starting Temperature(In Celsius):");
        double startingTemp = reader.nextDouble();
        if (startingTemp < -273.14) {
            startingTemp = -273.14;
        }
        startingPhaseCalculator(startingTemp);
        System.out.print("Enter Ending Temperature(In Celsius):");
        double endingTemp = reader.nextDouble();
        if (endingTemp < -273.14) {
            endingTemp = -273.14;
        }
        System.out.println("Mass: " + mass + "\nStarting Temperature: " + startingTemp + "C" + "\nEnding Temperature: " + endingTemp + "C");

    }

    public static String startingPhaseCalculator (double startingTemp) {
        String startingPhase = new String();
        if (startingTemp > 100) {
            startingPhase = "vapor";
        } else if (startingTemp < 100 && startingTemp > 0) {
            startingPhase = "liquid";
        } else {
            startingPhase = "Ice";
        }
        return startingPhase;
    }
}
