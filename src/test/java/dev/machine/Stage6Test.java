package dev.machine;

import dev.machine.stage6.CoffeeMachine;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class Stage6Test {
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
        String result = machine.buy(2);
        String expected = "I have enough resources, making you a coffee!";

        assertEquals(expected, result);

        String result2 = machine.getStatus();
        String expected2 = "The coffee machine has:\r\n" +
                "50 ml of water\r\n" +
                "465 ml of milk\r\n" +
                "100 g of coffee beans\r\n" +
                "8 disposable cups\r\n" +
                "$557 of money";

        assertEquals(expected2, result2);
    }
}