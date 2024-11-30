package com.mcsimulation;

import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    private static TreeMap<Integer, Integer> finalPrices = new TreeMap<>();
    private static int stockStartingPrice = 100;

    /**
     * Performs the Monte Carlo simulation with the given constraints
     * @param S Number of steps within each walk
     * @param N Number of walks of the simulation
     */
    private static void monteCarloSimulation(Integer S, Integer N) {
        int currentPrice;
        Random random = new Random();

        for(int i = 0; i < N; i++) {
            currentPrice = stockStartingPrice;
            for(int j = 0; j < S; j++){
                int direction = random.nextBoolean() ? 1 : -1;
                currentPrice += direction;
            }
            finalPrices.put(currentPrice, finalPrices.getOrDefault(currentPrice, 0) + 1);
        }
    }

    /**
     * Prompt the user with input messages and validate there response
     * @param scanner Scanner object to listen to terminal inputs
     * @param prompt Then message to ask the user
     * @param min Min bound of acceptable integer input
     * @param max Max bound of acceptable integer input
     * @return The integer inputted once a valid one has been entered
     */
    private static int getValidInput(Scanner scanner, String prompt, int min, int max) {
        int input = 0;
        boolean isValid = false;

        while (!isValid) {
            System.out.print(prompt);
            if (scanner.hasNextInt()) {
                input = scanner.nextInt();
                if (input >= min && input <= max) {
                    isValid = true;
                } else {
                    System.out.println("Error: Input must be between " + min + " and " + max + ". Please try again.");
                }
            } else {
                System.out.println("Error: Invalid input. Please enter an integer.");
                scanner.next();
            }
        }

        return input;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int S = getValidInput(scanner, "Enter the number of steps per walk (1-100): ", 1, 100);
        int N = getValidInput(scanner, "Enter the number of walks (1-10000): ", 1, 10000);

        System.out.println("\nValid inputs received:");
        System.out.println("Number of steps (S): " + S);
        System.out.println("Number of walks (N): " + N);

        scanner.close();

        monteCarloSimulation(S, N);

        System.out.printf("\n%-7s | %-7s%n", "Price (£)", "Probability");
        System.out.println("--------------------------------------");
        finalPrices.forEach((key, value) -> {
            // Calculate the probabilities for each outputted price
            double probability = (double) value / N;
            System.out.printf("%-7d | %-7f%n", key, probability);
        });
    }
}