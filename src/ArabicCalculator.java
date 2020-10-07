public class ArabicCalculator extends Calculator{
    public ArabicCalculator(String expression) {
        super(expression);
    }

    @Override
    public int[] parse() {
        int indexOfArithmeticOperator = indexOfArithmeticOperator();
        int firstNumber = Integer.parseInt(getExpression().substring(0, indexOfArithmeticOperator));
        int secondNumber = Integer.parseInt(getExpression().substring(indexOfArithmeticOperator + 1));

        return new int[]{firstNumber, secondNumber};
    }

    @Override
    public String formatResultToValidString() {
        return String.valueOf(getResult());
    }
}
