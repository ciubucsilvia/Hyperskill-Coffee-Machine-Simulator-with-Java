package ciubuc.dev.machine;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Stage3Test {
    @Test
    void testGetMessages1() {
        Stage3 stage = new Stage3();

        int water = 300;
        int milk = 65;
        int coffeeBeans = 100;
        int numberOfCups = 1;

        int posibleCupOfCoffee = stage.getPosibleCupOfCoffee(water, milk, coffeeBeans);
        String result = stage.getMessages(numberOfCups, posibleCupOfCoffee);

        assertEquals(result, "Yes, I can make that amount of coffee");
    }

    @Test
    void testGetMessages2() {
        Stage3 stage = new Stage3();

        int water = 500;
        int milk = 250;
        int coffeeBeans = 200;
        int numberOfCups = 10;

        int posibleCupOfCoffee = stage.getPosibleCupOfCoffee(water, milk, coffeeBeans);
        String result = stage.getMessages(numberOfCups, posibleCupOfCoffee);

        assertEquals(result, "No, I can make only 2 cup(s) of coffee");
    }

    @Test
    void testGetMessages3() {
        Stage3 stage = new Stage3();

        int water = 1550;
        int milk = 299;
        int coffeeBeans = 300;
        int numberOfCups = 3;

        int posibleCupOfCoffee = stage.getPosibleCupOfCoffee(water, milk, coffeeBeans);
        String result = stage.getMessages(numberOfCups, posibleCupOfCoffee);

        assertEquals(result, "Yes, I can make that amount of coffee (and even 2 more than that)");
    }

    @Test
    void testGetMessages4() {
        Stage3 stage = new Stage3();

        int water = 0;
        int milk = 0;
        int coffeeBeans = 0;
        int numberOfCups = 1;

        int posibleCupOfCoffee = stage.getPosibleCupOfCoffee(water, milk, coffeeBeans);
        String result = stage.getMessages(numberOfCups, posibleCupOfCoffee);

        assertEquals(result, "No, I can make only 0 cup(s) of coffee");
    }

    @Test
    void testGetMessages5() {
        Stage3 stage = new Stage3();

        int water = 0;
        int milk = 0;
        int coffeeBeans = 0;
        int numberOfCups = 0;

        int posibleCupOfCoffee = stage.getPosibleCupOfCoffee(water, milk, coffeeBeans);
        String result = stage.getMessages(numberOfCups, posibleCupOfCoffee);

        assertEquals(result, "Yes, I can make that amount of coffee");
    }

    @Test
    void testGetMessages6() {
        Stage3 stage = new Stage3();

        int water = 200;
        int milk = 50;
        int coffeeBeans = 15;
        int numberOfCups = 0;

        int posibleCupOfCoffee = stage.getPosibleCupOfCoffee(water, milk, coffeeBeans);
        String result = stage.getMessages(numberOfCups, posibleCupOfCoffee);

        assertEquals(result, "Yes, I can make that amount of coffee (and even 1 more than that)");
    }
}