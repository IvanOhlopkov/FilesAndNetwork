
public class Loader {

    public static void main(String[] args) {

        int cores = Runtime.getRuntime().availableProcessors();
        int regionsStartCount = 1;
        int regionsEndCount = 199;
        char[] letters = {'У', 'К', 'Е', 'Н', 'Х', 'В', 'А', 'Р', 'О', 'С', 'М', 'Т'};

        for (int i = 1; i <= cores; i++) {
            int part = regionsEndCount / cores;
            if (regionsEndCount % cores != 0 && i == cores) {
                part = regionsEndCount % cores + part;
            }

            int partEnd = regionsStartCount + part;
            CarNumberGenerator carNumberGenerator =
                    new CarNumberGenerator(regionsStartCount, partEnd, letters);
            new Thread(carNumberGenerator).start();
            regionsStartCount += part;
        }
    }
}



