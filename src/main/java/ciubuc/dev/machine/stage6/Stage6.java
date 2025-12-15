package ciubuc.dev.machine.stage6;

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
public class Stage6 {
    public static void main(String[] args) {
        CoffeeMachine machine = new CoffeeMachine();
        machine.start();
    }
}

/**
 * Represents all possible states/actions
 * that the coffee machine can handle.
 */
enum State {
    BUY,
    FILL,
    TAKE,
    CLEAR,
    REMAINING,
    EXIT
}

/**
 * Enum representing available beverages.
 * Each beverage has fixed resource requirements and cost.
 */
enum Beverage {
    // Beverage definitions with required resources and price
    ESPRESSO(250, 0, 16, 4),
    LATTE(350, 75, 20, 7),
    CAPPUCCINO(200, 100, 12, 6);

    // Required resources
    private final int water;
    private final int milk;
    private final int coffee;

    // Beverage price
    private final int cost;

    /**
     * Enum constructor.
     *
     * @param water
     * @param milk
     * @param coffee
     * @param cost
     */
    Beverage(int water, int milk, int coffee, int cost) {
        this.water = water;
        this.milk = milk;
        this.coffee = coffee;
        this.cost = cost;
    }

    public int getWater() {
        return water;
    }

    public int getMilk() {
        return milk;
    }

    public int getCoffee() {
        return coffee;
    }

    public int getCost() {
        return cost;
    }
}

/**
 * Represents the coffee machine.
 * Handles resources, user actions, and machine logic.
 */
class CoffeeMachine {
//    Scanner used for user input
    static final Scanner scanner = new Scanner(System.in);

//    Machine resources
    protected int water;
    protected int milk;
    protected int coffee;
    protected int cups;
    protected int price;

//    Resource availability flags
    protected boolean isCups;
    protected boolean isWater;
    protected boolean isMilk;
    protected boolean isCoffee;

//    Number of coffees made since last cleaning
    protected static int coffeeMade = 0;

    /**
     * Starts the coffee machine.
     */
    public void start() {
        firstSettings();
        getAction();
    }

    /**
     * Initializes machine with default values.
     */
    protected void firstSettings() {
        this.water = 400;
        this.milk = 540;
        this.coffee = 120;
        this.cups = 9;
        this.price = 550;

        this.isCups = true;
        this.isWater = true;
        this.isMilk = true;
        this.isCoffee = true;
    }

    /**
     * Reads user action and dispatches it.
     */
    protected void getAction() {
        System.out.println("Write action (buy, fill, take, remaining, exit):");
        String action = scanner.next();

        try {
            switch (State.valueOf(action.toUpperCase())) {
                case State.BUY -> buy();
                case State.FILL -> fill();
                case State.TAKE -> take();
                case State.CLEAR -> clear();
                case State.REMAINING -> remaining();
                case State.EXIT -> exit();
            }
        } catch (Exception e) {
            getAction();
        }


    }

    /**
     *  Handles buying coffee.
     */
    protected void buy() {
        System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:");
        String typOfCoffee = scanner.next();

        try {
            switch(Integer.parseInt(typOfCoffee)) {
                case 1 -> prepareCoffee(Beverage.ESPRESSO);
                case 2 -> prepareCoffee(Beverage.LATTE);
                case 3 -> prepareCoffee(Beverage.CAPPUCCINO);
                default -> exit();
            }
            getAction();
        } catch (Exception e) {
            buy();
        }
    }

    /**
     * Handles filling the machine with resources.
     */
    protected void fill() {
        inputWater();
        inputMilk();
        inputCofee();
        inputNumberOfCups();

        System.out.println();
        getAction();
    }

    /**
     * Gives all money to the user.
     */
    protected void take() {
        System.out.println();
        System.out.println("I gave you $" + getPrice());
        System.out.println();

        setPrice(0);
        getAction();
    }

    /**
     * Cleans the coffee machine.
     */
    protected void clear() {
        System.out.println("I have been cleaned!");
        getAction();
    }

    /**
     * Displays current machine resources.
     */
    protected void remaining() {
        getValuesCoffeeMachine();
        getAction();
    }

    /**
     *  Stops the machine.
     */
    protected void exit() {
        // Program ends
    }

    /**
     * Prepares a selected beverage if enough resources exist.
     *
     * @param element
     */
    protected void prepareCoffee(Beverage element) {
        if(coffeeMade >= 10) {
            System.out.println("I need cleaning!");
            getAction();
        }

        isWater =  checkIfExistsIngredient(water, element.getWater());
        isMilk = checkIfExistsIngredient(milk, element.getMilk());
        isCoffee = checkIfExistsIngredient(coffee, element.getCoffee());
        isCups = checkIfExistsIngredient(cups, 1);

        if(isCups && isWater && isMilk && isCoffee) {
            minusWater(element.getWater());
            minusMilk(element.getMilk());
            minusCoffee(element.getCoffee());
            minusCups();
            plusPrice(element.getCost());

            coffeeMade++;

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

    /**
     * Prints all current resource values.
     */
    protected void getValuesCoffeeMachine() {
        System.out.println();
        System.out.println("The coffee machine has:");
        System.out.printf("%d ml of water\n", water);
        System.out.printf("%d ml of milk\n", milk);
        System.out.printf("%d g of coffee beans\n", coffee);
        System.out.printf("%d disposable cups\n", cups);
        System.out.printf("$%d of money\n", price);
        System.out.println();
    }

    /**
     * Checks if enough resource exists.
     *
     * @param resource
     * @param ingredient
     * @return
     */
    protected static boolean checkIfExistsIngredient(int resource, int ingredient) {
        return resource - ingredient > 0 ? true : false;
    }

    public void plusWater(int water) {
        this.water += water;
    }

    public void minusWater(int water) {
        this.water -= water;
    }

    public void plusMilk(int milk) {
        this.milk += milk;
    }

    public void minusMilk(int milk) {
        this.milk -= milk;
    }

    public void plusCoffee(int coffee) {
        this.coffee += coffee;
    }

    public void minusCoffee(int coffee) {
        this.coffee -= coffee;
    }

    public void plusCups(int cups) {
        this.cups += cups;
    }

    public void minusCups() {
        this.cups--;
    }

    public int getPrice() {
        return this.price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void plusPrice(int price) {
        this.price += price;
    }

    private void inputWater() {
        System.out.println("Write how many ml of water you want to add:");
        plusWater(scanner.nextInt());
    }

    private void inputMilk() {
        System.out.println("Write how many ml of milk you want to add:");
        plusMilk(scanner.nextInt());
    }

    private void inputCofee() {
        System.out.println("Write how many grams of coffee beans you want to add:");
        plusCoffee(scanner.nextInt());
    }

    protected void inputNumberOfCups() {
        System.out.println("Write how many disposable cups you want to add:");
        plusCups(scanner.nextInt());
    }
}
