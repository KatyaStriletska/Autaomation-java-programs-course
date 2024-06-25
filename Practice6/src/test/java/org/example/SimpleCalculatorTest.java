package org.example;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;


import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.*;

class SimpleCalculatorTest {

    @Test
    @DisplayName("Testing whether 10 * 2 = 20")
    void multiplicationTwoNumbers() {
        var calculator = new SimpleCalculator();
        assertEquals(20, calculator.multiplication(10, 2));
    }

    @Test
    void divisionTwoNumbers() {
        var calculator = new SimpleCalculator();
        assumeTrue(calculator.division(10, 5) == 2);
        assertThrows(IllegalArgumentException.class,
                () -> {
                    calculator.division(10, 0);
                },
                "Second number must be more then 0!");
    }

    @ParameterizedTest(name = "Testing many variants for multiplication")
    @CsvSource({
            "0, 1, 0",
            "3, 6, 18",
            "7, 4, 28",
            "1, -8, -8",
            "9, 9, 81"
    })
    void multiplication(int a, int b, int expectedResult) {
        var calculator = new SimpleCalculator();
        assertEquals(expectedResult, calculator.multiplication(a, b),
                () -> a + " + " + b + " should equal " + expectedResult);
    }

}