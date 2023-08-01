import java.util.*;

public class Company {
    private final List<Employee> employees;

    public Company(){
        employees = new ArrayList<>();
    }

    public Company(int operators, int operatorsSalaryRate,
                   int managers, int managersSalaryRate,
                   int topManagers, int topManagersSalaryRate) {
        this.employees = new ArrayList<>();
        for (int i = 0; i < operators; i++) {
            hire(new Operator(operatorsSalaryRate));
        }
        for (int i = 0; i < managers; i++) {
            hire(new Manager(managersSalaryRate));
        }
        for (int i = 0; i < topManagers; i++) {
            hire(new TopManager(topManagersSalaryRate, this));
        }
    }

    public void hire(Employee employee) {
        employees.add(employee);
    }

    public void hireAll(Collection<Employee> employees) {
        this.employees.addAll(employees);
    }

    public void fire(Employee employee) {
        employees.remove(employee);
    }

    public List<Employee> getTopSalaryStaff(int count) {
        return getList(count, Comparator.reverseOrder());
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        return getList(count, Comparator.naturalOrder());
    }

    private List<Employee> getList(int count, Comparator<Employee> comparator) {
        if (count < 0) {
            System.out.println("Передано неверное значение");
            return Collections.emptyList();
        }
        if (count > employees.size()) {
            count = employees.size();
        }
        employees.sort(comparator);
        return new ArrayList<>(employees.subList(0, count));
    }

    public double getIncome() {
        double income = 0;
        for (Employee e : employees) {
            if (e instanceof Manager) {
                income += (((Manager) e).getSales());
            }
        }
        return income;
    }

    public List<Employee> getEmployeeList() {
        return new ArrayList<>(employees);
    }
}
