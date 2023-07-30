public class TopManager implements Employee{
    double topManagerSalaryRate;
    final double MIN_INCOME_FOR_BONUS = 10_000_000;
    double income;

    public TopManager(double topManagerSalaryRate, double income) {
        this.topManagerSalaryRate = topManagerSalaryRate;
        this.income = income;
    }

    @Override
    public double getMonthSalary() {
        if (income > MIN_INCOME_FOR_BONUS) {
            return topManagerSalaryRate + (topManagerSalaryRate * 0.5 + topManagerSalaryRate);
        }
        return topManagerSalaryRate;
    }

    @Override
    public double calcMonthSalary() {
        return 0;
    }

    @Override
    public String toString() {
        return getMonthSalary() + " руб.";
    }
}
