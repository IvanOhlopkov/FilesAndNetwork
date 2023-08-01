import java.util.Locale;

public class TopManager implements Employee {
    final double MIN_INCOME_FOR_BONUS = 10_000_000;
    final double BONUS = 1.5;
    private final Company company;
    double monthSalary;

    public TopManager(double topManagerSalaryRate, Company company) {
        this.monthSalary = topManagerSalaryRate * Math.random() + topManagerSalaryRate;
        this.company = company;
    }

    @Override
    public double getMonthSalary() {
        if (company.getIncome() > MIN_INCOME_FOR_BONUS)
            return monthSalary * BONUS;
        return monthSalary;
    }

    @Override
    public String toString() {
        return String.format(Locale.ROOT, "%.2f", getMonthSalary()) + " руб.";
    }
}
