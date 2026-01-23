import java.io.*;
import java.util.*;

class Student implements Serializable {
    String id, name, branch;
    int attendance = 0;
    int totalLectures = 0;
    HashMap<String, Integer> marks = new HashMap<>();

    Student(String id, String name, String branch) {
        this.id = id;
        this.name = name;
        this.branch = branch;
    }
    public void display() {
        System.out.println("ID: " + id);
        System.out.println("Name: " + name);
        System.out.println("Branch: " + branch);
        System.out.println("Attendance: " + attendance + "/" + totalLectures);
        System.out.println("Marks: " + marks);
    }
}

public class CollegeERP {

    static final String FILE = "students.db";
    static final String NOTICE_FILE = "notices.txt";

    static ArrayList<Student> students = new ArrayList<>();

    public static void main(String[] args) {
        loadData();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== College ERP Mini Version =====");
            System.out.println("1. Add Student");
            System.out.println("2. Mark Attendance");
            System.out.println("3. Add Marks");
            System.out.println("4. View Student Profile");
            System.out.println("5. Post Notice");
            System.out.println("6. View Notices");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addStudent(sc);
                case 2 -> markAttendance(sc);
                case 3 -> addMarks(sc);
                case 4 -> viewStudent(sc);
                case 5 -> postNotice(sc);
                case 6 -> viewNotices();
                case 7 -> { saveData(); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addStudent(Scanner sc) {
        System.out.print("Enter ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Name: ");
        String name = sc.nextLine();
        System.out.print("Enter Branch: ");
        String branch = sc.nextLine();

        students.add(new Student(id, name, branch));
        saveData();
        System.out.println("Student added successfully!");
    }

    static void markAttendance(Scanner sc) {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();

        Student s = findStudent(id);
        if (s == null) {
            System.out.println("Student not found!");
            return;
        }

        s.totalLectures++;
        System.out.print("Present? (y/n): ");
        if (sc.nextLine().equalsIgnoreCase("y"))
            s.attendance++;

        saveData();
        System.out.println("Attendance updated!");
    }

    static void addMarks(Scanner sc) {
        System.out.print("Enter Student ID: ");
        String id = sc.nextLine();
        Student s = findStudent(id);

        if (s == null) {
            System.out.println("Student not found!");
            return;
        }

        System.out.print("Enter Subject: ");
        String sub = sc.nextLine();
        System.out.print("Enter Marks: ");
        int m = sc.nextInt();

        s.marks.put(sub, m);
        saveData();
        System.out.println("Marks saved!");
    }

    static void viewStudent(Scanner sc) {
        System.out.print("Enter ID: ");
        Student s = findStudent(sc.nextLine());

        if (s == null) {
            System.out.println("Student not found!");
            return;
        }
        s.display();
    }

    static void postNotice(Scanner sc) {
        System.out.print("Enter Notice: ");
        String notice = sc.nextLine();

        try (FileWriter fw = new FileWriter(NOTICE_FILE, true)) {
            fw.write(notice + "\n");
        } catch (Exception e) {
            System.out.println("Error saving notice!");
        }

        System.out.println("Notice posted!");
    }

    static void viewNotices() {
        System.out.println("\n--- Notice Board ---");
        try (BufferedReader br = new BufferedReader(new FileReader(NOTICE_FILE))) {
            String line;
            while ((line = br.readLine()) != null)
                System.out.println("- " + line);
        } catch (Exception e) {
            System.out.println("No notices found.");
        }
    }

    static Student findStudent(String id) {
        for (Student s : students)
            if (s.id.equals(id)) return s;
        return null;
    }

    static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(students);
        } catch (Exception ignored) {}
    }

    static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            students = (ArrayList<Student>) ois.readObject();
        } catch (Exception ignored) {}
    }
}



