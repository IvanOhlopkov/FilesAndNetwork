import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        System.out.println("Выберите приложение: 1 - лифт ; 2 - груз" + "\n" +
                "_____________________________________________________________");
        int value = new Scanner(System.in).nextInt();

        switch (value) {
            case 1 -> {
                Elevator elevator = new Elevator(-3, 26);
                while (true) {
                    System.out.println("Введите номер этажа: ");
                    int floor = new Scanner(System.in).nextInt();
                    elevator.move(floor);
                }
            }
            case 2 -> {
                Dimensions dimensions = new Dimensions(30, 40, 50);

                Cargo cargoOriginal = new Cargo(dimensions, 500, "ул. Ленина 2", true,
                        "A33-U02", false);
                System.out.println("Оригинал: " + cargoOriginal + "\n");

                System.out.println("Введите новый адрес доставки: ");
                String newAddress = new Scanner(System.in).nextLine();

                Cargo cargoCopy = cargoOriginal.setDeliveryAddress(newAddress);
                System.out.println("Копия: " + cargoCopy + "\n");

                String originalAddress = cargoOriginal.getDeliveryAddress();
                String copyAddress = cargoCopy.getDeliveryAddress();

                System.out.println("Адрес оригинального груза: " + originalAddress + "\n" +
                        "Адрес скопированного груза: " + copyAddress);

            }
            default -> System.out.println("Неверный ввод");
        }
    }
}
