Feature: Simple number arithmetic operations

  Background:
    Given user have a simple calculator

  Scenario: 1. Multiply two numbers
    Given numbers 2 and 9
    When the user multiplies the numbers
    Then the result should be 18

  Scenario: 2. Division two numbers
    Given numbers 81 and 9
    When the user divides the numbers
    Then the result should be 9

  Scenario Outline: 3. Multiply two numbers <num1> & <num2>
    Given numbers <num1> and <num2>
    When the user multiplies the numbers
    Then the result should be <result>

    Examples:
      | num1 | num2 | result |
      | 7 | 0 | 0 |
      | 3 | 5 | 15 |
      | 4 | 5 | 20 |
      | 10 | 10 | 100 |