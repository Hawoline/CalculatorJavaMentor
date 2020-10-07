public abstract class Calculator {
    private String expression;
    private int result;

    public Calculator(String expression) {
        this.expression = expression.replace(" ", "");
    }

    public String getExpression() {
        return expression;
    }

    public void setExpression(String expression) {
        this.expression = expression.replace(" ", "");
    }

    public int getResult() {
        return result;
    }

    public static boolean expressionHasValidNumbers(String expression, boolean hasArabicNumbers) {
        char[] validNumbers;
        expression = expression.replace(" ", "");

        if (hasArabicNumbers) {
            validNumbers = new char[10];
            for (int i = 0; i < validNumbers.length; i++) {
                validNumbers[i] = (char) ('0' + i);
            }
        } else {
            validNumbers= new char[3];
            validNumbers[0] = 'I'; 
            validNumbers[1] = 'V';
            validNumbers[2] = 'X';
        }

        int countOfArithmeticOperators = 0;

        for (int i = 0; i < expression.length(); i++) {
            char symbol = expression.charAt(i);
            if (symbolIsArithmeticOperation(symbol)) {
                if (i == 0 || i == expression.length() - 1) {
                    return false;
                }
                countOfArithmeticOperators++;
                continue;
            }
            for (int j = 0; j < validNumbers.length; j++) {
                if (validNumbers[j] == symbol) {
                    break;
                }
                if (j == validNumbers.length - 1) {
                    return false;
                }
            }
        }

        return countOfArithmeticOperators == 1;
    }

    private static boolean symbolIsArithmeticOperation(char symbol) {
        return symbol == '/' || symbol == '-' || symbol == '+' || symbol == '*';
    }

    public int indexOfArithmeticOperator() {
        int result;
        if ((result=expression.indexOf('+')) != -1) {
            return result;
        }
        if ((result=expression.indexOf('-')) != -1) {
            return result;
        }
        if ((result=expression.indexOf('*')) != -1) {
            return result;
        }

        return expression.indexOf('/');
    }

    public void calc() {
        if (!(expressionHasValidNumbers(getExpression(), true) || expressionHasValidNumbers(getExpression(), false))) {
            throw new NumberFormatException("Invalid format");
        }
        int indexOfArithmeticOperator = indexOfArithmeticOperator();

        int[] numbers = parse();
        int firstNumber = numbers[0];
        int secondNumber = numbers[1];

        if (firstNumber < 0 || firstNumber > 10 || secondNumber < 0 || secondNumber > 10) {
            throw new IllegalStateException("Numbers are not included in 1-10");
        }
        int result = -1;

        switch (getExpression().charAt(indexOfArithmeticOperator)) {
            case '+':
                result = firstNumber + secondNumber;
                break;
            case '-':
                result = firstNumber - secondNumber;
                break;
            case '*':
                result = firstNumber * secondNumber;
                break;
            case '/':
                result = firstNumber / secondNumber;
                break;
        }
        this.result = result;
    }

    public abstract int[] parse();

    public abstract String formatResultToValidString();
}
