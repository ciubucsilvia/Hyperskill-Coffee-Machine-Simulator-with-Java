package dev.machine.stage5;

import java.util.Scanner;


/**
 * Keep track of the supplies
 *
 * Description
 *
 * Handling only a single action at a time is quite limited, so let's improve the program to handle multiple actions,
 * one after another. The program should repeatedly ask a user what they want to do. If the user types "buy", "fill"
 * or "take", then the program should behave exactly as it did in the previous stage. But unlike the previous stage,
 * where the state of the coffee machine was displayed before and after each action ( "buy", "fill" or "take"), the
 * state of the coffee machine should now be shown only when the user types "remaining". Additionally, if the user
 * wants to switch off the coffee machine, they should type "exit" to stop the program. In total, the program should
 * now five actions: "buy", "fill", "take", "remaining", and "exit".
 *
 * Remember, that:
 *      - For a cup of espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
 *      - For a latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
 *      - And for a cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee beans.
 *      It costs $6.
 *
 * Objectives
 *
 * Write a program that continuously processes user actions until the "exit" command is given. Additionally, introduce
 * two new options:
 *      - "remaining": to display the current state of the coffee machine
 *      - "exit": to switch off the coffee machine
 *
 * Remember, the coffee machine can run out of resources. If it doesn't have enough resources to make coffee, the program
 * should output a message that says it can't make a cup of coffee and indicate which resource is missing.
 *
 * And the last improvement to the program in this stage-if the user types "buy" to buy a cup of coffee but then changes
 * their mind, they should be able to type "back" to return into the main menu.
 *
 */
public class CoffeeMachine {
    final static Scanner scanner = new Scanner(System.in);

    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    public enum Action {
        BUY, FILL, TAKE, REMAINING, EXIT
    }

    public enum CoffeeType {
        ESPRESSO(250, 0, 16, 4),
        LATTE(350, 75, 20, 7),
        CAPPUCCINO(200, 100, 12, 6);

        final int water, milk, beans, cost;

        CoffeeType(int w, int m, int b, int c) {
            this.water = w;
            this.milk = m;
            this.beans = b;
            this.cost = c;
        }
    }

    public CoffeeMachine(int water, int milk, int beans, int cups, int money) {
        this.water = water;
        this.milk = milk;
        this.beans = beans;
        this.cups = cups;
        this.money = money;
    }

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
        run(machine);
    }

    public static void run(CoffeeMachine machine) {
        System.out.println("Write action (buy, fill, take, remaining, exit):");

        try {
            String action = scanner.next();
            System.out.println();

            switch (Action.valueOf(action.toUpperCase())) {
                case Action.BUY -> {
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, " +
                            "3 - cappuccino, back - to main menu:");
                    int choice = scanner.nextInt();
                    machine.buy(choice);
                    run(machine);
                }
                case Action.FILL -> {
                    int newWater = readInput("Write how many ml of water you want to add:");
                    int newMilk = readInput("Write how many ml of milk you want to add:");
                    int newBeans = readInput("Write how many grams of coffee beans you want to add:");
                    int newCups = readInput("Write how many disposable cups you want to add:");
                    machine.fill(newWater, newMilk, newBeans, newCups);
                    run(machine);
                }
                case Action.TAKE -> {
                    System.out.println("I gave you " + machine.take());
                    System.out.println();
                    run(machine);
                }
                case Action.REMAINING -> {
                    System.out.println(machine.getStatus());
                    System.out.println();
                    run(machine);
                }
                case Action.EXIT -> {

                }
            }
        } catch (Exception e) {

        }
    }

    public String getStatus() {
        StringBuilder sb = new StringBuilder();

        sb.append(String.format("The coffee machine has:%n"));
        sb.append(String.format("%d ml of water%n", water));
        sb.append(String.format("%d ml of milk%n", milk));
        sb.append(String.format("%d g of coffee beans%n", beans));
        sb.append(String.format("%d disposable cups%n", cups));
        sb.append(String.format("$%d of money", money));

        return sb.toString();
    }

    private static int readInput(String message) {
        System.out.println(message);
        return scanner.hasNextInt() ? scanner.nextInt() : 0;
    }

    public void buy(int type) {
        CoffeeType coffee = switch(type) {
            case 1 -> CoffeeType.ESPRESSO;
            case 2 -> CoffeeType.LATTE;
            case 3 -> CoffeeType.CAPPUCCINO;
            default -> null;
        };

        if(coffee != null) {
            boolean isWater = checkIfExistsIngredient(water, coffee.water);
            boolean isMilk = checkIfExistsIngredient(milk, coffee.milk);
            boolean isBeans = checkIfExistsIngredient(beans, coffee.milk);
            boolean isCups = checkIfExistsIngredient(cups, 1);

            if(isWater && isMilk && isBeans && isCups) {
                water -= coffee.water;
                milk -= coffee.milk;
                beans -= coffee.beans;
                cups--;
                money += coffee.cost;
                System.out.println("I have enough resources, making you a coffee!");
            } else if(!isWater) {
                System.out.println("Sorry, not enough water!");
            } else if(!isMilk) {
                System.out.println("Sorry, not enough milk!");
            } else if(!isBeans) {
                System.out.println("Sorry, not enough coffee!");
            }
            System.out.println();
        }
    }

    public void fill(int newWater, int newMilk, int newBeans, int newCups) {
        water += newWater;
        milk += newMilk;
        beans += newBeans;
        cups += newCups;
    }

    public int take() {
        int tmp = money;
        money = 0;
        return tmp;
    }

    protected static boolean checkIfExistsIngredient(int resource, int ingredient) {
        return resource - ingredient > 0;
    }
}