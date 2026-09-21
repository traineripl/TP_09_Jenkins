package com.example.tp9;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculatorTest {

    private final Calculator calculator = new Calculator();

    @Test
    void shouldAddNumbers() {
        assertEquals(5, calculator.add(2, 3));
    }

    @Test
    void shouldSubtractNumbers() {
        assertEquals(5, calculator.subtract(8, 3));
    }

    @Test
    void shouldMultiplyNumbers() {
        assertEquals(15, calculator.multiply(3, 5));
    }

    @Test
    void shouldDivideNumbers() {
        assertEquals(4.0, calculator.divide(8, 2));
    }

    @Test
    void shouldRejectDivisionByZero() {
        assertThrows(IllegalArgumentException.class,
                () -> calculator.divide(8, 0));
    }
}
