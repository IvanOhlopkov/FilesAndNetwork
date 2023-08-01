import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        Company company = new Company(180, 50_000,
                80, 70_000,
                10, 100_000);

        printEmployees(company);

        System.out.println("Увольнение 50% сотрудников");
        List<Employee> list = company.getEmployeeList();
        int size = list.size();
        for (int i = 0; i < size / 2; i++) {
            company.fire(list.get(i));
        }

        printEmployees(company);

        List<Employee> employees2 = new ArrayList<>();
        Company company2 = new Company();
        for (int i = 0; i < 200; i++) {
            employees2.add(new Operator(30_000));
            employees2.add(new Manager(40_000));
            employees2.add(new TopManager(50_000, company2));
        }
        company2.hireAll(employees2);
        System.out.println("------Вторая компания--------");
        printEmployees(company2);
    }

    public static void printEmployees(Company company) {
        System.out.println("Список самых высоких зарплат: ");
        for (Employee employee : company.getTopSalaryStaff(15)) {
            System.out.println(employee);
        }

        System.out.println("Список самых низких зарплат:");
        for (Employee employee : company.getLowestSalaryStaff(30)) {
            System.out.println(employee);
        }
    }
}
