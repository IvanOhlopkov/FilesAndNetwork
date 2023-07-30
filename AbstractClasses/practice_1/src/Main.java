import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        Company company = new Company(11_000_000, 180, 80, 10);

        System.out.println("Список самых высоких зарплат: ");
        for (Employee employee : company.getTopSalaryStaff(15)) {
            System.out.println(employee);
        }

        System.out.println("Список самых низких зарплат:");
        for (Employee employee : company.getLowestSalaryStaff(30)) {
            System.out.println(employee);
        }

        int managersFired = 0;
        int operatorsFired = 0;
        int topManagersFired = 0;
        ArrayList<Employee> list = company.getEmployeeList();
        for (int i = 0; i < list.size(); i++) {
            if (company.getEmployeeList().get(i) instanceof Manager && managersFired < (90)) {
                company.fire(company.getEmployeeList().get(i));
                managersFired++;
            }
            if (company.getEmployeeList().get(i) instanceof Operator && operatorsFired < (40)) {
                company.fire(company.getEmployeeList().get(i));
                operatorsFired++;
            }
            if (company.getEmployeeList().get(i) instanceof TopManager && topManagersFired < (5)) {
                company.fire(company.getEmployeeList().get(i));
                topManagersFired++;
            }
        }

        System.out.println("Список высоких зарплат после увольнения 50%:");
        for (
                Employee employee : company.getTopSalaryStaff(5)) {
            System.out.println(employee);
        }

        System.out.println("Список самых низких зарплат после увольнения 50%:");
        for (
                Employee employee : company.getLowestSalaryStaff(5)) {
            System.out.println(employee);
        }

        Company company2 = new Company();
        ArrayList<Employee> list2 = new ArrayList<>();
        list2.add(new Operator(80_000));
        list2.add(new Manager(135_000));
        list2.add(new TopManager(170_000, 9_000_000));
        company2.hireAll(list2);
    }
}
