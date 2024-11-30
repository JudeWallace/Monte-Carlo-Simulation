package org.mcsimulation;

import java.util.Random;
import java.util.Scanner;
import java.util.TreeMap;

public class Main {

    private static TreeMap<Integer, Integer> finalPrices = new TreeMap<>();

    private static void monteCarloSimulation(Integer S, Integer N) {
        int currentPrice;
        Random random = new Random();

        for(int i = 0; i < N; i++) {
            currentPrice = 100;
            for(int j = 0; j < S; j++){
                int direction = random.nextBoolean() ? 1 : -1;
                currentPrice += direction;
            }
            finalPrices.put(currentPrice, finalPrices.getOrDefault(currentPrice, 0) + 1);
        }
    }

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

        System.out.println("Valid inputs received:");
        System.out.println("Number of steps (S): " + S);
        System.out.println("Number of walks (N): " + N);

        scanner.close();

        monteCarloSimulation(S, N);


        System.out.printf("%-5s | %-20s%n", "Price", "Probability");  // Header with column names
        System.out.println("--------------------------------------");  // Line separator
        finalPrices.forEach((key, value) -> {
            double probability = (double) value / N; // Calculate the probability
            System.out.printf("%-5d | %-20.5f%n", key, probability);  // Neat alignment
        });
    }
}