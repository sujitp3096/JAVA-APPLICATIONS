import java.util.*;

class BankAccount {
    private String name;
    private double balance;

    BankAccount(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    void deposit(double amount) {
        balance += amount;
        System.out.println("Deposited: " + amount);
    }

    void withdraw(double amount) {
        if (amount > balance)
            System.out.println("Insufficient Balance!");
        else {
            balance -= amount;
            System.out.println("Withdrawn: " + amount);
        }
    }

    void displayBalance() {
        System.out.println("Account Holder: " + name);
        System.out.println("Balance: " + balance);
    }
}

public class SimpleBankApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        BankAccount acc = new BankAccount("Sujit Pawar", 1000);

        while (true) {
            System.out.println("\n1. Deposit  2. Withdraw  3. Check Balance  4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter amount to deposit: ");
                    acc.deposit(sc.nextDouble());
                }
                case 2 -> {
                    System.out.print("Enter amount to withdraw: ");
                    acc.withdraw(sc.nextDouble());
                }
                case 3 -> acc.displayBalance();
                case 4 -> {
                    System.out.println("Thank you for using our bank!");
                    System.exit(0);
                }
                default -> System.out.println("Invalid Choice!");
            }
        }
    }
}
