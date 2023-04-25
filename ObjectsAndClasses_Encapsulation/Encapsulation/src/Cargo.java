public class Cargo {
    private final Dimensions dimensions;
    private final double weight;
    private final String deliveryAddress;
    private final boolean canTurn;
    private final String registrationNumber;
    private final boolean hasFragile;

    public Cargo(Dimensions dimensions, double weight, String deliveryAddress, boolean canTurn,
                 String registrationNumber, boolean hasFragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.deliveryAddress = deliveryAddress;
        this.canTurn = canTurn;
        this.registrationNumber = registrationNumber;
        this.hasFragile = hasFragile;
    }

    public Cargo setDeliveryAddress(String deliveryAddress) {
        return new Cargo(dimensions, weight, deliveryAddress, canTurn, registrationNumber, hasFragile);
    }

    public Cargo setDimensions(Dimensions dimensions) {
        return new Cargo(dimensions, weight, deliveryAddress, canTurn, registrationNumber, hasFragile);
    }

    public Cargo setWeight(double weight) {
        return new Cargo(dimensions, weight, deliveryAddress, canTurn, registrationNumber, hasFragile);
    }

    public Dimensions getDimensions() {
        return dimensions;
    }

    public double getWeight() {
        return weight;
    }

    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public boolean isCanTurn() {
        return canTurn;
    }

    public String getRegistrationNumber() {
        return registrationNumber;
    }

    public boolean isHasFragile() {
        return hasFragile;
    }

    public String toString() {
        return "Груз - " + dimensions + "\n" + "Вес: " + weight + "\n" +
                "Адрес доставки: " + deliveryAddress + "\n" +
                "Можно ли переворачивать: " + (canTurn ? "Да" : "Нет") + "\n" +
                "Регистрационный номер: " + registrationNumber + "\n" +
                "Груз хрупкий: " + (hasFragile ? "Да" : "Нет");
    }
}
