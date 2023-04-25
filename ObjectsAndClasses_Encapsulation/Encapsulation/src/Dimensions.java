public class Dimensions {
    private final double width;
    private final double height;
    private final double length;

    public Dimensions(double width, double height, double length) {
        this.width = width;
        this.height = height;
        this.length = length;
    }

    public Dimensions setWidth(double width) {
        return new Dimensions(width, height, length);
    }

    public Dimensions setHeight(double height) {
        return new Dimensions(width, height, length);
    }

    public Dimensions setLength(double length) {
        return new Dimensions(width, height, length);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }

    public double getLength() {
        return length;
    }

    public double calcCargoSize(double width, double height, double length) {
        return width * height * length;
    }

    public String toString() {
        return "Габариты: ширина - " + width + " высота " + height + " длина " + length + "\n" +
                "Объем: " + calcCargoSize(width, height, length);
    }
}
