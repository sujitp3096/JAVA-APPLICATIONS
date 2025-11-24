import java.util.*;

public class MovieTicketBooking {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String[] movies = {"1. Avatar 2", "2. KGF Chapter 2", "3. Spider-Man No Way Home"};
        int[] seats = {50, 40, 30};   // Available seats for each movie
        int pricePerTicket = 200;

        System.out.println("===== MOVIE TICKET BOOKING SYSTEM =====");
        System.out.println("Available Movies:");

        for (String m : movies) {
            System.out.println(m);
        }

        System.out.print("\nEnter Movie Number to Book: ");
        int choice = sc.nextInt();

        if (choice < 1 || choice > movies.length) {
            System.out.println("Invalid Movie Selection!");
            return;
        }

        int index = choice - 1;
        System.out.println("You selected: " + movies[index]);
        System.out.println("Available Seats: " + seats[index]);

        System.out.print("Enter number of tickets: ");
        int tickets = sc.nextInt();

        if (tickets <= 0) {
            System.out.println("Invalid number of tickets!");
            return;
        }

        if (tickets > seats[index]) {
            System.out.println("Not enough seats available!");
            return;
        }

        int totalAmount = tickets * pricePerTicket;

        System.out.println("\n===== BOOKING CONFIRMATION =====");
        System.out.println("Movie: " + movies[index]);
        System.out.println("Tickets: " + tickets);
        System.out.println("Price per Ticket: " + pricePerTicket);
        System.out.println("Total Amount: " + totalAmount);
        System.out.println("Booking Successful! Enjoy your movie 😊");

        sc.close();
    }
}
