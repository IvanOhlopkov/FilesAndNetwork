import java.util.Map;
import java.util.Random;

public class Bank {

    private Map<String, Account> accounts;
    private Random random = new Random();

    public Bank(Map<String, Account> accounts) {
        this.accounts = accounts;
    }

    public synchronized boolean isFraud(String fromAccountNum, String toAccountNum, long amount)
            throws InterruptedException {
        Thread.sleep(1000);
        return random.nextBoolean();
    }

    public void transfer(String fromAccountNum, String toAccountNum, long amount) {
        if (accounts.get(fromAccountNum).getBlockedStatus()) {
            return;
        }
        if (accounts.get(toAccountNum).getBlockedStatus()) {
            return;
        }

        synchronized (accounts.get(fromAccountNum)) {
            accounts.get(fromAccountNum).setMoney(accounts.get(fromAccountNum).getMoney() - amount);
        }
        synchronized (accounts.get(toAccountNum)) {
            accounts.get(toAccountNum).setMoney(accounts.get(toAccountNum).getMoney() + amount);
        }

        if (amount > 50000) {
            try {
                if (isFraud(fromAccountNum, toAccountNum, amount)) {
                    accounts.get(fromAccountNum).setBlockedStatus(true);
                    System.out.println("Номер счета отправителя: "
                            + accounts.get(fromAccountNum).getAccNumber() + " заблокирован. "
                            + "\n" + "Сумма перевода: " + amount);
                    accounts.get(toAccountNum).setBlockedStatus(true);
                    System.out.println("Номер счета получателя: "
                            + accounts.get(toAccountNum).getAccNumber() + " заблокирован. "
                            + "\n" + "Сумма перевода: " + amount);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public long getBalance(String accountNum) {
        return accounts.get(accountNum).getMoney();
    }

    public long getSumAllAccounts() {
        long sum = 0;
        for (Map.Entry<String, Account> account : accounts.entrySet()) {
            sum += account.getValue().getMoney();
        }
        return sum;
    }
}
