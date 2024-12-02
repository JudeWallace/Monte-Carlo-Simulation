package org.mcsimulation;

import lombok.Getter;

import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    @Getter
    private static TreeMap<Integer, Integer> finalPrices = new TreeMap<>();
    private final static int stockStartingPrice = 100;

    /**
     * Performs the Monte Carlo simulation with the given constraints
     * @param S Number of steps within each walk
     * @param N Number of walks within the simulation
     */
    public static void monteCarloSimulation(Integer S, Integer N) {
        int currentStepPrice;
        Random random = new Random();

        for(int i = 0; i < N; i++) {
            currentStepPrice = stockStartingPrice;
            for(int j = 0; j < S; j++){
                int stepRandomDirection = random.nextBoolean() ? 1 : -1;
                currentStepPrice += stepRandomDirection;
            }
            finalPrices.put(currentStepPrice, finalPrices.getOrDefault(currentStepPrice, 0) + 1);
        }
    }

    /**
     * Prompt the user with input messages and validate there response
     * @param scanner Scanner object to listen to terminal inputs
     * @param prompt The message to ask the user
     * @param min Min bound of acceptable integer input
     * @param max Max bound of acceptable integer input
     * @return The integer inputted once a valid one has been entered
     */
    private static int getValidInput(Scanner scanner, String prompt, int min, int max) {
        int input = 0;
        boolean isValid = false;

        // Loop until the user enters a valid input based on the prompt, and it's min/max bounds
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

    /**
     * Houses the logic and entry point of the project, getting user inputs, running the simulation and then outputting
     * the results
     * @param args command line arguments if any are entered
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Get user to enter the values of S and N for the simulation
        int S = getValidInput(scanner, "Enter the number of steps per walk (1-100): ", 1, 100);
        int N = getValidInput(scanner, "Enter the number of walks (1-100000): ", 1, 100000);

        System.out.println("\nValid inputs received. Running the Monte Carlo Simulations with:");
        System.out.println("Number of steps (S): " + S);
        System.out.println("Number of walks (N): " + N);

        scanner.close();

        // Run the simulation
        monteCarloSimulation(S, N);

        // Display the simulations results in a formatted table
        System.out.printf("\n%-10s | %-20s%n", "Price (£)", "Probability");
        System.out.println("--------------------------------------");
        finalPrices.forEach((key, value) -> {
            // Calculate the probabilities for each outputted price
            double probability = (double) value / N;
            System.out.printf("%-10d | %-20f%n", key, probability);
        });
    }
}