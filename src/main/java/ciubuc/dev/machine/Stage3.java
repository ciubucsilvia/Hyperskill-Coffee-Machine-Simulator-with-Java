package ciubuc.dev.machine;

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
public class Stage3 {
    static final Scanner scanner = new Scanner(System.in);
    static final int WATER_FOR_A_CUP = 200;
    static final int MILK_FOR_A_CUP = 50;
    static final int COFEE_FOR_A_CUP = 15;

    public static void main(String[] args) {
        int water = getWater();
        int milk = getMilk();
        int coffeeBeans = getCofee();

        int numberOfCups = getNumberOfCups();
        int posibleCupOfCoffee = getPosibleCupOfCoffee(water, milk, coffeeBeans);

        System.out.println(getMessages(numberOfCups, posibleCupOfCoffee));
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

    private static int getWater() {
        System.out.println("Write how many ml of water the coffee machine has:");
         int water = scanner.nextInt();

         return water;
    }

    private static int getMilk() {
        System.out.println("Write how many ml of milk the coffee machine has:");
        int milk = scanner.nextInt();

        return milk;
    }

    private static int getCofee() {
        System.out.println("Write how many grams of coffee beans the coffee machine has:");
        int coffee = scanner.nextInt();

        return coffee;
    }

    protected static int getPosibleCupOfCoffee(int water, int milk, int coffeeBeans) {
        int posibleCupOfCoffee = Math.min(
                Math.min(water/WATER_FOR_A_CUP, milk/MILK_FOR_A_CUP),
                Math.min(milk/MILK_FOR_A_CUP, coffeeBeans/COFEE_FOR_A_CUP)
        );

        return posibleCupOfCoffee;
    }

    protected static String getMessages(int numberOfCups, int posibleCupOfCoffee) {
        String text = "";
        int restCups = getRestOfCups(posibleCupOfCoffee, numberOfCups);

        if(posibleCupOfCoffee >= numberOfCups) {
            if(restCups > 0) {
                text = "Yes, I can make that amount of coffee (and even " +
                        restCups + " more than that)";
            } else {
                text = "Yes, I can make that amount of coffee";
            }
        } else {
            text = "No, I can make only " +
                    posibleCupOfCoffee + " cup(s) of coffee";
        }
        return text;
    }

    protected static int getRestOfCups(int posibleCupOfCoffee, int numberOfCups) {
        return posibleCupOfCoffee - numberOfCups;
    }
}
