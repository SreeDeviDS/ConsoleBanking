public class CurrentAccount extends Account {

    // A current account can go below zero, but only up to this limit
    private static final double OVERDRAFT_LIMIT = 5000;

    CurrentAccount(double balance) {
        super(balance);
    }

    @Override
    boolean withdraw(double amount) {
        if (amount <= 0) {
            history.add("Failed Withdraw: Invalid amount");
            return false;
        }
        if (amount > getBalance() + OVERDRAFT_LIMIT) {
            history.add("Failed Withdraw: Overdraft limit exceeded");
            return false;
        }
        setBalance(getBalance() - amount);
        history.add("Withdrawn: " + amount + ", Balance: " + getBalance());
        return true;
    }
}
