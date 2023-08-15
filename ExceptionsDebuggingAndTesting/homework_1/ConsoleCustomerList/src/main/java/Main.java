import java.util.Scanner;

import org.apache.logging.log4j.*;

public class Main {
    private static final String ADD_COMMAND = "add Василий Петров " +
            "vasily.petrov@gmail.com +79215637722";
    private static final String COMMAND_EXAMPLES = "\t" + ADD_COMMAND + "\n" +
            "\tlist\n\tcount\n\tremove Василий Петров";
    private static final String COMMAND_ERROR = "Wrong command! Available command examples: \n" +
            COMMAND_EXAMPLES;
    private static final String helpText = "Command examples:\n" + COMMAND_EXAMPLES;

    private static final Logger logger = LogManager.getLogger(Main.class);
    private static final Marker INPUT_QUERY = MarkerManager.getMarker("QUERY");
    private static final Marker ERROR = MarkerManager.getMarker("ERROR");
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStorage executor = new CustomerStorage();

        while (true) {
            try {
                String command = scanner.nextLine();
                String[] tokens = command.split("\\s+", 2);

                logger.info(INPUT_QUERY, "Пользователь ввел: " + command);

                if (tokens[0].equals("add")) {
                    if (tokens.length == 1) {
                        throw new ArrayIndexOutOfBoundsException("Specify a customer for add. " +
                                "Correct format:\n" + ADD_COMMAND);
                    }
                    executor.addCustomer(tokens[1]);
                } else if (tokens[0].equals("list")) {
                    executor.listCustomers();
                } else if (tokens[0].equals("remove")) {
                    if (tokens.length == 1) {
                        throw new ArrayIndexOutOfBoundsException("Specify a customer for remove. " +
                                "Correct format:\nremove Василий Петров");
                    }
                    executor.removeCustomer(tokens[1]);
                } else if (tokens[0].equals("count")) {
                    System.out.println("There are " + executor.getCount() + " customers");
                } else if (tokens[0].equals("help")) {
                    System.out.println(helpText);
                } else {
                    logger.error(ERROR, "Пользователь ввел ошибочную команду");
                    System.out.println(COMMAND_ERROR);
                }
            } catch (Exception ex) {
                logger.error(ERROR, ex.getMessage());
                System.out.println(ex.getMessage());
            }
        }
    }
}
