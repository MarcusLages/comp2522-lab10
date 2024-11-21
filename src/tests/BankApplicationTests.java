import static org.junit.jupiter.api.Assertions.*;

import ca.bcit.comp2522.lab10.bam.Bank;
import ca.bcit.comp2522.lab10.bam.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BankApplicationTests {
    private Bank bank1;
    private Bank bank2;
    private BankAccount account1;
    private BankAccount account2;

    @BeforeEach
    void setUp() {
        bank1 = new Bank();
        bank2 = new Bank();
        account1 = new BankAccount("12345", 1000);
        account2 = new BankAccount("67890", 500);
        bank1.addAccount(account1);
        bank2.addAccount(account2);
    }

    @Test
    void depositIncreasesBalanceAndVerify() {
        account1.deposit(200);
        assertEquals(1200, account1.getBalanceUSD());
        account2.deposit(300);
        assertEquals(800, account2.getBalanceUSD());
    }

    @Test
    void withdrawDecreasesBalanceAndVerify() {
        account1.withdraw(200);
        assertEquals(800, account1.getBalanceUSD());
        account2.withdraw(100);
        assertEquals(400, account2.getBalanceUSD());
    }

    @Test
    void cannotWithdrawMoreThanBalanceAndHandleException() {
        final IllegalArgumentException exception1 =
                assertThrows(IllegalArgumentException.class, () -> account1.withdraw(1200));
        assertEquals("Insufficient funds", exception1.getMessage());
        final IllegalArgumentException exception2 =
                assertThrows(IllegalArgumentException.class, () -> account2.withdraw(600));
        assertEquals("Insufficient funds", exception2.getMessage());
    }

    @Test
    void addingAndRetrievingAccountFromBank() {
        BankAccount newAccount = new BankAccount("54321", 100);
        bank2.addAccount(newAccount);
        assertEquals(newAccount, bank2.getAccount("54321"));
        BankAccount newAccount2 = new BankAccount("11111", 300);
        bank1.addAccount(newAccount2);
        assertEquals(newAccount2, bank1.getAccount("11111"));
    }

    @Test
    void transferBetweenBankAccountsAndVerifyBalances() {
        account1.transferToBank(account2, "12345", 200);
        assertEquals(800, account1.getBalanceUSD());
        assertEquals(700, account2.getBalanceUSD());
        account2.transferToBank(account1, "67890", 100);
        assertEquals(900, account1.getBalanceUSD());
        assertEquals(600, account2.getBalanceUSD());
    }

    @Test
    void totalBalanceCalculationForBanks() {
        assertEquals(1000, bank1.getTotalBalanceUSD());
        assertEquals(500, bank2.getTotalBalanceUSD());
        bank1.addAccount(new BankAccount("33333", 200));
        assertEquals(1200, bank1.getTotalBalanceUSD());
    }

    @Test
    void handlingInvalidAccountRetrieval() {
        final IllegalArgumentException exception1 =
                assertThrows(IllegalArgumentException.class, () ->
                        bank1.getAccount("99999"));

        assertEquals("Account not found", exception1.getMessage());

        final IllegalArgumentException exception2 =
                assertThrows(IllegalArgumentException.class, () ->
                        bank2.getAccount("00000"));

        assertEquals("Account not found", exception2.getMessage());
    }

    @Test
    void handlingInvalidBankAccountCreation() {
        final IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> {
                    final BankAccount negativeBalance;
                    negativeBalance = new BankAccount("1234", -50);
                });
        assertEquals("Initial balance cannot be negative.", exception1.getMessage());

        final IllegalArgumentException exception2 = assertThrows(IllegalArgumentException.class,
                () -> {
                    final BankAccount nullName;
                    nullName = new BankAccount(null, 500);
                });
        assertEquals("Invalid question/answer. Input: null", exception2.getMessage());

        final IllegalArgumentException exception3 = assertThrows(IllegalArgumentException.class,
                () -> {
                    final BankAccount emptyName;
                    emptyName = new BankAccount("", 500);
                });
        assertEquals("Invalid question/answer. Input: ", exception3.getMessage());

    }

    @Test
    void handleNegativeDeposit() {
        final IllegalArgumentException exception1 = assertThrows(IllegalArgumentException.class,
                () -> {
                    account1.deposit(-1);
                });
        assertEquals("Deposit amount must be non-negative.", exception1.getMessage());
    }

// Additional tests can include:
// - Checking the initial balance correctness.
// - Handling invalid operations.
// - Summing balances from multiple accounts in a single bank.
}
