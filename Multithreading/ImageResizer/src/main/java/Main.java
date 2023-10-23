import java.io.File;

public class Main {
    private static int newWidth = 300;

    public static void main(String[] args) {

        String srcFolder = "/users/iokhl/Desktop/skillbox/src";
        String dstFolder = "/users/iokhl/Desktop/skillbox/dst";

        File srcDir = new File(srcFolder);
        long start = System.currentTimeMillis();
        File[] files = srcDir.listFiles();

        int cores = Runtime.getRuntime().availableProcessors();

        int dstPos = 0;
        for (int i = 1; i <= cores; i++) {

            int part = files.length / cores;
            if (files.length % cores != 0 && i == cores) {
                part = part + files.length % cores;
            }
            File[] filesPartArray = new File[part];

            System.arraycopy(files, dstPos, filesPartArray, 0, filesPartArray.length);
            ImageResizer resizer = new ImageResizer(filesPartArray, newWidth, dstFolder, start);
            new Thread(resizer).start();
            dstPos = dstPos + part;
        }

    }
}
