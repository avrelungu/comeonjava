package avrel.Calculator;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class Calculator {
    public int add(Integer num1, Integer num2)
    {
        return Integer.sum(num1, num2);
    }

    public int subtract(Integer num1, Integer num2)
    {
        return num1 - num2;
    }

    public int divide(Integer num1, Integer num2)
    {
        return num1 / num2;
    }

    public int multiply(Integer num1, Integer num2)
    {
        return num1 * num2;
    }
}
