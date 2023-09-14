import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class DataHandler {

    public static void main(String[] args) throws IOException {
        String url = "https://skillbox-java.github.io/";
        Map<String, List<?>> metroMap = WebPageParser.parsePage(WebPageParser.getHtml(url));
        LinkedHashSet<String> csvMap = CsvFileParser.parse("data", ".csv");
        LinkedList<String> jsonMap = JsonFileParser.parse("data", ".json");

        createMapFile(metroMap);
        createStationsFile(metroMap, csvMap, jsonMap);
    }

    public static void createMapFile(Map<String, List<?>> metroMap) {
        File output = new File("map.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mapFile = new LinkedHashMap<>();
        Map<String, List<String>> mapStations = new LinkedHashMap<>();
        List<?> lines = metroMap.get("lines");
        List<?> stations = metroMap.get("stations");
        HashSet<List<Station>> connections = new HashSet<>();

        for (Object value : lines) {
            List<String> stationsInLine = new ArrayList<>();
            Line line = (Line) value;

            for (Object o : stations) {
                Station station = (Station) o;
                if (line.getNumber().equals(station.getLineNumber())) {
                    stationsInLine.add(station.getName());
                    mapStations.put(line.getNumber(), stationsInLine);
                }
            }
        }

        for (Object o : stations) {
            Station station = (Station) o;
            if (station.isHasConnection()) {
                List<Station> connection = new ArrayList<>();
                connection.add(station);
                for (Map.Entry<String, String> b : station.getConnections().entrySet()) {
                    for (Object c : stations) {
                        Station connectedStation = (Station) c;
                        if (b.getValue().equals(connectedStation.getName())
                                && b.getKey().equals(connectedStation.getLineNumber())) {
                            connection.add(connectedStation);
                        }
                        connection.sort((o1, o2) -> {
                            if (o1.getName().equals(o2.getName())) {
                                return o1.getLineNumber().compareTo(o2.getLineNumber());
                            }
                            return o1.getName().compareTo(o2.getName());
                        });
                    }
                }
                connections.add(connection);
            }
        }

        mapFile.put("stations", mapStations);
        mapFile.put("lines", lines);
        mapFile.put("connections", connections);

        try {
            objectMapper.writeValue(output, mapFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void createStationsFile(Map<String, List<?>> metroMap
            , LinkedHashSet<String> stationsDate, LinkedList<String> stationsDepth) {
        File output = new File("stations.json");
        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mapFile = new LinkedHashMap<>();

        List<?> stations = metroMap.get("stations");
        List<?> lines = metroMap.get("lines");
        List<Object> list = new ArrayList<>();

        for (Object o : stations) {
            Station station = (Station) o;
            String removeDateLine = null;
            String removeDepthLine = null;

            for (String entry : stationsDate) {
                String[] fragments = entry.split(",");
                if (station.getName().equalsIgnoreCase(fragments[0])
                        && station.getDate() == null) {
                    station.setDate(fragments[1]);
                    removeDateLine = entry;
                }
            }
            stationsDate.remove(removeDateLine);

            for (String entry : stationsDepth) {
                if (entry.contains(station.getName()) && station.getDepth() == 0) {
                    String[] fragments = entry.split(",");
                    double depth = Double.parseDouble(fragments[1]);
                    station.setDepth(depth);
                    removeDepthLine = entry;
                }
            }
            stationsDepth.remove(removeDepthLine);
        }

        for(Object o : stations){
            Station station = (Station) o;
            ObjectNode jsonObject = objectMapper.createObjectNode();
            jsonObject.put("name", station.getName());
            for(Object c : lines){
                Line line = (Line) c;
                if(station.getLineNumber().equals(line.getNumber())){
                    jsonObject.put("line", line.getName());
                }
            }
            jsonObject.put("date", station.getDate());
            jsonObject.put("depth", station.getDepth());
            jsonObject.put("hasConnection", station.isHasConnection());
            list.add(jsonObject);
        }
        mapFile.put("stations", list);

        try {
            objectMapper.writeValue(output, mapFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}


