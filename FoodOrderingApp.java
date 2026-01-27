import java.util.*;

public class FoodOrderingApp {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int choice, quantity;
        double totalBill = 0;
        // Menu with prices
        String[] items = {"Pizza", "Burger", "Pasta", "Fries", "Coffee"};
        double[] prices = {199, 99, 149, 79, 59};
        System.out.println("====== WELCOME TO FOOD ORDERING APP ======");
        while (true) {
            System.out.println("\n----- MENU -----");
            for (int i = 0; i < items.length; i++) {
                System.out.println((i + 1) + ". " + items[i] + " - ₹" + prices[i]);
            }
            System.out.println("6. Exit & Generate Bill");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            if (choice == 6) {
                break;
            } else if (choice >= 1 && choice <= 5) {
                System.out.print("Enter quantity: ");
                quantity = sc.nextInt();

                double itemTotal = prices[choice - 1] * quantity;
                totalBill += itemTotal;

                System.out.println("Added to cart: " + items[choice - 1] + " x" + quantity);
                System.out.println("Subtotal: ₹" + itemTotal);
            } else {
                System.out.println("Invalid choice! Please try again.");
            }
        }
        System.out.println("\n====== ORDER SUMMARY ======");
        System.out.println("Total Bill: ₹" + totalBill);
        System.out.println("Thank you for ordering! Have a great day!");
        sc.close();
    }
}






