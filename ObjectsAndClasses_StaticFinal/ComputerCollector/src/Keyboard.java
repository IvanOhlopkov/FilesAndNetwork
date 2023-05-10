public class Keyboard {
    private final String type;
    private final KeyboardHasLight keyboardHasLight;
    private final double weight;

    public Keyboard(String type, KeyboardHasLight keyboardHasLight, double weight) {
        this.type = type;
        this.keyboardHasLight = keyboardHasLight;
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public KeyboardHasLight getKeyboardHasLight() {
        return keyboardHasLight;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return "Тип: " +
                ", Наличие подсветки: " + keyboardHasLight +
                ", вес: " + weight + " кг.";
    }
}
