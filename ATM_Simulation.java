import java.util.Scanner;

public class ATM_Simulation {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        int pin = 1234;          // Default PIN
        int userPin;
        double balance = 5000;   // Default balance
        int choice;
        System.out.println("===== ATM MACHINE =====");

        System.out.print("Enter your PIN: ");
        userPin = sc.nextInt();

        if (userPin != pin) {
            System.out.println("Incorrect PIN! Try again.");
            return;
        }

        do {
            System.out.println("\n===== ATM MENU =====");
            System.out.println("1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1:
                    System.out.println("Your Current Balance: ₹" + balance);
                    break;

                case 2:
                    System.out.print("Enter amount to deposit: ");
                    double deposit = sc.nextDouble();
                    if (deposit > 0) {
                        balance += deposit;
                        System.out.println("Amount Deposited Successfully!");
                    } else {
                        System.out.println("Invalid Amount!");
                    }
                    break;

                case 3:
                    System.out.print("Enter amount to withdraw: ");
                    double withdraw = sc.nextDouble();
                    if (withdraw > balance) {
                        System.out.println("Insufficient Balance!");
                    } else if (withdraw <= 0) {
                        System.out.println("Invalid Amount!");
                    } else {
                        balance -= withdraw;
                        System.out.println("Withdrawal Successful!");
                    }
                    break;

                case 4:
                    System.out.println("Thank you for using ATM 😊");
                    break;

                default:
                    System.out.println("Invalid Option!");
            }
        } while (choice != 4);

        sc.close();
    }
}
/*
🖥 Sample Output (Successful Run)
===== ATM MACHINE =====
Enter your PIN: 1234

===== ATM MENU =====
1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Exit
Enter your choice: 1
Your Current Balance: ₹5000.0

===== ATM MENU =====
1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Exit
Enter your choice: 2
Enter amount to deposit: 2000
Amount Deposited Successfully!

===== ATM MENU =====
1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Exit
Enter your choice: 3
Enter amount to withdraw: 1500
Withdrawal Successful!

===== ATM MENU =====
1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Exit
Enter your choice: 1
Your Current Balance: ₹5500.0

===== ATM MENU =====
1. Check Balance
2. Deposit Money
3. Withdraw Money
4. Exit
Enter your choice: 4
Thank you for using ATM 😊

SAMPLE OUTPUT (WRONG PIN CASE)
===== ATM MACHINE =====
Enter your PIN: 1111
Incorrect PIN! Try again.
*/



