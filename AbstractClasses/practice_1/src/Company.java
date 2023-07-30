import java.util.*;

public class Company {
    private final ArrayList<Employee> employees;

    public Company(){
        employees = new ArrayList<>();
    }
    public Company(double income, int operators, int managers, int topManagers){
        this.employees = new ArrayList<>();
        for (int i = 0; i < operators; i++) {
            hire(new Operator(100_000));
        }
        for (int i = 0; i < managers; i++) {
            hire(new Manager(110_000));
        }
        for (int i = 0; i < topManagers; i++) {
            hire(new TopManager(150_000, income));
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
        if (count < 0) {
            count = Math.abs(count);
        }
        if (count > employees.size()) {
            count = employees.size();
        }
        employees.sort((o1, o2) -> -Double.compare(o1.getMonthSalary(), o2.getMonthSalary()));
      return employees.subList(0, count);
    }

    public List<Employee> getLowestSalaryStaff(int count) {
        employees.sort(Comparator.comparingDouble(Employee::getMonthSalary));
        return employees.subList(0, count);
    }

    public ArrayList<Employee> getEmployeeList(){
        return employees;
    }
}
