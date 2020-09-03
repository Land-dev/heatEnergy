package com.company;

import java.util.Scanner;

public class Main {

    // all in kJ
    public static final double specificHeatSolid = 2.108;
    public static final double specificHeatWater = 4.184;
    public static final double specificHeatVapor = 1.996;
    public static final double latentHeatFusion = .333;
    public static final double latentHeatVaporization = 2.257;


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


        String startingPhase = phaseCalculator(startingTemp);
        String endingPhase = phaseCalculator(endingTemp);

        System.out.println("Mass: " + mass + "\nStarting Temperature: " + startingTemp + "C" + " (" + startingPhase + ")"+
                "\nEnding Temperature: " + endingTemp + "C" + " (" + endingPhase + ")");


        String phase = "";
        double vaporTempChange = 0;
        boolean endothermic = endothermic(tempChange);

        double iceEnergy = iceEnergy(mass, startingTemp, endingTemp, endingPhase, endothermic);
        double fusionEnergy = fusion(mass, endothermic);
        double liquidEnergy = liquidEnergy(mass, startingTemp, endingTemp, endingPhase, endothermic);
        double vaporizationEnergy = vaporization(mass, endothermic);
        double vaporEnergy = vaporEnergy(mass, startingTemp, endingTemp, endingPhase, endothermic);

        double heatEnergy = iceEnergy + vaporEnergy + liquidEnergy;
        double phaseEnergy = fusionEnergy + vaporizationEnergy;
        double totalHeatEnergy = heatEnergy + phaseEnergy;

        System.out.println("Total Heat Energy: " + totalHeatEnergy + " kJ");
    }

    public static boolean endothermic (double tempChange) {
        boolean endothermic = false;
        if (tempChange > 0) {
            endothermic = true;
        }
        return endothermic;
    }

    public static String phaseCalculator (double phaseTemp) {
        String phase = new String();
        if (phaseTemp > 100) {
            phase = "Water Vapor";
        } else if (phaseTemp < 100 && phaseTemp > 0) {
            phase = "Liquid Water";
        } else {
            phase = "Ice";
        }
        return phase;
    }



    public static double iceEnergy (double mass, double startTemp, double endTemp, String endingPhase, boolean endothermic) {
        if (!endingPhase.equals("Ice")) {
            endTemp = 0;
        }

        double energyChange = round(mass * specificHeatSolid * (endTemp - startTemp));
        if (endothermic == true) {
            System.out.println("Heating (Ice): " + energyChange + " kJ");
        } else {
            System.out.println("Cooling (Ice): " + energyChange + " kJ");
        }

        return  energyChange;
    }

    public static double vaporEnergy (double mass, double startTemp, double endTemp, String endingPhase, boolean endothermic) {
        if (!endingPhase.equals("Water Vapor")) {
            endTemp = 100;
        }

        double energyChange = round(mass * specificHeatVapor * (endTemp - startTemp));
        if (endothermic == true) {
            System.out.println("Heating (Water Vapor): " + energyChange + " kJ");
        } else {
            System.out.println("Cooling (Water Vapor): " + energyChange + " kJ");
        }

        return  energyChange;
    }

    public static double liquidEnergy (double mass, double startTemp, double endTemp, String endingPhase, boolean endothermic) {
        if (endingPhase.equals("Ice")) {
            endTemp = 0;
        } else if (endingPhase.equals("Water Vapor")) {
            endTemp = 100;
        }

        double energyChange = round(mass * specificHeatWater * (endTemp - startTemp));
        if (endothermic == true) {
            System.out.println("Heating (Liquid Water): " + energyChange + " kJ");
        } else {
            System.out.println("Cooling (Liquid Water): " + energyChange + " kJ");
        }

        return  energyChange;
    }

    public static double fusion (double mass, boolean endothermic) {
        double fusionEnergy = 0;
        if (endothermic == true) {
            fusionEnergy = round(mass * latentHeatFusion);
            System.out.println("Phase Change (Melting): " + fusionEnergy + " kJ");
        } else {
            fusionEnergy = round(mass * -latentHeatFusion);
            System.out.println("Phase Change (Freezing): " + fusionEnergy + " kJ");
        }
        return fusionEnergy;
    }

    public static double vaporization (double mass, boolean endothermic) {
        double vaporizationEnergy = 0;
        if (endothermic == true) {
            vaporizationEnergy = round(mass * latentHeatVaporization);
            System.out.println("Phase Change (Evaporation): " + vaporizationEnergy + " kJ");
        } else {
            vaporizationEnergy = round(mass * -latentHeatVaporization);
            System.out.println("Phase Change (Condensation): " + vaporizationEnergy + " kJ");
        }
        return vaporizationEnergy;
    }

    public static double round (double x) {
        x *= 10;
        if (x > 0) {
            return (int)(x + 0.5)/10.0;
        } else {
            return  (int)(x);
        }
    }
}
