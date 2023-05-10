public class HardDisk {
    private final HardDiskType hardDiskType;
    private final int memorySize;
    private final double weight;

    public HardDisk(HardDiskType hardDiskType, int memorySize, double weight) {
        this.hardDiskType = hardDiskType;
        this.memorySize = memorySize;
        this.weight = weight;
    }

    public HardDiskType getHardDiskType() {
        return hardDiskType;
    }

    public int getMemorySize() {
        return memorySize;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return "Тип: " + hardDiskType +
                ", Объём памяти: " + memorySize + "GB" +
                ", Вес: " + weight + " кг.";
    }
}
