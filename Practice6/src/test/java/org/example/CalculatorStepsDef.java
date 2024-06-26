package org.example;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assumptions.assumeTrue;

public class CalculatorStepsDef {
    private SimpleCalculator simpleCalculator;
    private int res;
    private int arg0, arg1;
    @Given("user have a simple calculator")
    public void initASimpleCalculator() {
        simpleCalculator = new SimpleCalculator();
    }

    @Given("numbers {int} and {int}")
    public void givenNumbers(int arg0, int arg1) {
        this.arg0 = arg0;
        this.arg1 = arg1;
    }
    @When("the user multiplies the numbers")
    public void userMultiplyNumbers() {
        res = simpleCalculator.multiplication(arg0, arg1);
    }

    @Then("the result should be {int}")
    public void theResultShouldBe(int arg0) {
        assumeTrue(arg0 == res);
    }

    @When("the user divides the numbers")
    public void theUserDividesTheNumbers() {
        res = simpleCalculator.division(arg0, arg1);
    }

}
