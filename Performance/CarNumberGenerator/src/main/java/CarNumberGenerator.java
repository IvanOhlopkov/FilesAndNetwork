import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class CarNumberGenerator implements Runnable {

    private int regionStartCode;
    private int regionEndCode;
    char letters[];

    public CarNumberGenerator(int regionStartCode, int regionEndCode, char[] letters) {
        this.regionStartCode = regionStartCode;
        this.regionEndCode = regionEndCode;
        this.letters = letters;
    }

    @Override
    public void run() {
        long start = System.currentTimeMillis();
        for (int regionCode = regionStartCode; regionCode < regionEndCode; regionCode++) {
            PrintWriter writer = null;
            try {
                writer = new PrintWriter("res/numbers" + regionCode + "region.txt");
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            StringBuilder builder = new StringBuilder();
            for (int number = 1; number < 1000; number++) {
                for (char firstLetter : letters) {
                    for (char secondLetter : letters) {
                        for (char thirdLetter : letters) {
                            builder.append(firstLetter);
                            builder.append(padNumber(number, 3));
                            builder.append(secondLetter);
                            builder.append(thirdLetter);
                            builder.append(padNumber(regionCode, 2));
                            builder.append("\n");
                        }
                    }
                }
            }
            writer.write(builder.toString());
            writer.flush();
            writer.close();
        }
        System.out.println(System.currentTimeMillis() - start);
    }

    private static String padNumber(int number, int numberLength) {
        String numberStr = Integer.toString(number);

        int padSize = numberLength - numberStr.length();

        for (int i = 0; i < padSize; i++) {
            numberStr = "0".concat(numberStr);
        }

        return numberStr;
    }
}
