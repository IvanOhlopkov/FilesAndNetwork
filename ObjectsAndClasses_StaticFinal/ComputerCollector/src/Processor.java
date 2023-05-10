public class Processor {

    private final double frequency;
    private final int cores;
    private final String producer;
    private final double weight;

    public Processor(double frequency, int cores, String producer, double weight) {
        this.frequency = frequency;
        this.cores = cores;
        this.producer = producer;
        this.weight = weight;
    }

    public double getFrequency() {
        return frequency;
    }

    public int getCores() {
        return cores;
    }

    public String getProducer() {
        return producer;
    }

    public double getWeight() {
        return weight;
    }

    public String toString() {
        return "Частота: " + frequency + "GHz" +
                ", Количество ядер: " + cores +
                ", Производитель: " + producer +
                ", Вес: " + weight + " кг.";
    }
}
