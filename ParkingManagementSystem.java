import java.util.*;

class ParkingSpot {
    int spotNumber;
    boolean isOccupied;
    String vehicleNumber;

    ParkingSpot(int spotNumber) {
        this.spotNumber = spotNumber;
        this.isOccupied = false;
        this.vehicleNumber = "";
    }
}
public class ParkingManagementSystem {
    static Scanner sc = new Scanner(System.in);
    static ArrayList<ParkingSpot> parking = new ArrayList<>();

    public static void main(String[] args) {
        System.out.print("Enter number of parking spots: ");
        int n = sc.nextInt();
        for (int i = 1; i <= n; i++) {
            parking.add(new ParkingSpot(i));
        }

        while (true) {
            System.out.println("\n===== PARKING MANAGEMENT MENU =====");
            System.out.println("1. Park Vehicle");
            System.out.println("2. Remove Vehicle");
            System.out.println("3. View Parking Status");
            System.out.println("4. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    parkVehicle();
                    break;
                case 2:
                    removeVehicle();
                    break;
                case 3:
                    viewStatus();
                    break;
                case 4:
                    System.out.println("Exiting Parking System...");
                    System.exit(0);
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    static void parkVehicle() {
        System.out.print("Enter vehicle number: ");
        String vnum = sc.next();

        for (ParkingSpot spot : parking) {
            if (!spot.isOccupied) {
                spot.isOccupied = true;
                spot.vehicleNumber = vnum;
                System.out.println("Vehicle parked at spot: " + spot.spotNumber);
                return;
            }
        }
        System.out.println("No empty parking spots available!");
    }

    static void removeVehicle() {
        System.out.print("Enter spot number to free: ");
        int spotNum = sc.nextInt();

        if (spotNum <= 0 || spotNum > parking.size()) {
            System.out.println("Invalid spot number!");
            return;
        }

        ParkingSpot spot = parking.get(spotNum - 1);

        if (spot.isOccupied) {
            System.out.println("Vehicle " + spot.vehicleNumber + " removed.");
            spot.isOccupied = false;
            spot.vehicleNumber = "";
        } else {
            System.out.println("Spot already empty.");
        }
    }

    static void viewStatus() {
        System.out.println("\n------ PARKING STATUS ------");
        for (ParkingSpot spot : parking) {
            if (spot.isOccupied)
                System.out.println("Spot " + spot.spotNumber + " : OCCUPIED by " + spot.vehicleNumber);
            else
                System.out.println("Spot " + spot.spotNumber + " : EMPTY");
        }
    }
}


