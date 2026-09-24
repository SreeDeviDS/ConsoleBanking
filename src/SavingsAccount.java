public class SavingsAccount extends Account implements InterestApplicable {

    private static final double INTEREST_RATE = 0.04; // 4% example

    SavingsAccount(double balance) {
        super(balance);
    }

    @Override
    boolean withdraw(double amount) {
        if (amount <= 0) {
            history.add("Failed Withdraw: Invalid amount");
            return false;
        }
        if (amount > getBalance()) {
            history.add("Failed Withdraw: Insufficient balance");
            return false;
        }
        setBalance(getBalance() - amount);
        history.add("Withdrawn: " + amount + ", Balance: " + getBalance());
        return true;
    }

    @Override
    public void applyInterest() {
        double interest = getBalance() * INTEREST_RATE;
        setBalance(getBalance() + interest);
        history.add("Interest applied: " + interest + ", Balance: " + getBalance());
    }
}
