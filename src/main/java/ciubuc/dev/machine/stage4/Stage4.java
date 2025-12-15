package ciubuc.dev.machine.stage4;

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
public class Stage4 {
    static final Scanner scanner = new Scanner(System.in);
    static CoffeeMachine machine = new CoffeeMachine();

    public static void main(String[] args) {

        getValuesCoffeeMachine();

        System.out.println();
        System.out.println("Write action (buy, fill, take):");
        String action = scanner.next();
        System.out.println();

        switch (action) {
            case "buy" -> buy();
            case "fill" -> fill();
            case "take" -> take();
        }

        getValuesCoffeeMachine();
    }

    protected static void getValuesCoffeeMachine() {
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", machine.getWater());
        System.out.printf("%d ml of milk\n", machine.getMilk());
        System.out.printf("%d g of coffee beans\n", machine.getCoffee());
        System.out.printf("%d disposable cups\n", machine.getCups());
        System.out.printf("$%d of money\n", machine.getPrice());
    }

    protected static void prepareCoffee(int water, int milk, int coffee, int price) {
        machine.minusWater(water);
        machine.minusMilk(milk);
        machine.minusCoffee(coffee);
        machine.minusCups(1);
        machine.plusPrice(price);
    }

    protected static int inputNumberOfCups() {
        System.out.println("Write how many disposable cups you want to add:");
        int numberOfCups = 0;

        try {
            numberOfCups = scanner.nextInt();
        } catch (Exception e) {

        }
        return numberOfCups;
    }

    private static int inputWater() {
        System.out.println("Write how many ml of water you want to add:");
        int water = scanner.nextInt();

        return water;
    }

    private static int inputMilk() {
        System.out.println("Write how many ml of milk you want to add:");
        int milk = scanner.nextInt();

        return milk;
    }

    private static int inputCofee() {
        System.out.println("Write how many grams of coffee beans you want to add:");
        int coffee = scanner.nextInt();

        return coffee;
    }

    protected static int getRestOfCups(int posibleCupOfCoffee, int numberOfCups) {
        return posibleCupOfCoffee - numberOfCups;
    }

    protected static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino:");
        int typOfCoffee = scanner.nextInt();

        switch(typOfCoffee) {
            case 1 -> prepareCoffee(250, 0, 16, 4);
            case 2 -> prepareCoffee(350, 75, 20, 7);
            case 3 -> prepareCoffee(200, 100, 12, 6);
        }
    }

    protected static void fill() {
        machine.plusWater(inputWater());
        machine.plusMilk(inputMilk());
        machine.plusCoffee(inputCofee());
        machine.plusCups(inputNumberOfCups());
    }

    protected static void take() {
        System.out.println("I gave you " + machine.getPrice());
        System.out.println();
        machine.setPrice(0);
    }
}

class CoffeeMachine {
    static final int WATER_FOR_A_CUP = 200;
    static final int MILK_FOR_A_CUP = 50;
    static final int COFEE_FOR_A_CUP = 15;

    private int water = 400;
    private int milk = 540;
    private int coffee = 120;
    private int cups = 9;
    private int price = 550;


    public int getWater() {
        return this.water;
    }

    public void plusWater(int water) {
        this.water += water;
    }

    public void minusWater(int water) {
        this.water -= water;
    }

    public int getMilk() {
        return this.milk;
    }

    public void plusMilk(int milk) {
        this.milk += milk;
    }

    public void minusMilk(int milk) {
        this.milk -= milk;
    }

    public int getCoffee() {
        return this.coffee;
    }

    public void plusCoffee(int coffee) {
        this.coffee += coffee;
    }

    public void minusCoffee(int coffee) {
        this.coffee -= coffee;
    }

    public int getCups() {
        return this.cups;
    }

    public void plusCups(int cups) {
        this.cups += cups;
    }

    public void minusCups(int cups) {
        this.cups -= cups;
    }

    public int getPrice() {
        return this.price;
    }

    public void plusPrice(int price) {
        this.price += price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
