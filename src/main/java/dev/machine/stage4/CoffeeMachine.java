package dev.machine.stage4;

import java.util.Scanner;

/**
 * Add functions to your machine
 *
 * Description
 * Now, let's simulate an actual coffee machine! This coffee machine will have a limited supply of water, milk,
 * coffee beans, and disposable cups. Additionally, it will track how much money it earns from selling coffee.
 *
 * The coffee machine will have three main functions:
 *
 * 1. It can sell different types of coffee: espresso, latte, and cappuccino. Of course, each variety would require
 * a different amount of supplies, however, in any case, would need only one disposable cup for a drink.
 * 2. A special worker should be able to replenish the machine's supplies.
 * 3. Another special worker should be able to collect the money earned by the machine.
 *
 * Objectives
 * Write a program that offers three actions: buying coffee, refilling supplies, or taking its money out. Note that
 * the program is supposed to perform only one of the mentioned actions at a time for each input. It should update
 * the coffee machine's state accordingly i.e. calculate the amounts of remaining ingredients and the total money
 * collected; and display them before and after each action.
 *
 * 1. First, your program reads one option from the standard input, which can be "buy", "fill", "take". If a user wants
 * to buy some coffee, the input is "buy". If a special worker thinks that it is time to fill out all the supplies for
 * the coffee machine, the input line will be "fill". If another special worker decides that it is time to take out the
 * money from the coffee machine, you'll get the input "take".
 * 2. If the user writes "buy" then they must choose one of three types of coffee that the coffee machine can make:
 * espresso, latte, or cappuccino.
 *       - For a cup of espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
 *       - For a latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
 *       - And for a cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee beans.
 *       It costs $6.
 * 3. If the user writes "fill", the program should ask them how much water, milk, coffee beans, and how many disposable
 * cups they want to add into the coffee machine.
 * 4. If the user writes "take" the program should give all the money that it earned from selling coffee.
 *
 * In summary, your program should display the coffee machine's current state, process one user action, and then display
 * the updated state. Aim to implement each action using separate functions.
 */
public class CoffeeMachine {
    final static Scanner scanner = new Scanner(System.in);

    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    public enum Action {
        BUY, FILL, TAKE
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
        System.out.println(machine.getStatus());

        System.out.println();
        System.out.println("Write action (buy, fill, take):");

        try {
            String action = scanner.next();
//            System.out.println();

            switch (Action.valueOf(action.toUpperCase())) {
                case Action.BUY -> {
                    System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
                    int choice = scanner.nextInt();
                    machine.buy(choice);
                }
                case Action.FILL -> {
                    int newWater = readInput("Write how many ml of water you want to add:");
                    int newMilk = readInput("Write how many ml of milk you want to add:");
                    int newBeans = readInput("Write how many grams of coffee beans you want to add:");
                    int newCups = readInput("Write how many disposable cups you want to add:");
                    machine.fill(newWater, newMilk, newBeans, newCups);
                }
                case Action.TAKE -> {
                    System.out.println("I gave you " + machine.take());
                }
            }

            System.out.println("\n" + machine.getStatus());
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
            water -= coffee.water;
            milk -= coffee.milk;
            beans -= coffee.beans;
            cups--;
            money += coffee.cost;
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
}