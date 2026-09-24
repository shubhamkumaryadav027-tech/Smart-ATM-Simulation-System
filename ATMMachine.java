import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ATMMachine {
    public static void main(String[] args) {
        double balance = 5000.0;
        int pin = 1234;
        ArrayList<String> history = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Welcome to Smart ATM ---");

        // 3 attempts, then block
        int attempts = 0;
        boolean verified = false;
        while (attempts < 3) {
            System.out.print("Enter your 4-digit PIN: ");
            int enteredPin = sc.nextInt();
            if (enteredPin == pin) {
                verified = true;
                break;
            }
            attempts++;
            System.out.println("Incorrect PIN! Attempts left: " + (3 - attempts));
        }
        if (!verified) {
            System.out.println("Card blocked. Contact your bank.");
            return;
        }

        while (true) {
            System.out.println("\n1. Check Balance");
            System.out.println("2. Deposit Money");
            System.out.println("3. Withdraw Money");
            System.out.println("4. Change PIN");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Choose an option: ");

            try {
                int choice = sc.nextInt();
                switch (choice) {
                    case 1:
                        System.out.println("Current Balance: Rs. " + balance);
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: Rs. ");
                        double dep = sc.nextDouble();
                        if (dep <= 0) {
                            System.out.println("Error: Invalid amount!");
                        } else {
                            balance += dep;
                            history.add("Deposited: Rs. " + dep);
                            System.out.println("Deposited! New Balance: Rs. " + balance);
                        }
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: Rs. ");
                        double wd = sc.nextDouble();
                        if (wd <= 0) {
                            System.out.println("Error: Invalid amount!");
                        } else if (wd > 20000) {
                            System.out.println("Error: Limit is Rs. 20,000 per transaction!");
                        } else if (wd > balance) {
                            System.out.println("Error: Insufficient Balance!");
                        } else {
                            balance -= wd;
                            history.add("Withdrawn: Rs. " + wd);
                            System.out.println("Collect your cash. Balance: Rs. " + balance);
                        }
                        break;
                    case 4:
                        System.out.print("Enter current PIN: ");
                        if (sc.nextInt() == pin) {
                            System.out.print("Enter NEW PIN: ");
                            int newPin = sc.nextInt();
                            System.out.print("Confirm NEW PIN: ");
                            if (newPin == sc.nextInt()) {
                                pin = newPin;
                                System.out.println("PIN changed successfully!");
                            } else {
                                System.out.println("Error: PINs do not match!");
                            }
                        } else {
                            System.out.println("Error: Incorrect current PIN!");
                        }
                        break;
                    case 5:
                        if (history.isEmpty()) {
                            System.out.println("No transactions yet.");
                        } else {
                            for (String t : history) System.out.println(t);
                        }
                        break;
                    case 6:
                        System.out.println("Thank you for using our ATM. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid option! Try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Error: Please enter numbers only!");
                sc.nextLine(); // clear the bad input
            }
        }
    }
}
