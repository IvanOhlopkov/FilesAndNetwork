import java.util.Locale;

public class Manager implements Employee {
    final int MIN_EARNED_MONEY = 115_000;
    final int MAX_EARNED_MONEY = 140_000;
    final double BONUS_PERCENT = 0.05;
    private final double monthSalary;
    private final double sales;

    public Manager(double managerSalaryRate) {
        sales = MIN_EARNED_MONEY + (int) (Math.random() * MAX_EARNED_MONEY + 1);
        monthSalary = managerSalaryRate + sales * BONUS_PERCENT;
    }

    @Override
    public double getMonthSalary() {
        return monthSalary;
    }

    public double getSales() {
        return sales;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "%.2f", getMonthSalary()) + " руб.";
    }
}
