public class Operator implements Employee{
    double OperatorRateSalary;
    private final double monthSalary;

    public Operator(double rateSalary) {
        this.OperatorRateSalary = rateSalary;
        this.monthSalary = calcMonthSalary();
    }

    @Override
    public double getMonthSalary() {
        return monthSalary;
    }

    @Override
    public double calcMonthSalary() {
        return OperatorRateSalary;
    }

    @Override
    public String toString() {
        return getMonthSalary() + " руб.";
    }
}
