package dev.machine.stage1;

/**
 * Make a first cup
 *
 * Description
 * Let's start with a program that makes you a coffee – virtual coffee, of course.
 * In this project, you will implement functionality that simulates a real coffee machine.
 * It can run out of ingredients, such as milk or coffee beans, it can offer you various types
 * of coffee, and, finally, it will take money for the prepared drink. Throughout the project,
 * you'll learn how to manage ingredients, take user input, and make decisions based on those
 * inputs.
 *
 * This stage introduces basic console output that represents the process of making a cup of
 * coffee. In the next stages, you will improve the program by adding user interaction and
 * resource management.
 *
 * Objective
 * The first version of the program just makes you a coffee. Your task is to simulate the
 * coffee-making process by printing each step to the console, showing what the coffee machine
 * does as it makes the drink.
 */
public class CoffeeMachine {
    public static void main(String[] args) {
        System.out.println("Starting to make a coffee");
        System.out.println("Grinding coffee beans");
        System.out.println("Boiling water");
        System.out.println("Mixing boiled water with crushed coffee beans");
        System.out.println("Pouring coffee into the cup");
        System.out.println("Pouring some milk into the cup");
        System.out.println("Coffee is ready!");
    }
}
