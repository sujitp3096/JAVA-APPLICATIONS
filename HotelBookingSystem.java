import java.util.*;

class Room {
    int roomNumber;
    boolean booked;

    Room(int roomNumber) {
        this.roomNumber = roomNumber;
        this.booked = false;
    }
}

public class HotelBookingSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Room> rooms = new ArrayList<>();

        for (int i = 1; i <= 5; i++) rooms.add(new Room(i));

        while (true) {
            System.out.println("\n1. View Rooms  2. Book Room  3. Checkout  4. Exit");
            System.out.print("Enter choice: ");
            int ch = sc.nextInt();

            switch (ch) {
                case 1 -> {
                    System.out.println("\n--- Room Status ---");
                    for (Room r : rooms)
                        System.out.println("Room " + r.roomNumber + " - " + (r.booked ? "Booked" : "Available"));
                }
                case 2 -> {
                    System.out.print("Enter room number to book: ");
                    int rn = sc.nextInt();
                    for (Room r : rooms)
                        if (r.roomNumber == rn && !r.booked) {
                            r.booked = true;
                            System.out.println("Room " + rn + " booked successfully!");
                        }
                }
                case 3 -> {
                    System.out.print("Enter room number to checkout: ");
                    int rn = sc.nextInt();
                    for (Room r : rooms)
                        if (r.roomNumber == rn && r.booked) {
                            r.booked = false;
                            System.out.println("Room " + rn + " is now available!");
                        }
                }
                case 4 -> System.exit(0);
                default -> System.out.println("Invalid choice!");
            }
        }
    }
}
