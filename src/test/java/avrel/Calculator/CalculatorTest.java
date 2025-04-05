package avrel.Calculator;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;


@Tag("Calculator")
public class CalculatorTest {
    private Calculator calculator;

    private Calculator getCalculator() {
        return this.calculator;
    }

    @BeforeEach
    public void setUp() {
        this.calculator = new Calculator();
    }

    // ADD METHOD
    @Nested
    @DisplayName("Calculator add method tests")
    @Tag("add")
    class addCalculatorNestedTest {
        @Nested
        @DisplayName("Add method with positive numbers")
        class addCalculatorNestedPositiveNumbersTest {
            @Test
            @DisplayName("Simple add")
            public void test_add() {
                var calculator = getCalculator();
                int num1 = 2;
                int num2 = 4;
                int expected = 6;

                int result = calculator.add(num1, num2);

                Assertions.assertEquals(expected, result);
            }

            @ParameterizedTest
            @ValueSource(ints = {100000, 999999})
            @DisplayName("Test add with large numbers")
            public void test_add_from_parametrized_large_numbers(int number) {
                var calculator = getCalculator();
                int expected = number * 2;

                int result = calculator.add(number, number);

                Assertions.assertEquals(expected, result);
            }
        }

        @Nested
        @DisplayName("Add method with negative numbers")
        class addCalculatorNestedNegativeNumbersTest {
            @Test
            @DisplayName("Add with a negative number")
            public void test_add_with_negative_number() {
                var calculator = getCalculator();
                int num1 = 2;
                int num2 = -4;
                int expected = -2;

                int result = calculator.add(num1, num2);

                Assertions.assertEquals(expected, result);
            }

            @Test
            @DisplayName("Add with both negative numbers")
            public void test_add_with_both_negative_numbers() {
                var calculator = getCalculator();
                int num1 = -2;
                int num2 = -4;
                int expected = -6;

                int result = calculator.add(num1, num2);

                Assertions.assertEquals(expected, result);
            }
        }

        @Nested
        @DisplayName("Add method with null/empty values")
        class addCalculatorNestedNullValuesTest {
            @ParameterizedTest
            @NullSource
            @DisplayName("Add with null parameter throws NullPointerException")
            public void test_add_nested_null_inputs(Integer number) {
                var calculator = getCalculator();

                Assertions.assertThrows(NullPointerException.class, () -> {
                    calculator.add(number, number);
                });
            }

            @ParameterizedTest
            @EmptySource
            @DisplayName("Add throws NumberFormatException for empty String values")
            public void test_add_nested_null_and_empty_inputs(String number) {
                var calculator = getCalculator();

                Assertions.assertThrows(NumberFormatException.class, () -> {
                    calculator.add(Integer.valueOf(number), Integer.valueOf(number));
                });
            }
        }

        @Nested
        @DisplayName("Add method tests from parametrized")
        class addCalculatorWithParametrizedTest {

            static List<Integer> numbersForFieldSource = new ArrayList<>(List.of(3, 4));

            static List<Integer> getNumbers() {
                return new ArrayList<>(List.of(1, 2));
            }

            @ParameterizedTest
            @MethodSource("getNumbers")
            @DisplayName("Add with parametrized from MethodSource")
            public void test_add_from_parametrized_method_source(Integer number) {
                var calculator = getCalculator();
                int expected = number * 2;

                int result = calculator.add(number, number);

                Assertions.assertEquals(expected, result);
            }

            @ParameterizedTest
            @FieldSource("numbersForFieldSource")
            @DisplayName("Add with parametrized from FieldSource")
            public void test_add_from_parametrized_field_source(Integer number) {
                var calculator = getCalculator();
                int expected = number * 2;

                int result = calculator.add(number, number);

                Assertions.assertEquals(expected, result);
            }
        }
    }

    // SUBTRACT METHOD
    @Nested
    @DisplayName("Calculator subtract method tests")
    @Tag("subtract")
    class subtractCalculatorNestedTest {
        @Nested
        @DisplayName("Subtract method with positive numbers")
        class subtractCalculatorNestedPositiveNumbersTest {
            @Test
            @DisplayName("Simple subtract")
            public void test_subtract() {
                var calculator = getCalculator();
                int num1 = 2;
                int num2 = 4;
                int expected = -2;

                int result = calculator.subtract(num1, num2);

                Assertions.assertEquals(expected, result);
            }

