
import java.util.*;

class Employee {
    int id;
    String name;
    double salary;

    Employee(int id, String name, double salary) {
        this.id = id;
        this.name = name;
        this.salary = salary;
    }

    void display() {
        
        System.out.println("ID: " + id + " | Name: " + name + " | Salary: ₹" + salary);
    }
}

public class EmployeePayroll {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Employee> empList = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Add Employee  2. View Payroll  3. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Salary: ");
                    double sal = sc.nextDouble();
                    empList.add(new Employee(id, name, sal));
                }
                case 2 -> {
                    System.out.println("\n--- Payroll List ---");
                    for (Employee e : empList) e.display();
                }
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}


