package dev.machine;

import dev.machine.stage3.CoffeeMachine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Stage3Test {
    @Test
    void testIngredientReport1() {
        // Limited by milk: 300 water, 65 milk, 100 beans
        String result = CoffeeMachine.getIngredientsReport(300, 65, 100, 1);
        String expected = "Yes, I can make that amount of coffee";
        assertEquals(expected, result);
    }

    @Test
    void testIngredientReport2() {
        // Limited by milk: 500 water, 250 milk, 200 beans
        String result = CoffeeMachine.getIngredientsReport(500, 250, 200, 10);
        String expected = "No, I can make only 2 cup(s) of coffee";
        assertEquals(expected, result);
    }

    @Test
    void testIngredientReport3() {
        // Limited by milk: 1550 water, 299 milk, 300 beans
        String result = CoffeeMachine.getIngredientsReport(1550, 299, 300, 3);
        String expected = "Yes, I can make that amount of coffee (and even 2 more than that)";
        assertEquals(expected, result);
    }

    @Test
    void testIngredientReport4() {
        // Limited by milk: 0 water, 0 milk, 0 beans
        String result = CoffeeMachine.getIngredientsReport(0, 0, 0, 1);
        String expected = "No, I can make only 0 cup(s) of coffee";
        assertEquals(expected, result);
    }

    @Test
    void testIngredientReport5() {
        // Limited by milk: 0 water, 0 milk, 0 beans
        String result = CoffeeMachine.getIngredientsReport(0, 0, 0, 0);
        String expected = "Yes, I can make that amount of coffee";
        assertEquals(expected, result);
    }

    @Test
    void testIngredientReport6() {
        // Limited by milk: 200 water, 50 milk, 15 beans
        String result = CoffeeMachine.getIngredientsReport(200, 50, 15, 0);
        String expected = "Yes, I can make that amount of coffee (and even 1 more than that)";
        assertEquals(expected, result);
    }
}