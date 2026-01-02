package dev.machine;

import dev.machine.stage2.CoffeeMachine;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class Stage2Test {
    @Test
    public void testIngredientsReport25() {
        String text = "For 25 cups of coffee you will need:\n" +
                "5000 ml of water\n" +
                "1250 ml of milk\n" +
                "375 g of coffee beans";
        assertEquals(CoffeeMachine.getIngredientsReport(25), text);
    }

    @Test
    public void testIngredientsReport125() {
        String text = "For 125 cups of coffee you will need:\n" +
                "25000 ml of water\n" +
                "6250 ml of milk\n" +
                "1875 g of coffee beans";
        assertEquals(CoffeeMachine.getIngredientsReport(125), text);
    }
}
