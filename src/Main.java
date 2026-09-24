import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<Account> accounts = new ArrayList<>();
        Account currentAccount = null;

        Scanner sc = new Scanner(System.in);
        boolean running = true;

        while (running) {

            System.out.println("\n--- MENU ---");
            System.out.println("1. Create Account");
            System.out.println("2. Select Account");
            System.out.println("3. Deposit");
            System.out.println("4. Withdraw");
            System.out.println("5. View History");
            System.out.println("6. Check Balance");
            System.out.println("7. Apply interest");
            System.out.println("8. Exit");

            int choice = readInt(sc, "Enter your choice:");

            switch (choice) {
                case 1: {
                    System.out.println("Choose account type:");
                    System.out.println("1. Savings");
                    System.out.println("2. Current");
                    int type = readInt(sc, "Enter type:");

                    if (type != 1 && type != 2) {
                        System.out.println("Invalid choice. Please enter 1 or 2");
                        break;
                    }

                    double balance = readDouble(sc, "Enter initial balance:");
                    if (balance < 0) {
                        System.out.println("Negative opening balance is not allowed. Starting with 0.");
                    }

                    Account newAccount;
                    if (type == 1) {
                        newAccount = new SavingsAccount(balance);
                    } else {
                        newAccount = new CurrentAccount(balance);
                    }
                    accounts.add(newAccount);
                    System.out.println("Account created successfully! Account ID: " + newAccount.getAccountId());
                    break;
                }

                case 2: {
                    if (accounts.isEmpty()) {
                        System.out.println("No accounts available");
                        break;
                    }
                    System.out.println("Available accounts:");
                    for (Account a : accounts) {
                        System.out.println("ID: " + a.getAccountId() + " | " + a.getClass().getSimpleName() + " | Balance: " + a.getBalance());
                    }
                    int id = readInt(sc, "Enter ID:");
                    Account found = null;
                    for (Account a : accounts) {
                        if (a.getAccountId() == id) {
                            found = a;
                            break;
                        }
                    }
                    if (found != null) {
                        currentAccount = found;
                        System.out.println("Selected Account ID: " + currentAccount.getAccountId());
                    } else {
                        System.out.println("Invalid ID");
                    }
                    break;
                }

                case 3: {
                    if (currentAccount == null) {
                        System.out.println("Please select an account first");
                        break;
                    }
                    double amount = readDouble(sc, "Enter amount:");
                    if (currentAccount.deposit(amount)) {
                        System.out.println("Amount deposited successfully!");
                    } else {
                        System.out.println("Deposit failed: amount must be greater than 0.");
                    }
                    break;
                }

                case 4: {
                    if (currentAccount == null) {
                        System.out.println("Please select an account first");
                        break;
                    }
                    double amount = readDouble(sc, "Enter amount:");
                    if (currentAccount.withdraw(amount)) {
                        System.out.println("Amount withdrawn successfully!");
                    } else {
                        System.out.println("Withdrawal failed. Check the history for the reason.");
                    }
                    break;
                }

                case 5:
                    if (currentAccount == null) {
                        System.out.println("Please select an account first");
                        break;
                    }
                    currentAccount.printHistory();
                    break;

                case 6:
                    if (currentAccount == null) {
                        System.out.println("Please select an account first");
                        break;
                    }
                    currentAccount.checkBalance();
                    break;

                case 7:
                    if (currentAccount == null) {
                        System.out.println("Please select an account first");
                        break;
                    }
                    if (currentAccount instanceof InterestApplicable) {
                        ((InterestApplicable) currentAccount).applyInterest();
                        System.out.println("Interest added successfully!");
                    } else {
                        System.out.println("Interest is not applicable for this account");
                    }
                    break;

                case 8:
                    running = false;
                    System.out.println("Exit");
                    break;

                default:
                    System.out.println("Invalid choice");
            }
        }
        sc.close();
    }

    // Keeps asking until the user enters a valid whole number
    private static int readInt(Scanner sc, String message) {
        while (true) {
            System.out.println(message);
            if (!sc.hasNextLine()) {
                System.out.println("Input ended. Exiting.");
                System.exit(0);
            }
            String line = sc.nextLine().trim();
            try {
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid whole number");
            }
        }
    }

    // Keeps asking until the user enters a valid number
    private static double readDouble(Scanner sc, String message) {
        while (true) {
            System.out.println(message);
            if (!sc.hasNextLine()) {
                System.out.println("Input ended. Exiting.");
                System.exit(0);
            }
            String line = sc.nextLine().trim();
            try {
                return Double.parseDouble(line);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number");
            }
        }
    }
}
