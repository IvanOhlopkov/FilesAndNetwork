import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CsvFileParser {
    public static void main(String[] args) throws IOException {
        List<String> list = FindFiles.searchFiles("data", ".csv");
        parse(list);
    }

    public static void parse(List<String> list) throws IOException {
        List<String> lines = Files.readAllLines(Paths.get(list.get(0)));
        String firtsLine = null;
        for(String line : lines) {
            String[] fragments = line.split(",");
            if(firtsLine == null) {
                firtsLine = line;
                continue;
            }
            System.out.println(fragments[0]);
            System.out.println(fragments[1]);
        }
    }

}

