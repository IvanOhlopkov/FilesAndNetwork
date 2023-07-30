public class Manager implements Employee {
    double managerSalaryRate;
    final int MIN_EARNED_MONEY = 115_000;
    final int MAX_EARNED_MONEY = 140_000;
    final double BONUS_PERCENT = 0.05;
    private final double monthSalary;

    public Manager(double managerSalaryRate) {
        this.managerSalaryRate = managerSalaryRate;
        this.monthSalary = calcMonthSalary();
    }

    @Override
    public double getMonthSalary() {
        return monthSalary;
    }

    @Override
    public double calcMonthSalary() {
        return managerSalaryRate +
                (MIN_EARNED_MONEY + (int) (Math.random() * MAX_EARNED_MONEY + 1))
                        * BONUS_PERCENT;

    }

    @Override
    public String toString() {
        return getMonthSalary() + " руб.";
    }
}
