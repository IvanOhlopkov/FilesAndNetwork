public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40, 1, 1);
        basket.add("Potato", 33, 2, 5);
        basket.print("Milk");
        Arithmetic arithmetic = new Arithmetic(1, 2);
        System.out.println(arithmetic.getMinOfNumbers());

        System.out.println("______________");

        Printer printer = new Printer();
        printer.append("текст 1");
        printer.append("текст 2", "Название 1");
        printer.append("текст 3", 3);
        System.out.println(printer.getPrintedPagesCount());
        System.out.println(printer.getPendingPagesCount());
        printer.print();
        System.out.println(printer.getPrintedPagesCount());
        System.out.println(printer.getPendingPagesCount());
    }
}
