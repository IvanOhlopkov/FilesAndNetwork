import java.util.HashMap;
import java.util.Map;

public class CustomerStorage {
    private final Map<String, Customer> storage;

    public CustomerStorage() {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) {
        final int INDEX_NAME = 0;
        final int INDEX_SURNAME = 1;
        final int INDEX_EMAIL = 2;
        final int INDEX_PHONE = 3;

        String[] components = data.split("\\s+");
        if (components.length != 4) {
            throw new IllegalArgumentException("Wrong format. Correct format: \n" +
                    "add Василий Петров vasily.petrov@gmail.com +79215637722");
        }

        String emailFormat = "[A-Za-z0-9]+[.]{0,1}[A-Za-z0-9]+[@]{1}[A-Za-z]+[.]{1}[A-Za-z]{2,3}";
        String phoneFormat = "[+]{1}[0-9]{11}";
        try {
            if (!components[2].matches(emailFormat)) {
                throw new EmailException("Wrong email format. Correct format: vasily.petrov@gmail.com");
            }

            if (!components[3].matches(phoneFormat)) {
                throw new TelephoneException("Wrong telephone format. Correct format: +79215637722");
            }
        } catch (EmailException | TelephoneException e) {
            throw new RuntimeException(e);
        }


        String name = components[INDEX_NAME] + " " + components[INDEX_SURNAME];
        storage.put(name, new Customer(name, components[INDEX_PHONE], components[INDEX_EMAIL]));
    }

    public void listCustomers() {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name) {
        String[] components = name.split("\\s+");
        if (components.length != 2) {
            throw new IllegalArgumentException("Wrong format. Correct format: \n" +
                    "remove Василий Петров");
        }
        storage.remove(name);
    }

    public Customer getCustomer(String name) {
        return storage.get(name);
    }

    public int getCount() {
        return storage.size();
    }
}