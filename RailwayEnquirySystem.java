import java.util.*;

class Train {
    int trainNumber;
    String trainName;
    String source;
    String destination;
    String time;
    Train(int trainNumber, String trainName, String source, String destination, String time) {
        this.trainNumber = trainNumber;
        this.trainName = trainName;
        this.source = source;
        this.destination = destination;
        this.time = time;
    }
}

public class RailwayEnquirySystem {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Train Database
        Train[] trains = {
            new Train(11001, "Deccan Express", "Mumbai", "Pune", "07:00 AM"),
            new Train(11002, "Intercity Express", "Pune", "Mumbai", "06:45 AM"),
            new Train(17317, "Hubli Express", "Mumbai", "Hubli", "08:30 PM"),
            new Train(22160, "Mumbai Express", "Chennai", "Mumbai", "03:00 PM"),
            new Train(12124, "Deccan Queen", "Pune", "Mumbai", "05:10 PM")
        };

        while (true) {
            System.out.println("\n===== Railway Enquiry System =====");
            System.out.println("1. Search Train by Number");
            System.out.println("2. Search Train by Source & Destination");
            System.out.println("3. Display All Trains");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // clear buffer

            switch (choice) {

                case 1:
                    System.out.print("Enter Train Number: ");
                    int number = sc.nextInt();
                    boolean found = false;

                    for (Train t : trains) {
                        if (t.trainNumber == number) {
                            System.out.println("\nTrain Found:");
                            System.out.println("Train Number: " + t.trainNumber);
                            System.out.println("Train Name  : " + t.trainName);
                            System.out.println("From        : " + t.source);
                            System.out.println("To          : " + t.destination);
                            System.out.println("Departure   : " + t.time);
                            found = true;
                            break;
                        }
                    }

                    if (!found) {
                        System.out.println("No train found with this number.");
                    }
                    break;

                case 2:
                    System.out.print("Enter Source: ");
                    String src = sc.nextLine().toLowerCase();

                    System.out.print("Enter Destination: ");
                    String dest = sc.nextLine().toLowerCase();

                    boolean matched = false;
                    for (Train t : trains) {
                        if (t.source.toLowerCase().equals(src) && t.destination.toLowerCase().equals(dest)) {
                            System.out.println("\nTrain Found:");
                            System.out.println("Train Number: " + t.trainNumber);
                            System.out.println("Train Name  : " + t.trainName);
                            System.out.println("Departure   : " + t.time);
                            matched = true;
                        }
                    }

                    if (!matched) {
                        System.out.println("No trains available for this route.");
                    }
                    break;
                case 3:
                    System.out.println("\nAll Available Trains:");
                    for (Train t : trains) {
                        System.out.println(t.trainNumber + " - " + t.trainName + " (" + t.source + " → " + t.destination + ") " + t.time);
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using Railway Enquiry System!");
                    return;

                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }
}


