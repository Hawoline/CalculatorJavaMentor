public class RomanCalculator extends Calculator {
    public RomanCalculator(String expression) {
        super(expression);
    }

    @Override
    public int[] parse() {
        if (!Calculator.expressionHasValidNumbers(getExpression(), false)) {
            throw new NumberFormatException("Invalid format");
        }
        int indexOfOperator = indexOfArithmeticOperator();

        String firstRomanNumber = getExpression().substring(0, indexOfOperator);
        String secondRomanNumber = getExpression().substring(indexOfOperator + 1);

        RomanNumeral firstRomanNumeral = new RomanNumeral(firstRomanNumber);
        RomanNumeral secondRomanNumeral = new RomanNumeral(firstRomanNumber);

        int firstNumber = firstRomanNumeral.toInt();
        int secondNumber = secondRomanNumeral.toInt();

        return new int[] {firstNumber, secondNumber};
    }

    @Override
    public String formatResultToValidString() {
        RomanNumeral romanNumeral = new RomanNumeral(getResult());
        return romanNumeral.toString();
    }
}
