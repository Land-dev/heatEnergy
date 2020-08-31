package com.company;

import java.util.Scanner;

public class Main {

    // all in kJ
    public static final double specificHeatSolid = 2.108;
    public static final double specificHeatWater = 4.184;
    public static final double specificHeatVapor = 1.996;
    public static final double LatentHeatFusion = .333;
    public static final double LatentHeatVaporization = 2.257;


    public static void main(String[] args) {
        Scanner reader = new Scanner(System.in);
        System.out.print("Enter Mass(In Grams):");
        double mass = reader.nextDouble();
        System.out.print("Enter Starting Temperature(In Celsius):");
        double startingTemp = reader.nextDouble();
        if (startingTemp < -273.14) {
            startingTemp = -273.14;
        }
        System.out.print("Enter Ending Temperature(In Celsius):");
        double endingTemp = reader.nextDouble();
        if (endingTemp < -273.14) {
            endingTemp = -273.14;
        }


        double tempChange = endingTemp - startingTemp;


        String startingPhase = startingPhaseCalculator(startingTemp);
        String endingPhase = endingPhaseCalculator(endingTemp);

        System.out.println("Mass: " + mass + "\nStarting Temperature: " + startingTemp + "C" + " (" + startingPhase + ")"+
                "\nEnding Temperature: " + endingTemp + "C" + " (" + endingPhase + ")");

        double heatEnergy = 0;
        double phaseEnergy = 0;
        double totalHeatEnergy = 0;
        String phase = "";
        String heatingCooling = heatingOrCooling(tempChange);

        if (startingPhase == "Water Vapor" && endingPhase == "Water Vapor") {
            heatEnergy = mass * specificHeatVapor * tempChange;
            totalHeatEnergy = heatEnergy;
            phase = "Vapor";
        } else if (startingPhase == "Liquid Water" && endingPhase == "Liquid Water") {
            heatEnergy = mass * specificHeatWater * tempChange;
            totalHeatEnergy = heatEnergy;
            phase = "Liquid";
        } else if (startingPhase == "Ice" && endingPhase == "Ice") {
            heatEnergy = mass * specificHeatSolid * tempChange;
            totalHeatEnergy = heatEnergy;
            phase = "Ice";
        } else if (startingPhase == "Ice" && endingPhase == "Liquid Water") {

        }


        System.out.println("Total Heat Energy: " + totalHeatEnergy + " kJ");
    }

    public static String heatingOrCooling (double tempChange) {
        String heatingCooling = new String();
        if (tempChange > 0) {
            heatingCooling = "Heating";
        } else {
            heatingCooling = "Cooling";
        }
        return heatingCooling;
    }

    public static String startingPhaseCalculator (double startingTemp) {
        String startingPhase = new String();
        if (startingTemp > 100) {
            startingPhase = "Water Vapor";
        } else if (startingTemp < 100 && startingTemp > 0) {
            startingPhase = "Liquid Water";
        } else {
            startingPhase = "Ice";
        }
        return startingPhase;
    }

    public static String endingPhaseCalculator (double endingTemp) {
        String endingPhase = new String();
        if (endingTemp > 100) {
            endingPhase = "Water Vapor";
        } else if (endingTemp < 100 && endingTemp > 0) {
            endingPhase = "Liquid Water";
        } else {
            endingPhase = "Ice";
        }
        return endingPhase;
    }
}
