package ca.bcit.comp2522.lab10.bam;
/**
 * Represents a bank account with basic operations to deposit, withdraw,
 * and retrieve the current balance.
 *
 * @author Ben Nguyen, Marcus Lages, Andre Dizon
 * @version 1.0
 */
public class BankAccount {
    private int currentBalance;
    private String bankAccountId;
    private static final int NO_FUNDS = 0;
    //you need to add another parameter for bank account id :0
    //Andre
    /**
     * Constructs a BankAccount object with the specified initial balance.
     *
     * @param currentBalance the initial balance of the account in USD.
     * @throws IllegalArgumentException if the initial balance is negative.
     */
    public BankAccount(final int currentBalance) {
        validateCurrentBalance(currentBalance);
        validateAccountNumber(bankAccountId);
        this.currentBalance = currentBalance;
        this.bankAccountId = bankAccountId;
    }

    /**
     * Retrieves the current balance of the account in USD.
     *
     * @return the current balance.
     */
    public int getBalanceUSD() {
        return currentBalance;
    }

    /**
     * Deposits the specified amount into the account.
     *
     * @param amount the amount to deposit, must be a positive value.
     * @throws IllegalArgumentException if the deposit amount is negative.
     */
    public void deposit(final int amount) {
        if (amount < NO_FUNDS) {
            throw new IllegalArgumentException("Deposit amount must be non-negative.");
        }
        currentBalance += amount;
    }

    /**
     * Withdraws the specified amount from the account.
     *
     * @param amount the amount to withdraw, must not exceed the current balance.
     * @throws IllegalArgumentException if the withdrawal amount exceeds the current balance.
     */
    public void withdraw(final int amount) {
        if (amount > currentBalance) {
            throw new IllegalArgumentException("Insufficient funds.");
        }
        currentBalance -= amount;
    }

    /**
     * Validates the specified initial balance to ensure it is non-negative.
     *
     * @param currentBalance the balance to validate.
     * @throws IllegalArgumentException if the balance is negative.
     */
    public void validateCurrentBalance(final int currentBalance) {
        if (currentBalance < NO_FUNDS) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
    }
    public void validateAccountNumber(final String accountNumber) {
        if (accountNumber == null||accountNumber.isEmpty() || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Invalid question/answer. Input: " + accountNumber);
        }
    }
}