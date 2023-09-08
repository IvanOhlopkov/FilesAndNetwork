import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DataHandler {

    public static void main(String[] args) throws IOException {
        String url = "https://skillbox-java.github.io/";

        createMapFile(WebPageParser.parsePage(WebPageParser.getHtml(url)));
    }

    public static void createMapFile(Map<String, List<?>> metroMap) {
        File output = new File("export.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mapFile = new LinkedHashMap<>();
        Map<String, List<String>> mapStations = new LinkedHashMap<>();
        List<?> lines = metroMap.get("lines");
        List<?> stations = metroMap.get("stations");

        for (int i = 0; i < lines.size(); i++) {
            List<String> stationsInLine = new ArrayList<>();
            Line line = (Line) lines.get(i);

            for (int c = 0; c < stations.size(); c++){
                Station station = (Station) stations.get(c);
                if (line.getNumber().equals(station.getLineNumber())) {
                    stationsInLine.add(station.getName());
                    mapStations.put(line.getNumber(), stationsInLine);
                }
            }
        }
        mapFile.put("stations", mapStations);
        mapFile.put("lines", lines);


        try {
            objectMapper.writeValue(output, mapFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