            @ParameterizedTest
            @ValueSource(ints = {100000, 999999})
            @DisplayName("Test subtract with large numbers")
            public void test_subtract_from_parametrized_large_numbers(int number) {
                var calculator = getCalculator();

                int result = calculator.subtract(number, number);

                Assertions.assertEquals(0, result);
            }
        }

        @Nested
        @DisplayName("Subtract method with negative numbers")
        class subtractCalculatorNestedNegativeNumbersTest {
            @Test
            @DisplayName("Subtract with a negative number")
            public void test_subtract_with_negative_number() {
                var calculator = getCalculator();
                int num1 = 2;
                int num2 = -4;
                int expected = 6;

                int result = calculator.subtract(num1, num2);

                Assertions.assertEquals(expected, result);
            }

            @Test
            @DisplayName("Subtract with both negative numbers")
            public void test_subtract_with_both_negative_numbers() {
                var calculator = getCalculator();
                int num1 = -2;
                int num2 = -4;
                int expected = 2;

                int result = calculator.subtract(num1, num2);

                Assertions.assertEquals(expected, result);
            }
        }

        @Nested
        @DisplayName("Subtract method with null/empty values")
        class subtractCalculatorNestedNullValuesTest {
            @ParameterizedTest
            @NullSource
            @DisplayName("Subtract with null parameter throws NullPointerException")
            public void test_subtract_nested_null_inputs(Integer number) {
                var calculator = getCalculator();

                Assertions.assertThrows(NullPointerException.class, () -> {
                    calculator.subtract(number, number);
                });
            }

            @ParameterizedTest
            @EmptySource
            @DisplayName("Subtract throws NumberFormatException for empty String values")
            public void test_subtract_nested_null_and_empty_inputs(String number) {
                var calculator = getCalculator();

                Assertions.assertThrows(NumberFormatException.class, () -> {
                    calculator.subtract(Integer.valueOf(number), Integer.valueOf(number));
                });
            }
        }

        @Nested
        @DisplayName("Subtract method tests from parametrized")
        class subtractCalculatorWithParametrizedTest {

            static List<Integer> numbersForFieldSource = new ArrayList<>(List.of(3, 4));

            static List<Integer> getNumbers() {
                return new ArrayList<>(List.of(1, 2));
            }

            @ParameterizedTest
            @MethodSource("getNumbers")
            @DisplayName("Subtract with parametrized from MethodSource")
            public void test_subtract_from_parametrized_method_source(Integer number) {
                var calculator = getCalculator();

                int result = calculator.subtract(number, number);

                Assertions.assertEquals(0, result);
            }

            @ParameterizedTest
            @FieldSource("numbersForFieldSource")
            @DisplayName("Subtract with parametrized from FieldSource")
            public void test_subtract_from_parametrized_field_source(Integer number) {
                var calculator = getCalculator();

                int result = calculator.subtract(number, number);

                Assertions.assertEquals(0, result);
            }
        }
    }

    // DIVIDE METHOD
    @Nested
    @DisplayName("Calculator divide method tests")
    @Tag("divide")
    class divideCalculatorNestedTest {
        @Nested
        @DisplayName("Divide method with positive numbers")
        class divideCalculatorNestedPositiveNumbersTest {
            @Test
            @DisplayName("Simple divide")
            public void test_divide() {
                var calculator = getCalculator();
                int num1 = 4;
                int num2 = 2;
                int expected = 2;

                int result = calculator.divide(num1, num2);

                Assertions.assertEquals(expected, result);
            }

            @ParameterizedTest
            @ValueSource(ints = {100000, 999999})
            @DisplayName("Test divide with large numbers")
            public void test_divide_from_parametrized_large_numbers(int number) {
                var calculator = getCalculator();

                int result = calculator.divide(number, number);

                Assertions.assertEquals(1, result);
            }
        }

