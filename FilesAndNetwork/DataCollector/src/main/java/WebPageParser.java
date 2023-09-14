import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebPageParser {

    public static Document getHtml(String url) throws IOException {
        return Jsoup.connect(url).get();
    }

    public static Map<String, List<?>> parsePage(Document document) {
        Elements elements = document.select("[id=\"metrodata\"]");
        Map<String, List<?>> metroMap = new HashMap<>();
        for (Element element : elements) {
            List<Line> lines = new ArrayList<>();
            for (Element line : element.select(".js-metro-line")) {
                lines.add(new Line(line.attr("data-line"), line.text()));
            }
            metroMap.put("lines", lines);

            List<Station> stations = new ArrayList<>();


            String stationRegex = "«([^»]+)»";
            String lineRegex = "[^ln-]+$";
            String number = null;
            String name = null;
            for (Element stationElement : element.select(".single-station")) {
                if (stationElement.children().attr("title").startsWith("переход")) {
                    Station station = new Station(stationElement.select(".name").text(),
                            stationElement.parent().attr("data-line"), true);
                    stations.add(station);
                    LinkedHashMap<String, String> connections = new LinkedHashMap<>();

                    for (Element connectStation : stationElement.getElementsByClass("t-icon-metroln")) {
                        Pattern patternTitle = Pattern.compile(stationRegex);
                        Matcher matcherTitle = patternTitle.matcher(connectStation.attr("title"));
                        while (matcherTitle.find()) {
                            name = matcherTitle.group(1);
                        }
                        Pattern patternClass = Pattern.compile(lineRegex);
                        Matcher matcherClass = patternClass.matcher(connectStation.attr("class"));
                        while (matcherClass.find()) {
                            number = matcherClass.group();
                        }
                        connections.put(number, name);
                    }
                    station.setConnections(connections);
                } else {
                    stations.add(new Station(stationElement.select(".name").text()
                            , stationElement.parent().attr("data-line"), false));
                }
            }
            metroMap.put("stations", stations);
        }
        return metroMap;
    }
}
