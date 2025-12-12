import java.io.*;
import java.time.LocalDate;
import java.util.*;

class Medicine implements Serializable {
    String id;
    String name;
    int quantity;
    double price;
    LocalDate expiry;

    public Medicine(String id, String name, int quantity, double price, LocalDate expiry) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.expiry = expiry;
    }

    public boolean isExpired() {
        return expiry.isBefore(LocalDate.now());
    }

    public String toString() {
        return String.format("%s | %s | Qty: %d | ₹%.2f | Expiry: %s%s",
                id, name, quantity, price, expiry,
                isExpired() ? " ❗(Expired)" : "");
    }
}

class PharmacyDatabase {
    private final String FILE = "medicines.dat";

    public List<Medicine> load() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            return (List<Medicine>) ois.readObject();
        } catch (Exception e) {
            return new ArrayList<>();
        }
    }

    public void save(List<Medicine> list) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(list);
        } catch (Exception ignored) {}
    }
}

public class PharmacySystem {
    static Scanner sc = new Scanner(System.in);
    static PharmacyDatabase db = new PharmacyDatabase();
    static List<Medicine> medicines;

    public static void main(String[] args) {
        medicines = db.load();

        while (true) {
            System.out.println("\n===== PHARMACY MANAGEMENT SYSTEM =====");
            System.out.println("1. Add Medicine");
            System.out.println("2. View All Medicines");
            System.out.println("3. Search Medicine");
            System.out.println("4. Purchase / Billing");
            System.out.println("5. Show Expired Medicines");
            System.out.println("6. Exit");
            System.out.print("Choose: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> addMedicine();
                case 2 -> viewAll();
                case 3 -> searchMedicine();
                case 4 -> billing();
                case 5 -> showExpired();
                case 6 -> {
                    db.save(medicines);
                    System.out.println("Data saved. Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addMedicine() {
        sc.nextLine();
        System.out.print("Medicine ID: ");
        String id = sc.nextLine();

        System.out.print("Name: ");
        String name = sc.nextLine();

        System.out.print("Quantity: ");
        int qty = sc.nextInt();

        System.out.print("Price: ");
        double price = sc.nextDouble();

        System.out.print("Expiry (YYYY-MM-DD): ");
        LocalDate exp = LocalDate.parse(sc.next());

        medicines.add(new Medicine(id, name, qty, price, exp));
        System.out.println("✔ Medicine added successfully!");
    }

    static void viewAll() {
        if (medicines.isEmpty()) {
            System.out.println("No medicines available.");
            return;
        }
        medicines.forEach(System.out::println);
    }

    static void searchMedicine() {
        sc.nextLine();
        System.out.print("Enter medicine name: ");
        String name = sc.nextLine().toLowerCase();

        for (Medicine m : medicines) {
            if (m.name.toLowerCase().contains(name)) {
                System.out.println(m);
                return;
            }
        }
        System.out.println("❌ Medicine not found!");
    }

    static void billing() {
        sc.nextLine();
        System.out.print("Enter medicine ID: ");
        String id = sc.nextLine();

        Medicine med = null;
        for (Medicine m : medicines) {
            if (m.id.equals(id)) {
                med = m;
                break;
            }
        }

        if (med == null) {
            System.out.println("❌ Medicine not found!");
            return;
        }

        if (med.isExpired()) {
            System.out.println("⚠ Cannot sell expired medicine!");
            return;
        }

        System.out.print("Enter quantity: ");
        int qty = sc.nextInt();

        if (qty > med.quantity) {
            System.out.println("❌ Insufficient stock!");
            return;
        }

        double total = qty * med.price;
        med.quantity -= qty;

        System.out.println("\n----- BILL -----");
        System.out.println("Medicine: " + med.name);
        System.out.println("Quantity: " + qty);
        System.out.println("Price per unit: ₹" + med.price);
        System.out.println("Total: ₹" + total);
        System.out.println("-----------------");

        db.save(medicines);
        System.out.println("✔ Purchase successful! Stock updated.");
    }

    static void showExpired() {
        System.out.println("\nExpired Medicines:");
        boolean found = false;

        for (Medicine m : medicines) {
            if (m.isExpired()) {
                System.out.println(m);
                found = true;
            }
        }

        if (!found)
            System.out.println("No expired medicines.");
    }
}
