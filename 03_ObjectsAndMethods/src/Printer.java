public class Printer {
    String queue = "";
    int queuePageCount = 0;
    int printedPagesCount = 0;

    public void append(String text) {
        append(text, "Без названия", 1);
    }

    public void append(String text, String name) {
        append(text, name, 1);
    }

    public void append(String text, int pageCount) {
        append(text, "Без названия", pageCount);
    }

    public void append(String text, String name, int pageCount) {
        queue += "Текст: \"" + text + "\"" + "\tназвание: \"" + name + "\"\tкол-во страниц: " + pageCount + "\n";
        queuePageCount += pageCount;
    }

    public void clear() {
        queue = "";
        queuePageCount = 0;
    }

    public void print() {
        System.out.print(queue);
        printedPagesCount += queuePageCount;
        clear();
    }

    public int getPendingPagesCount() {
        return queuePageCount;
    }

    public int getPrintedPagesCount() {
        return printedPagesCount;
    }

}
