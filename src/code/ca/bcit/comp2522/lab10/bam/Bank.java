package ca.bcit.comp2522.lab10.bam;

import java.util.HashMap;
import java.util.Map;

/**
 * The Bank class manages a collection of BankAccount objects.
 * It allows the addition of new accounts, retrieval of accounts by ID,
 * and calculation of the total balance in all accounts.
 *
 * @author Andre Dizon
 * @author Ben Nguyen
 * @author Marcus Lages
 * @version 1.0
 */
public class Bank {

    /** A map that stores the BankAccount objects, keyed by their account IDs */
    private final Map<String, BankAccount> accounts;

    /**
     * Constructs a new Bank object with an empty account list.
     */
    public Bank() {
        accounts = new HashMap<>();
    }

    /**
     * Adds a new account to the bank.
     * If an account with the given ID already exists, no new account is created.
     *
     * @param accountId The unique identifier for the new account.
     * @param initialBalance The initial balance to be set for the new account.
     */
    // Msrcus: put final u mon**y
    public void addAccount(String accountId, double initialBalance) {
        if (!accounts.containsKey(accountId)) {
            final BankAccount newAccount = new BankAccount(accountId, initialBalance);
            accounts.put(accountId, newAccount);
            System.out.println("Account created: " + newAccount);
        } else {
            System.out.println("Account with ID " + accountId + " already exists.");
        }
    }

    // Marcus: missing method
    public void addAccount(final BankAccount account) {}

    /**
     * Retrieves a BankAccount by its account ID.
     * If the account does not exist, a message is printed and null is returned.
     *
     * @param accountId The unique identifier of the account to retrieve.
     * @return The BankAccount associated with the given ID, or null if not found.
     */
    // Marcus: this should be public
    private BankAccount getAccount(final String accountId) {
        if (accountExists(accountId)) {
            return accounts.get(accountId);
        } else {
            System.out.println("Account with ID " + accountId + " does not exist.");
            return null;
        }
    }

    /**
     * Calculates the total balance across all accounts in the bank.
     *
     * @return The total balance of all accounts in the bank.
     */
    // Marcus: Put the currency, like getTotalBalanceUSD()
    public double getTotalBalance() {
        // Marcus: magic number
        double total = 0;
        for (final BankAccount account : accounts.values()) {
            total += account.getBalanceUSD();
        }
        return total;
    }

    /**
     * Displays all the accounts in the bank, including their account ID and balance.
     * If no accounts are available, a message is printed to indicate that.
     */
    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts available.");
        } else {
            for (final BankAccount account : accounts.values()) {
                System.out.println(account);
            }
        }
    }

    /**
     * Checks whether an account with the given account ID exists in the bank.
     *
     * @param accountId The unique identifier of the account to check.
     * @return true if the account exists, false otherwise.
     */
    private boolean accountExists(final String accountId) {
        return accounts.containsKey(accountId);
    }

    /**
     * Validates that the provided account ID is non-null and non-empty.
     *
     * @param accountId The account ID to validate.
     * @return true if the account ID is valid (non-null and non-empty), false otherwise.
     */
    private boolean isValidAccountId(final String accountId) {
        return accountId != null && !accountId.trim().isEmpty();
    }
}
