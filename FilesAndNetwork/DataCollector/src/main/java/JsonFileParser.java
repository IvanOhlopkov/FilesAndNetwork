import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JsonFileParser {
    public static LinkedList<String> parse(String path, String formatFile) throws IOException {
        FindFiles findFiles = new FindFiles();
        List<String> listJson = findFiles.searchFiles(path, formatFile);
        LinkedList<String> stationsDepth = new LinkedList<>();
        for (String file : listJson) {
            String jsonFile = Files.readString(Paths.get(file));
            ObjectMapper objectMapper = new ObjectMapper();
            ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(jsonFile);
            for (JsonNode jsonNode : arrayNode) {
                String stationName = jsonNode.get("station_name").asText();
                double stationDepth = jsonNode.get("depth").asDouble();
                for (String line : stationsDepth) {
                    String[] fragments = line.split(",");
                    if (fragments[0].equals(stationName)
                            && Double.parseDouble(fragments[1])
                            < stationDepth) {
                        stationDepth = Double.parseDouble(fragments[1]);
                    }
                }
                stationsDepth.add(stationName + "," +
                        stationDepth);
            }
        }
        return stationsDepth;
    }
}
