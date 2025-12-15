package ciubuc.dev.machine.stage5;

import java.util.Scanner;

/**
 * Keep track of the supplies 6
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
public class Stage5 {
    static final Scanner scanner = new Scanner(System.in);
    static CoffeeMachine machine = new CoffeeMachine();

    public static void main(String[] args) {
        getAction();
    }

    protected static void getValuesCoffeeMachine() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", machine.getWater());
        System.out.printf("%d ml of milk\n", machine.getMilk());
        System.out.printf("%d g of coffee beans\n", machine.getCoffee());
        System.out.printf("%d disposable cups\n", machine.getCups());
        System.out.printf("$%d of money\n", machine.getPrice());
        System.out.println();
    }

    protected static void getAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = scanner.next();

        switch (action) {
            case "buy" -> buy();
            case "fill" -> fill();
            case "take" -> take();
            case "remaining" -> remaining();
            case "exit" -> exit();
        }
    }

    protected static void prepareCoffee(int water, int milk, int coffee, int price) {
        boolean isWater =  checkIfExistsIngredient(machine.getWater(), water);
        boolean isMilk = checkIfExistsIngredient(machine.getMilk(), milk);
        boolean isCoffee = checkIfExistsIngredient(machine.getCoffee(), coffee);
        boolean isCups = checkIfExistsIngredient(machine.getCups(), 1);

        if(isCups && isWater && isMilk && isCoffee) {
            machine.minusWater(water);
            machine.minusMilk(milk);
            machine.minusCoffee(coffee);
            machine.minusCups(1);
            machine.plusPrice(price);

            System.out.println("I have enough resources, making you a coffee!");
        } else if(isWater == false) {
            System.out.println("Sorry, not enough water!");
        } else if(isMilk == false) {
            System.out.println("Sorry, not enough milk!");
        } else if(isCoffee == false) {
            System.out.println("Sorry, not enough coffee!");
        }
        System.out.println();
    }

    protected static boolean checkIfExistsIngredient(int resource, int ingredient) {
        return resource - ingredient > 0 ? true : false;
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

    protected static void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String typOfCoffee = scanner.next();

        switch(Integer.parseInt(typOfCoffee)) {
            case 1 -> prepareCoffee(250, 0, 16, 4);
            case 2 -> prepareCoffee(350, 75, 20, 7);
            case 3 -> prepareCoffee(200, 100, 12, 6);
            default -> exit();
        }

        getAction();
    }

    protected static void fill() {
        machine.plusWater(inputWater());
        machine.plusMilk(inputMilk());
        machine.plusCoffee(inputCofee());
        machine.plusCups(inputNumberOfCups());

        System.out.println();
        getAction();
    }

    protected static void take() {
        System.out.println();
        System.out.println("I gave you $" + machine.getPrice());
        System.out.println();

        machine.setPrice(0);
        getAction();
    }

    protected static void remaining() {
        getValuesCoffeeMachine();
        getAction();
    }

    protected static void exit() {

    }
}

class CoffeeMachine {
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

    public boolean minusCoffee(int coffee) {
        if(this.coffee - coffee < 0) {
            return false;
        }
        this.coffee -= coffee;
        return true;
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
