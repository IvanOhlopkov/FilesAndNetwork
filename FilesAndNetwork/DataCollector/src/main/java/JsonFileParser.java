import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

public class JsonFileParser {

    public static void main(String[] args) throws IOException {
        List<String> list = FindFiles.searchFiles("data", ".json");
        parse(list);
    }

    public static void parse(List<String> list) throws IOException {
        String jsonFile = Files.readString(Paths.get(list.get(0)));
        ObjectMapper objectMapper = new ObjectMapper();
        ArrayNode arrayNode = (ArrayNode) objectMapper.readTree(jsonFile);
        for (JsonNode jsonNode : arrayNode) {
            System.out.println(jsonNode.get("station_name").asText());
            jsonNode.get("depth");
        }

    }

}
