package dev.machine.stage3;

import java.util.Scanner;

/**
 * Estimate the number of servings
 *
 * Description
 * A real coffee machine doesn't have an infinite supply of water, milk, or coffee beans. If you
 * request too many cups of coffee, it's almost certain that a real coffee machine wouldn't have
 * enough supplies to fulfill the order.
 *
 * In this stage, you need to improve the previous stage program. Now you will check amounts of
 * water, milk, and coffee beans available in the coffee machine at the moment.
 *
 * Objectives
 * Write a program that does the following:
 *
 * 1. Requests the amounts of water, milk, and coffee beans available at the moment, and then asks
 * for the number of cups of coffee a user needs.
 * 2. If the coffee machine has enough supplies to make the specified amount of coffee, the
 * program should print "Yes, I can make that amount of coffee".
 * 3. If the coffee machine can make more than the requested amount, the program should output
 * "Yes, I can make that amount of coffee (and even N more than that)", where N is the number of
 * additional cups of coffee that the coffee machine can make.
 * 4. If the available resources are insufficient to make the requested amount of coffee, the
 * program should output "No, I can make only N cup(s) of coffee".
 *
 * Like in the previous stage, the coffee machine needs 200 ml of water, 50 ml of milk, and
 * 15 g of coffee beans to make one cup of coffee.
 *
 */
public class CoffeeMachine {
    final static Scanner scanner = new Scanner(System.in);

    static final int WATER_FOR_A_CUP = 200;
    static final int MILK_FOR_A_CUP = 50;
    static final int COFEE_FOR_A_CUP = 15;

    public static void main(String[] args) {
        int water = readInput("Write how many ml of water the coffee machine has:");
        int milk = readInput("Write how many ml of milk the coffee machine has:");
        int coffeeBeans = readInput("Write how many grams of coffee beans the coffee machine has:");
        int numberOfCups = readInput("Write how many cups of coffee you will need:");

        String report  = getIngredientsReport(water, milk, coffeeBeans, numberOfCups);
        System.out.println(report);
    }

    public static String getIngredientsReport(int water, int milk, int coffeeBeans, int cups) {
        int possibleCups = calculateMaxCups(water, milk, coffeeBeans);
        return formatMessage(cups, possibleCups);
    }

    private static int readInput(String message) {
        System.out.println(message);
        return scanner.hasNextInt() ? scanner.nextInt() : 0;
    }

    public static int calculateMaxCups(int water, int milk, int coffeeBeans) {
        int posibleCupOfCoffee = Math.min(
                Math.min(water/WATER_FOR_A_CUP, milk/MILK_FOR_A_CUP),
                Math.min(milk/MILK_FOR_A_CUP, coffeeBeans/COFEE_FOR_A_CUP)
        );

        return posibleCupOfCoffee;
    }

    protected static String formatMessage(int requested, int possible) {
        if(possible >= requested) {
            if((possible - requested) > 0) {
                return "Yes, I can make that amount of coffee (and even " +
                        (possible - requested) + " more than that)";
            } else {
                return "Yes, I can make that amount of coffee";
            }
        } else {
            return "No, I can make only " +
                    possible + " cup(s) of coffee";
        }
    }

}