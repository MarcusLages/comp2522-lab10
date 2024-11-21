package ca.bcit.comp2522.lab10.bam;

/**
 * Represents a bank account with basic operations to deposit, withdraw,
 * and retrieve the current balance.
 *
 * @author Ben Nguyen, Marcus Lages, Andre Dizon
 * @version 1.0
 */
public class BankAccount {

    private double currentBalance;
    private final String bankAccountId;
    private static final int NO_FUNDS = 0;

    /**
     * Constructs a BankAccount object with the specified initial balance.
     *
     * @param initialBalance the initial balance of the account in USD.
     * @throws IllegalArgumentException if the initial balance is negative.
     */
    public BankAccount(final String bankAccountId, final double initialBalance) {

        validateCurrentBalance(initialBalance);
        validateAccountNumber(bankAccountId);

        this.currentBalance = initialBalance;
        this.bankAccountId = bankAccountId;
    }

    /**
     * Retrieves the current balance of the account in USD.
     *
     * @return the current balance.
     */
    public double getBalanceUSD() {
        return currentBalance;
    }

    public String getBankAccountId() {
        return bankAccountId;
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
            throw new IllegalArgumentException("Insufficient funds");
        }

        currentBalance -= amount;
    }
    /**
     * Deposits the specified amount into another account
     *
     * @param bankAccountReceiver the bank account receiving money.
     * @param bankAccountId the bank account sending money.
     * @param amount the amount to deposit, must be a positive value.
     */
    public void transferToBank(final BankAccount bankAccountReceiver,
                               final String bankAccountId,
                               final int amount) {

        if (bankAccountId.equals(this.bankAccountId)) {
            bankAccountReceiver.deposit(amount);
            withdraw(amount);
        }
    }
    /**
     * Validates the specified initial balance to ensure it is non-negative.
     *
     * @param currentBalance the balance to validate.
     * @throws IllegalArgumentException if the balance is negative.
     */
    public void validateCurrentBalance(final double currentBalance) {

        if (currentBalance < NO_FUNDS) {
            throw new IllegalArgumentException("Initial balance cannot be negative.");
        }
    }
    public void validateAccountNumber(final String accountNumber) {

        if (accountNumber == null || accountNumber.isEmpty() || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Invalid question/answer. Input: " + accountNumber);
        }
    }

}