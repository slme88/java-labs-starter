package edu.course.lab03;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PriceCalculatorTest {

    @Test
    void testNoDiscount() {
        DiscountPolicy policy = new NoDiscount();
        PriceCalculator calculator = new PriceCalculator(policy);
        assertEquals(100.0, calculator.calculate(100.0), 0.001);
    }

    @Test
    void testNoDiscountNegativePriceThrowsException() {
        DiscountPolicy policy = new NoDiscount();
        PriceCalculator calculator = new PriceCalculator(policy);
        assertThrows(IllegalArgumentException.class, () -> calculator.calculate(-10.0));
    }

    @Test
    void testPercentDiscount() {
        DiscountPolicy policy = new PercentDiscount(20.0);
        PriceCalculator calculator = new PriceCalculator(policy);
        assertEquals(80.0, calculator.calculate(100.0), 0.001);
    }

    @Test
    void testPercentDiscountInvalidPercentThrowsException() {
        assertThrows(IllegalArgumentException.class, () -> new PercentDiscount(150.0));
    }

    @Test
    void testPolicyReplacement() {
        PriceCalculator calculator = new PriceCalculator(new NoDiscount());
        assertEquals(100.0, calculator.calculate(100.0), 0.001);

        calculator = new PriceCalculator(new PercentDiscount(50.0));
        assertEquals(50.0, calculator.calculate(100.0), 0.001);
    }
}