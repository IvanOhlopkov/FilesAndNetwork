import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FindFiles {
    private static List<String> list = new ArrayList<>();
    public static void main(String[] args) {
        String path = "data";
        System.out.println(searchFiles(path, ".json"));
        System.out.println(searchFiles(path, ".csv"));
    }

    public static List<String> searchFiles(String path, String formatFile){
        File folder = new File(path);
        File[] files = folder.listFiles();

        for (File file : files) {
            if (file.isDirectory()) {
                searchFiles(String.valueOf(file),formatFile);
            } else if (file.isFile() &&
                    file.getName().endsWith(formatFile)){
                list.add(file.getPath());
            }
        }
        return list;
    }
}
