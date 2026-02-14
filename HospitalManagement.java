

import java.util.*;

class Patient {
    int id;
    String name;
    String disease;
    Patient(int id, String name, String disease) {
        this.id = id;
        this.name = name;
        this.disease = disease;
    }

    void display() {
        System.out.println(id + " - " + name + " (" + disease + ")")
    }
}

public class HospitalManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Patient> patients = new ArrayList<>();

        while (true) {
            System.out.println("\n1. Add Patient  2. View Patients  3. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> {
                    System.out.print("Enter ID: ");
                    int id = sc.nextInt();
                    System.out.print("Enter Name: ");
                    String name = sc.next();
                    System.out.print("Enter Disease: ");
                    String dis = sc.next();
                    patients.add(new Patient(id, name, dis));
                }
                case 2 -> {
                    System.out.println("\n--- Patient List ---");
                    for (Patient p : patients) p.display();
                }
                case 3 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}



