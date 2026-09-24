import java.util.Scanner;

public class ATMMachine {
    public static void main(String[] args) {
        double balance = 5000.0; 
        int pin = 1234;          
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("--- Welcome to Smart ATM ---");
        System.out.print("Enter your 4-digit PIN: ");
        int enteredPin = sc.nextInt();
        
        // PIN Verification
        if (enteredPin == pin) {
            int choice;
            
            while (true) {
                System.out.println("\n1. Check Balance");
                System.out.println("2. Deposit Money");
                System.out.println("3. Withdraw Money");
                System.out.println("4. Change PIN"); // Naya Feature
                System.out.println("5. Exit");
                System.out.print("Choose an option: ");
                choice = sc.nextInt();
                
                switch (choice) {
                    case 1:
                        System.out.println("Current Balance: Rs. " + balance);
                        break;
                    case 2:
                        System.out.print("Enter amount to deposit: Rs. ");
                        double depositAmount = sc.nextDouble();
                        balance = balance + depositAmount;
                        System.out.println("Successfully Deposited! New Balance: Rs. " + balance);
                        break;
                    case 3:
                        System.out.print("Enter amount to withdraw: Rs. ");
                        double withdrawAmount = sc.nextDouble();
                        if (withdrawAmount <= balance) {
                            balance = balance - withdrawAmount;
                            System.out.println("Please collect your cash. Remaining Balance: Rs. " + balance);
                        } else {
                            System.out.println("Error: Insufficient Balance!");
                        }
                        break;
                    case 4: 
                        System.out.print("Enter your current PIN again: ");
                        int currentCheck = sc.nextInt();
                        if (currentCheck == pin) {
                            System.out.print("Enter your NEW 4-digit PIN: ");
                            int newPin = sc.nextInt();
                            System.out.print("Confirm your NEW 4-digit PIN: ");
                            int confirmPin = sc.nextInt();
                            
                            if (newPin == confirmPin) {
                                pin = newPin; 
                                System.out.println("Success: PIN changed successfully! Use your new PIN next time.");
                            } else {
                                System.out.println("Error: New PIN and Confirm PIN do not match!");
                            }
                        } else {
                            System.out.println("Error: Incorrect Current PIN!");
                        }
                        break;
                    case 5:
                        System.out.println("Thank you for using our ATM. Goodbye!");
                        System.exit(0);
                    default:
                        System.out.println("Invalid option! Please try again.");
                }
            }
        } else {
            System.out.println("Incorrect PIN! Access Denied.");
        }
    }
}
