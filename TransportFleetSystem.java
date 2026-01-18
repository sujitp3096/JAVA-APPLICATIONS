import java.io.*;
import java.util.*;

class Vehicle implements Serializable {
    String id, driver;
    String location = "Not Updated";
    double fuelLevel = 0;
    ArrayList<String> tripHistory = new ArrayList<>();
    int maintenanceCounter = 0;

    Vehicle(String id, String driver) {
        this.id = id;
        this.driver = driver;
    }

    void display() {
        System.out.println("\n--- Vehicle Details ---");
        System.out.println("ID: " + id);
        System.out.println("Driver: " + driver);
        System.out.println("Current Location: " + location);
        System.out.println("Fuel Level: " + fuelLevel + " L");
        System.out.println("Trips: " + tripHistory);
        System.out.println("Maintenance Counter: " + maintenanceCounter + " km");
    }
}
public class TransportFleetSystem {

    static final String FILE = "fleet.db";
    static ArrayList<Vehicle> fleet = new ArrayList<>();

    public static void main(String[] args) {
        loadData();
        Scanner sc = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== Transport Fleet Tracking System =====");
            System.out.println("1. Register New Vehicle");
            System.out.println("2. Update Location");
            System.out.println("3. Log Fuel");
            System.out.println("4. Add Trip Record");
            System.out.println("5. Vehicle Status");
            System.out.println("6. Maintenance Alert Check");
            System.out.println("7. Exit");
            System.out.print("Enter choice: ");

            int ch = sc.nextInt();
            sc.nextLine();

            switch (ch) {
                case 1 -> addVehicle(sc);
                case 2 -> updateLocation(sc);
                case 3 -> addFuel(sc);
                case 4 -> addTrip(sc);
                case 5 -> viewVehicle(sc);
                case 6 -> checkMaintenance();
                case 7 -> { saveData(); return; }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addVehicle(Scanner sc) {
        System.out.print("Enter Vehicle ID: ");
        String id = sc.nextLine();
        System.out.print("Enter Driver Name: ");
        String driver = sc.nextLine();

        fleet.add(new Vehicle(id, driver));
        saveData();
        System.out.println("Vehicle registered!");
    }

    static void updateLocation(Scanner sc) {
        System.out.print("Vehicle ID: ");
        Vehicle v = find(sc.nextLine());
        if (v == null) { System.out.println("Not found!"); return; }

        System.out.print("Enter new location: ");
        v.location = sc.nextLine();

        v.maintenanceCounter += 20; // add km (simulation)
        saveData();
        System.out.println("Location updated!");
    }

    static void addFuel(Scanner sc) {
        System.out.print("Vehicle ID: ");
        Vehicle v = find(sc.nextLine());
        if (v == null) { System.out.println("Not found!"); return; }

        System.out.print("Enter fuel added (L): ");
        v.fuelLevel += sc.nextDouble();

        saveData();
        System.out.println("Fuel logged!");
    }

    static void addTrip(Scanner sc) {
        System.out.print("Vehicle ID: ");
        Vehicle v = find(sc.nextLine());
        if (v == null) { System.out.println("Not found!"); return; }

        System.out.print("Enter trip description: ");
        v.tripHistory.add(sc.nextLine());

        saveData();
        System.out.println("Trip added!");
    }

    static void viewVehicle(Scanner sc) {
        System.out.print("Enter Vehicle ID: ");
        Vehicle v = find(sc.nextLine());
        if (v == null) { System.out.println("Vehicle not found!"); return; }

        v.display();
    }

    static void checkMaintenance() {
        System.out.println("\n--- Maintenance Alerts ---");
        for (Vehicle v : fleet) {
            if (v.maintenanceCounter >= 100) {
                System.out.println("⚠ Vehicle " + v.id + " needs maintenance!");
            }
        }
    }

    static Vehicle find(String id) {
        for (Vehicle v : fleet)
            if (v.id.equals(id)) return v;
        return null;
    }

    static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE))) {
            oos.writeObject(fleet);
        } catch (Exception ignored) {}
    }

    static void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE))) {
            fleet = (ArrayList<Vehicle>) ois.readObject();
        } catch (Exception ignored) {}
    }
}

