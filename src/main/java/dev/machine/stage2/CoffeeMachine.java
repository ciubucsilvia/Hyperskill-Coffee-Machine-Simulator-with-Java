package dev.machine.stage2;

import java.util.Scanner;

/**
 *Calculate the ingredients
 *
 * Description
 * Now let's consider a scenario where you need a lot of coffee—perhaps you're hosting a party
 * with many guests! In such cases, it's better to make preparations in advance.
 *
 * In this stage, you will ask the user to enter the desired number of coffee cups. Based on this
 * input, you will calculate the necessary amounts of water, coffee, and milk needed to prepare
 * the specified quantity of coffee.
 *
 * Please note that the coffee machine won't actually make any coffee in this stage; instead,
 * it will simply compute the required ingredients.
 *
 * Objectives
 * Let's break down the task into several steps:
 *
 * 1. Read the number of coffee cups from the input.
 * 2. Calculate the amount of each ingredient needed. One cup of coffee requires:
 *      200 ml of water
 *      50 ml of milk
 *      15 g of coffee beans
 * 3. Output the required ingredient amounts back to the user.
 *
 */
public class CoffeeMachine {
    final static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        int numberOfCups = getNumberOfCups();
        String report  = getIngredientsReport(numberOfCups);
        System.out.println(report);
    }

    public static int getNumberOfCups() {
        System.out.println("Write how many cups of coffee you will need: ");

        try {
            if(scanner.hasNextInt()) {
                return scanner.nextInt();
            }
        } catch (Exception e) {

        }
        return 0;
    }

    public static String getIngredientsReport(int cups) {
        StringBuilder sb = new StringBuilder();

        int water = 200 * cups;
        int milk = 50 * cups;
        int coffeeBeans = 15 * cups;

        sb.append(String.format("For %d cups of coffee you will need:\n", cups));
        sb.append(String.format("%d ml of water\n", water));
        sb.append(String.format("%d ml of milk\n", milk));
        sb.append(String.format("%d g of coffee beans", coffeeBeans));

        return sb.toString();
    }
}