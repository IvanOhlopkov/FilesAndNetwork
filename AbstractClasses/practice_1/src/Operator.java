import java.util.Locale;

public class Operator implements Employee {
    private final double monthSalary;

    public Operator(double rateSalary) {
        this.monthSalary = rateSalary * Math.random() + rateSalary;
    }

    @Override
    public double getMonthSalary() {
        return monthSalary;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "%.2f", getMonthSalary()) + " руб.";
    }
}
