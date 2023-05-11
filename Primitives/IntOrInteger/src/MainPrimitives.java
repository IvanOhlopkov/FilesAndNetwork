public class MainPrimitives {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());

        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.
        for (int i = 1040; i < 1104; i++) {
            char c = (char) i;
            System.out.println(i + " - " + c);
            switch (c) {
                case 'Е' -> {
                    char d = (char) 1025;
                    System.out.println(1025 + " - " + d);
                }
                case 'е' -> {
                    char d = (char) 1105;
                    System.out.println(1105 + " - " + d);
                }
            }
        }
    }
}

