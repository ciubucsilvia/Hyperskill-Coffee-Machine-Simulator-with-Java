package dev.machine;

import java.util.Scanner;

/**
 * Brush up your code
 *
 * Description
 * In this stage, let's improve the design of our program by organizing it into classes that represent different parts
 * of the coffee machine. For instance, we can create one class for the coffee machine itself and another class to
 * represent each type of coffee with its ingredients and cost. This approach helps structure the code better, allowing
 * for easier reuse and extension. Each class should have methods that handle specific tasks, working together to
 * process the user input and manage the coffee machine's operations.
 *
 * Your program should handle the user's input through methods in these classes. Every time the user inputs something,
 * it will be processed by these methods to update the state of the machine. This setup simulates how real-world
 * machines operate, where each part has a defined role.
 *
 * As the coffee machine operates, it will keep track of its resources, including water, milk, coffee beans, disposable
 * cups, and the cash collected. Each action taken by the user should be processed in the context of the machine's
 * current state, which reflects the available resources.
 *
 * Additionally, we'll introduce a new action: cleaning. The coffee machine will monitor how many coffees
 * have been made. After producing 10 cups, it will require cleaning. During this action, the machine will not be able
 * to make any more coffee until it is cleaned by the user typing "clean". After cleaning, the machine resumes its
 * normal operations.
 *
 * Remember, that:
 *
 * For a cup of espresso, the coffee machine needs 250 ml of water and 16 g of coffee beans. It costs $4.
 * For a latte, the coffee machine needs 350 ml of water, 75 ml of milk, and 20 g of coffee beans. It costs $7.
 * And for a cappuccino, the coffee machine needs 200 ml of water, 100 ml of milk, and 12 g of coffee beans. It costs $6.
 * Objective
 * Your final task is to refactor the program to ensure you can interact with the coffee machine through methods in
 * the classes you created. Implement the cleaning action, where the machine requires cleaning after 10 cups of coffee
 * are made. Once cleaned, the machine can make coffee again.
 *
 */
public class CoffeeMachine {
    final static Scanner scanner = new Scanner(System.in);

    private int water;
    private int milk;
    private int beans;
    private int cups;
    private int money;

    //    Number of coffees made since last cleaning
    protected int coffeeMadeSinceCleaning;
    private static final int CLEANING_THRESHOLD = 10;

    public enum Action {
        BUY, FILL, TAKE, REMAINING, CLEAN, EXIT
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
        this.coffeeMadeSinceCleaning = 0;
    }

    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);
        boolean running = true;

        while (running) {
            System.out.println("Write action (buy, fill, take, clean, remaining, exit):");

            try {
                String action = scanner.nextLine().trim().toUpperCase();
                System.out.println();

                switch (Action.valueOf(action.toUpperCase())) {
                    case Action.BUY -> {
                        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, " +
                                "3 - cappuccino, back - to main menu:");
                        String choice = scanner.nextLine().trim();
                        if (!choice.equals("back")) {
                            System.out.println(machine.buy(Integer.parseInt(choice)));
                        }
                        System.out.println();
                    }
                    case Action.FILL -> {
                        int newWater = readInput("Write how many ml of water you want to add:");
                        int newMilk = readInput("Write how many ml of milk you want to add:");
                        int newBeans = readInput("Write how many grams of coffee beans you want to add:");
                        int newCups = readInput("Write how many disposable cups you want to add:");
                        machine.fill(newWater, newMilk, newBeans, newCups);
                        System.out.println();
                    }
                    case Action.TAKE -> {
                        System.out.println("I gave you " + machine.take());
                        System.out.println();
                    }
                    case Action.REMAINING -> {
                        System.out.println(machine.getStatus());
                        System.out.println();
                    }
                    case Action.CLEAN -> {
                        machine.clean();
                        System.out.println("I have been cleaned!");
                    }
                    case Action.EXIT -> running = false;
                }
            } catch (Exception e) {

            }
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
        return scanner.hasNextLine()
                ? Integer.parseInt(scanner.nextLine().trim())
                : 0;
    }

    public String buy(int type) {
        if(coffeeMadeSinceCleaning >= CLEANING_THRESHOLD) {
            return "I need cleaning!";
        }

        CoffeeType coffee = switch(type) {
            case 1 -> CoffeeType.ESPRESSO;
            case 2 -> CoffeeType.LATTE;
            case 3 -> CoffeeType.CAPPUCCINO;
            default -> null;
        };

        if(coffee != null) {
            boolean isWater = checkIfExistsIngredient(water, coffee.water);
            boolean isMilk = checkIfExistsIngredient(milk, coffee.milk);
            boolean isBeans = checkIfExistsIngredient(beans, coffee.beans);
            boolean isCups = checkIfExistsIngredient(cups, 1);

            if(isWater && isMilk && isBeans && isCups) {
                water -= coffee.water;
                milk -= coffee.milk;
                beans -= coffee.beans;
                cups--;
                money += coffee.cost;
                coffeeMadeSinceCleaning++;

                return "I have enough resources, making you a coffee!";
            } else if(!isWater) {
                return "Sorry, not enough water!";
            } else if(!isMilk) {
                return "Sorry, not enough milk!";
            } else if(!isBeans) {
                return "Sorry, not enough coffee!";
            }
            System.out.println();
        }
        return "";
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

    public void clean() {
        coffeeMadeSinceCleaning = 0;
    }

    protected static boolean checkIfExistsIngredient(int resource, int ingredient) {
        return resource - ingredient >= 0;
    }
}