        @Nested
        @DisplayName("Divide method with negative numbers")
        class divideCalculatorNestedNegativeNumbersTest {
            @Test
            @DisplayName("Divide with a negative number")
            public void test_divide_with_negative_number() {
                var calculator = getCalculator();
                int num1 = 44;
                int num2 = -11;
                int expected = -4;

                int result = calculator.divide(num1, num2);

                Assertions.assertEquals(expected, result);
            }

            @Test
            @DisplayName("Divide with both negative numbers")
            public void test_divide_with_both_negative_numbers() {
                var calculator = getCalculator();
                int num1 = 21;
                int num2 = -3;
                int expected = -7;

                int result = calculator.divide(num1, num2);

                Assertions.assertEquals(expected, result);
            }
        }

        @Nested
        @DisplayName("Divide method with null/empty values")
        class divideCalculatorNestedNullValuesTest {
            @ParameterizedTest
            @NullSource
            @DisplayName("Divide with null parameter throws NullPointerException")
            public void test_divide_nested_null_inputs(Integer number) {
                var calculator = getCalculator();

                Assertions.assertThrows(NullPointerException.class, () -> {
                    calculator.divide(number, number);
                });
            }

            @ParameterizedTest
            @EmptySource
            @DisplayName("Divide throws NumberFormatException for empty String values")
            public void test_divide_nested_null_and_empty_inputs(String number) {
                var calculator = getCalculator();

                Assertions.assertThrows(NumberFormatException.class, () -> {
                    calculator.divide(Integer.valueOf(number), Integer.valueOf(number));
                });
            }
        }

        @Nested
        @DisplayName("Divide method tests from parametrized")
        class divideCalculatorWithParametrizedTest {

            static List<Integer> numbersForFieldSource = new ArrayList<>(List.of(3, 4));

            static List<Integer> getNumbers() {
                return new ArrayList<>(List.of(1, 2));
            }

            @ParameterizedTest
            @MethodSource("getNumbers")
            @DisplayName("Divide with parametrized from MethodSource")
            public void test_divide_from_parametrized_method_source(Integer number) {
                var calculator = getCalculator();

                int result = calculator.divide(number, number);

                Assertions.assertEquals(1, result);
            }

            @ParameterizedTest
            @FieldSource("numbersForFieldSource")
            @DisplayName("Divide with parametrized from FieldSource")
            public void test_divide_from_parametrized_field_source(Integer number) {
                var calculator = getCalculator();

                int result = calculator.divide(number, number);

                Assertions.assertEquals(1, result);
            }
        }
    }

    // MULTIPLY METHOD
    @Nested
    @DisplayName("Calculator multiply method tests")
    @Tag("multiply")
    class multiplyCalculatorNestedTest {
        @Nested
        @DisplayName("Multiply method with positive numbers")
        class multiplyCalculatorNestedPositiveNumbersTest {
            @Test
            @DisplayName("Simple multiply")
            public void test_multiply() {
                var calculator = getCalculator();
                int num1 = 4;
                int num2 = 2;
                int expected = 8;

                int result = calculator.multiply(num1, num2);

                Assertions.assertEquals(expected, result);
            }

            @ParameterizedTest
            @ValueSource(ints = {100000, 999999})
            @DisplayName("Test multiply with large numbers")
            public void test_multiply_from_parametrized_large_numbers(int number) {
                var calculator = getCalculator();

                int result = calculator.multiply(number, number);

                Assertions.assertEquals(number * number, result);
            }
        }

        @Nested
        @DisplayName("Multiply method with negative numbers")
        class multiplyCalculatorNestedNegativeNumbersTest {
            @Test
            @DisplayName("Multiply with a negative number")
            public void test_multiply_with_negative_number() {
                var calculator = getCalculator();
                int num1 = 44;
                int num2 = -11;
                int expected = -484;

                int result = calculator.multiply(num1, num2);

                Assertions.assertEquals(expected, result);
            }

            @Test
            @DisplayName("Multiply with both negative numbers")
            public void test_multiply_with_both_negative_numbers() {
                var calculator = getCalculator();
                int num1 = 21;
                int num2 = -3;
                int expected = -63;

                int result = calculator.multiply(num1, num2);

                Assertions.assertEquals(expected, result);
            }
        }

