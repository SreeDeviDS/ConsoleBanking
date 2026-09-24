import java.util.ArrayList;

public abstract class Account {
    private double balance;
    private int accountId;
    private static int counter = 0;

    protected ArrayList<String> history = new ArrayList<>();

    Account(double balance) {
        if (balance >= 0) {
            this.balance = balance;
        } else {
            this.balance = 0;
        }
        this.accountId = ++counter;

        history.add("Account created with balance: " + this.balance + " and AccountID: " + this.accountId);
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public int getAccountId() {
        return accountId;
    }

    // Returns true if the deposit was done, false if it was rejected
    boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            history.add("Deposited: " + amount + ", Balance: " + balance);
            return true;
        }
        history.add("Failed deposit: Invalid amount");
        return false;
    }

    // Returns true if the withdrawal was done, false if it was rejected
    abstract boolean withdraw(double amount);

    void checkBalance() {
        System.out.println("Available balance: " + balance);
    }

    void printHistory() {
        System.out.println("Transaction history:");
        history.forEach(s -> System.out.println(s));
    }
}
