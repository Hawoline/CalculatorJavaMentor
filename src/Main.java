import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Calculator calculator;

        String expression = scanner.nextLine();
        if (Calculator.expressionHasValidNumbers(expression, true)) {
            calculator = new ArabicCalculator(expression);
            testCalculator(calculator);
        } else if (Calculator.expressionHasValidNumbers(expression, false)) {
            calculator = new RomanCalculator(expression);
            testCalculator(calculator);
        } else {
            throw new NumberFormatException();
        }
    }

    private static void testCalculator(Calculator calculator) {
        calculator.calc();
        System.out.println(calculator.formatResultToValidString());
    }
}
