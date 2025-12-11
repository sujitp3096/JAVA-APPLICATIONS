import java.io.*;
import java.util.*;

// ------------------  MODEL CLASS  ------------------
class Customer implements Serializable {
    String customerId, name, address;
    int electricityUnits;
    int waterUnits;

    Customer(String id, String name, String address) {
        this.customerId = id;
        this.name = name;
        this.address = address;
    }

    int calculateElectricityBill() {
        int u = electricityUnits;
        int bill = 0;

        if (u <= 100) bill = u * 5;
        else if (u <= 300) bill = (100 * 5) + (u - 100) * 7;
        else bill = (100 * 5) + (200 * 7) + (u - 300) * 10;

        return bill;
    }

    int calculateWaterBill() {
        int w = waterUnits;
        int bill = 0;

        if (w <= 50) bill = w * 2;
        else if (w <= 150) bill = (50 * 2) + (w - 50) * 3;
        else bill = (50 * 2) + (100 * 3) + (w - 150) * 5;

        return bill;
    }

    int totalBill() {
        return calculateElectricityBill() + calculateWaterBill();
    }

    void printBill() {
        System.out.println("\n----------------------------------------");
        System.out.println("              BILL SUMMARY              ");
        System.out.println("----------------------------------------");
        System.out.println("Customer ID : " + customerId);
        System.out.println("Name        : " + name);
        System.out.println("Address     : " + address);
        System.out.println("----------------------------------------");
        System.out.println("Electricity Units : " + electricityUnits);
        System.out.println("Electricity Bill  : ₹" + calculateElectricityBill());
        System.out.println("----------------------------------------");
        System.out.println("Water Usage (KL)  : " + waterUnits);
        System.out.println("Water Bill        : ₹" + calculateWaterBill());
        System.out.println("----------------------------------------");
        System.out.println("TOTAL PAYABLE     : ₹" + totalBill());
        System.out.println("----------------------------------------\n");
    }
}

// ------------------  MAIN SYSTEM CLASS  ------------------
public class water&elecrticity_BillingSystem {

    static final String FILE = "customers.db";
    static ArrayList<Customer> customers = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        loadData();

        while (true) {
            System.out.println("\n====== Water & Electricity Billing System ======");
            System.out.println("1. Add New Customer");
            System.out.println("2. Enter Monthly Usage");
            System.out.println("3. Generate Bill");
            System.out.println("4. View All Customers");
            System.out.println("5. Search Customer");
            System.out.println("6. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addCustomer();
                case 2 -> updateUsage();
                case 3 -> generateBill();
                case 4 -> listCustomers();
                case 5 -> searchCustomer();
                case 6 -> { saveData(); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addCustomer() {
        System.out.print("Customer ID: ");
        String id = sc.nextLine();
        System.out.print("Name       : ");
        String name = sc.nextLine();
        System.out.print("Address    : ");
        String address = sc.nextLine();

        customers.add(new Customer(id, name, address));
        saveData();

        System.out.println("Customer added successfully!");
    }

    static void updateUsage() {
        System.out.print("Enter Customer ID: ");
        Customer c = find(sc.nextLine());

        if (c == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.print("Enter Electricity Units: ");
        c.electricityUnits = sc.nextInt();

        System.out.print("Enter Water Usage (KL): ");
        c.waterUnits = sc.nextInt();

        saveData();
        System.out.println("Usage updated!");
    }

    static void generateBill() {
        System.out.print("Enter Customer ID: ");
        Customer c = find(sc.nextLine());

        if (c == null) {
            System.out.println("Customer not found!");
            return;
        }

        c.printBill();
    }

    static void listCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found!");
            return;
        }

        System.out.println("\n--- All Registered Customers ---");
        for (Customer c : customers) {
            System.out.println(c.customerId + " | " + c.name + " | " + c.address);
        }
    }

    static void searchCustomer() {
        System.out.print("Enter ID to search: ");
        Customer c = find(sc.nextLine());

        if (c == null) {
            System.out.println("Customer not found!");
            return;
        }

        System.out.println("\nFound:");
        System.out.println(c.customerId + " | " + c.name + " | " + c.address);
    }

    static Customer find(String id) {
        for (Customer c : customers)
            if (c.customerId.equals(id)) return c;
        return null;
    }

    static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(customers);
        } catch (Exception ignored) {}
    }

    static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            customers = (ArrayList<Customer>) ois.readObject();
        } catch (Exception ignored) {}
    }
}
