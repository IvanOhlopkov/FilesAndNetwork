public class Arithmetic {
    int numberOne;
    int numberTwo;

    public Arithmetic(int valueOne, int valueTwo) {
        numberOne = valueOne;
        numberTwo = valueTwo;
    }

    public int getTotal() {
        return numberOne + numberTwo;
    }

    public int getProductOfNumbers() {
        return numberOne * numberTwo;
    }

    public int getMaxOfNumbers() {
        return Math.max(numberOne, numberTwo);
    }

    public int getMinOfNumbers() {
        return Math.min(numberOne, numberTwo);
    }
}
