public class Display {
    private final double diagonal;
    private final DisplayType displayType;
    private final double weight;

    public Display(double diagonal, DisplayType displayType, double weight) {
        this.diagonal = diagonal;
        this.displayType = displayType;
        this.weight = weight;
    }

    public double getDiagonal() {
        return diagonal;
    }

    public DisplayType getDisplayType() {
        return displayType;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return "Диагональ: " + diagonal +
                ", Тип: " + displayType +
                ", Вес: " + weight + " кг.";
    }
}
