package ciubuc.dev.machine;

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
public class Stage2 {
    final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        int numberOfCups = getNumberOfCups();

        int water = 200 * numberOfCups;
        int milk = 50 * numberOfCups;
        int coffeeBeans = 15 * numberOfCups;

        System.out.printf("For %d cups of coffee you will need:\n", numberOfCups);
        System.out.printf("%d ml of water\n", water);
        System.out.printf("%d ml of milk\n", milk);
        System.out.printf("%d g of coffee beans", coffeeBeans);
    }

    protected static int getNumberOfCups() {
        System.out.println("Write how many cups of coffee you will need: ");
        int numberOfCups = 0;
        try {
            numberOfCups = scanner.nextInt();
        } catch (Exception e) {

        }
        return numberOfCups;
    }
}
