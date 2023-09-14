import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

public class CsvFileParser {
    public static LinkedHashSet<String> parse(String path, String formatFile) throws IOException {
        FindFiles findFiles = new FindFiles();
        List<String> list = findFiles.searchFiles(path, formatFile);

        List<String> stationsDateNotUnique = new ArrayList<>();
        for (String file : list) {
            List<String> lines = Files.readAllLines(Paths.get(file));
            String firtsLine = null;
            for (String line : lines) {
                if (firtsLine == null) {
                    firtsLine = line;
                    continue;
                }
                stationsDateNotUnique.add(line.replace("Воробьёвы", "Воробьевы"));
            }
        }
        stationsDateNotUnique.sort(String::compareTo);
        return new LinkedHashSet<>(stationsDateNotUnique);
    }
}