        @Nested
        @DisplayName("Multiply method with null/empty values")
        class multiplyCalculatorNestedNullValuesTest {
            @ParameterizedTest
            @NullSource
            @DisplayName("Multiply with null parameter throws NullPointerException")
            public void test_multiply_nested_null_inputs(Integer number) {
                var calculator = getCalculator();

                Assertions.assertThrows(NullPointerException.class, () -> {
                    calculator.multiply(number, number);
                });
            }

            @ParameterizedTest
            @EmptySource
            @DisplayName("Multiply throws NumberFormatException for empty String values")
            public void test_multiply_nested_null_and_empty_inputs(String number) {
                var calculator = getCalculator();

                Assertions.assertThrows(NumberFormatException.class, () -> {
                    calculator.multiply(Integer.valueOf(number), Integer.valueOf(number));
                });
            }
        }

        @Nested
        @DisplayName("Multiply method tests from parametrized")
        class multiplyCalculatorWithParametrizedTest {

            static List<Integer> numbersForFieldSource = new ArrayList<>(List.of(3, 4));

            static List<Integer> getNumbers() {
                return new ArrayList<>(List.of(1, 2));
            }

            @ParameterizedTest
            @MethodSource("getNumbers")
            @DisplayName("Multiply with parametrized from MethodSource")
            public void test_multiply_from_parametrized_method_source(Integer number) {
                var calculator = getCalculator();

                int result = calculator.multiply(number, number);

                Assertions.assertEquals(Math.pow(number, 2), result);
            }

            @ParameterizedTest
            @FieldSource("numbersForFieldSource")
            @DisplayName("Multiply with parametrized from FieldSource")
            public void test_multiply_from_parametrized_field_source(Integer number) {
                var calculator = getCalculator();

                int result = calculator.multiply(number, number);

                Assertions.assertEquals(Math.pow(number, 2), result);
            }
        }
    }

    // Assumption
    @Test
    public void test_calculator_with_assumptions() {
        var calculator = getCalculator();
        int num1 = 2;
        int num2 = 2;

        Assumptions.assumingThat(4 == calculator.add(num1, num2), () -> {
            Assertions.assertEquals(0, calculator.subtract(num1, num2));
            Assertions.assertEquals(1, calculator.divide(num1, num2));
            Assertions.assertEquals(4, calculator.multiply(num1, num2));
        });
    }

    // Timeout
    @Test
    @Timeout(3)
    public void test_add_with_timeout() {
        var calculator = getCalculator();
        int num1 = 1;
        int num2 = 3;

        Assertions.assertEquals(4, calculator.add(num1, num2));
    }

    @Test
    @Timeout(value = 3000, unit = TimeUnit.MILLISECONDS)
    public void test_add_with_timeout_exceeded() {
        var calculator = getCalculator();
        int num1 = 1;
        int num2 = 3;

        Assertions.assertThrows(TimeoutException.class, () -> {
            CompletableFuture<Integer> future = CompletableFuture.supplyAsync(
                    () -> {
                        try {
                            Thread.sleep(2000);
                            return calculator.add(num1, num2);
                        } catch (InterruptedException e) {
                            throw new RuntimeException(e);
                        }
                    }
            );

            try {
                future.get(500, TimeUnit.MILLISECONDS);
            } catch (InterruptedException | ExecutionException e) {
                throw new RuntimeException(e);
            }
        });
    }

    // Mockito
    @Test
    public void test_calculator_with_mockito_mock_and_stub() {
        Calculator calculator = mock(Calculator.class);
        int num1 = 2;
        int num2 = 2;

        when(calculator.add(2, 2)).thenThrow(new NullPointerException()).thenReturn(3);

        Assertions.assertThrows(NullPointerException.class, () -> {calculator.add(num1, num2);});
        Assertions.assertEquals(3, calculator.add(num1, num2));
    }

    @Test
    public void test_calculator_with_mockito_spy()
    {
        Calculator calculator = new Calculator();
        Calculator spy = spy(calculator);

        int num1 = 2;
        int num2 = 2;

        when(spy.add(1, 2)).thenReturn(4).thenCallRealMethod();

        Assertions.assertEquals(4, calculator.add(num1, num2));
        Assertions.assertEquals(0, calculator.subtract(num1, num2));
    }
}
