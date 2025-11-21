import java.util.*;

public class TrainTicketBooking {

    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        String[] stations = {"Mumbai", "Thane", "Dombivli", "Kalyan", "Karjat"};
        int[] prices = {10, 15, 20, 25, 30};

        System.out.println("===== Local Train Ticket Booking System =====");

        // Display stations
        System.out.println("Available Stations:");
        for (int i = 0; i < stations.length; i++) {
            System.out.println((i + 1) + ". " + stations[i] + " - Rs." + prices[i]);
        }

        // Select source
        System.out.print("\nEnter your source station number: ");
        int src = sc.nextInt() - 1;

        // Select destination
        System.out.print("Enter your destination station number: ");
        int dest = sc.nextInt() - 1;

        if (src < 0 || dest < 0 || src >= stations.length || dest >= stations.length) {
            System.out.println("Invalid station selection!");
            return;
        }

        if (src == dest) {
            System.out.println("Source and destination cannot be the same!");
            return;
        }

        int totalFare = Math.abs(prices[dest] - prices[src]);

        System.out.println("\n----- Ticket Details -----");
        System.out.println("From: " + stations[src]);
        System.out.println("To: " + stations[dest]);
        System.out.println("Total Fare: Rs." + totalFare);
        System.out.println("Ticket Booked Successfully!");
    }
}
