import java.io.*;
import java.util.*;

class TollRecord implements Serializable {
    String vehicleNo, type, dateTime;
    int tollAmount;

    TollRecord(String vehicleNo, String type, int tollAmount, String dateTime) {
        this.vehicleNo = vehicleNo;
        this.type = type;
        this.tollAmount = tollAmount;
        this.dateTime = dateTime;
    }

    void display() {
        System.out.println("\n--- Toll Receipt ---");
        System.out.println("Vehicle No: " + vehicleNo);
        System.out.println("Type: " + type);
        System.out.println("Toll Amount: ₹" + tollAmount);
        System.out.println("Time: " + dateTime);
    }
}

public class TollBoothSystem {

    static final String FILE = "toll_records.db";
    static ArrayList<TollRecord> records = new ArrayList<>();

    public static void main(String[] args) {
        loadData();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Toll Booth System =====");
            System.out.println("1. Pay Toll");
            System.out.println("2. View All Toll Records");
            System.out.println("3. Daily Collection Report");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> payToll(sc);
                case 2 -> viewRecords();
                case 3 -> dailyReport();
                case 4 -> { saveData(); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void payToll(Scanner sc) {
        System.out.print("Enter Vehicle No: ");
        String vno = sc.nextLine();

        System.out.println("\nSelect Vehicle Type:");
        System.out.println("1. Car - ₹50");
        System.out.println("2. Truck - ₹120");
        System.out.println("3. Bus - ₹90");
        System.out.println("4. Motorcycle - ₹20");
        System.out.print("Enter choice: ");

        int typeChoice = sc.nextInt();
        sc.nextLine();

        String type = "";
        int amount = 0;

        switch (typeChoice) {
            case 1 -> { type = "Car"; amount = 50; }
            case 2 -> { type = "Truck"; amount = 120; }
            case 3 -> { type = "Bus"; amount = 90; }
            case 4 -> { type = "Motorcycle"; amount = 20; }
            default -> {
                System.out.println("Invalid type!");
                return;
            }
        }

        String time = new Date().toString();
        TollRecord tr = new TollRecord(vno, type, amount, time);
        records.add(tr);
        saveData();

        System.out.println("\nPayment Successful!");
        tr.display();
    }

    static void viewRecords() {
        if (records.isEmpty()) {
            System.out.println("No records found!");
            return;
        }

        System.out.println("\n--- All Toll Records ---");
        for (TollRecord r : records) {
            System.out.println(r.vehicleNo + " | " + r.type + " | ₹" + r.tollAmount + " | " + r.dateTime);
        }
    }

    static void dailyReport() {
        int total = 0;
        int count = 0;

        for (TollRecord r : records) {
            total += r.tollAmount;
            count++;
        }

        System.out.println("\n--- Daily Collection Report ---");
        System.out.println("Total Vehicles: " + count);
        System.out.println("Total Collection: ₹" + total);
    }

    static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(records);
        } catch (Exception ignored) {}
    }

    static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            records = (ArrayList<TollRecord>) ois.readObject();
        } catch (Exception ignored) {}
    }
}
