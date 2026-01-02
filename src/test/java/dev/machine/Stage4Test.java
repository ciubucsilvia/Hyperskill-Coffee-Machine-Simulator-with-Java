package dev.machine;

import dev.machine.stage4.CoffeeMachine;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Stage4Test {
    public CoffeeMachine machine = new CoffeeMachine(400, 540, 120, 9, 550);

    @Test
    void testInitialIngredients() {

        String result = machine.getStatus();
        String expected = "The coffee machine has:\r\n" +
                "400 ml of water\r\n" +
                "540 ml of milk\r\n" +
                "120 g of coffee beans\r\n" +
                "9 disposable cups\r\n" +
                "$550 of money";

        assertEquals(expected, result);
    }

    @Test
    void testActionBuy() {
        machine.buy(3);
        String result = machine.getStatus();
        String expected = "The coffee machine has:\r\n" +
                "200 ml of water\r\n" +
                "440 ml of milk\r\n" +
                "108 g of coffee beans\r\n" +
                "8 disposable cups\r\n" +
                "$556 of money";

        assertEquals(expected, result);
    }

    @Test
    void testActionFill() {
        machine.fill(2000, 500, 100, 10);
        String result = machine.getStatus();
        String expected = "The coffee machine has:\r\n" +
                "2400 ml of water\r\n" +
                "1040 ml of milk\r\n" +
                "220 g of coffee beans\r\n" +
                "19 disposable cups\r\n" +
                "$550 of money";

        assertEquals(expected, result);
    }

    @Test
    void testActionTake() {
        machine.take();
        String result = machine.getStatus();
        String expected = "The coffee machine has:\r\n" +
                "400 ml of water\r\n" +
                "540 ml of milk\r\n" +
                "120 g of coffee beans\r\n" +
                "9 disposable cups\r\n" +
                "$0 of money";

        assertEquals(expected, result);
    }
}
