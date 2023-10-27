import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BankTest {
    static Map<String, Account> testAccounts = new TreeMap<>();
    static Bank bank;
    static long sumAllAccounts;

    @BeforeEach
    protected void setUp() {

        for(int i = 1; i <= 100; i++) {
            long moneyAccount = 100000 + i;
            testAccounts.put("Счет номер: " + i,
                    new Account(moneyAccount, "40817810" + String.format("%012d", i)));
            sumAllAccounts += moneyAccount;
        }
        bank = new Bank(testAccounts);
    }

    @Test
    @DisplayName("Сумма всех счетов")
    public void testTransferAllAccount() throws InterruptedException {
        long expected = 10005050;
        for(int i = 0; i < 100; i++) {
            new Thread(() -> {
                for(int j = 1; j <= 50; j++) {
                    bank.transfer("Счет номер: " + j
                            , "Счет номер: " + (100-j), 200);
                }
            }).start();
        }
            Thread.sleep(1000);
        long actual = bank.getSumAllAccounts();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("5% транзакций с суммой больше 50000. Счет 1 - не изменился остатка")
    public void testFraudMethod() throws InterruptedException {
        testAccounts.get("Счет номер: 1").setBlockedStatus(true);
        long expected = 100001;
        for(int i = 0; i < 100; i++) {
            new Thread(() -> {
                for(int j = 1; j <= 50; j++) {
                    bank.transfer("Счет номер: " + j
                            , "Счет номер: " + (100-j)
                            , 100 + (Math.random() < 0.95 ? 0 : 50000));
                }
            }).start();
        }
        Thread.sleep(1000);
        long actual = testAccounts.get("Счет номер: 1").getMoney();
        assertEquals(expected, actual);
    }

    @Test
    @DisplayName("Тест трансфера - сумма получателя")
    public void testTransfer(){
        long expected = bank.getBalance("Счет номер: 3") + 350;
        bank.transfer("Счет номер: 36"
                , "Счет номер: 3", 350);
        long actual = bank.getBalance("Счет номер: 3");

        assertEquals(expected, actual);
    }

}
