public class RAM {
    private final String type;
    private final int size;
    private final double weight;

    public RAM(String type, int size, double weight) {
        this.type = type;
        this.size = size;
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public int getSize() {
        return size;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return "Тип: " + type +
                ", Объём: " + size + "GB" +
                ", Вес: " + weight + " кг.";
    }

}
