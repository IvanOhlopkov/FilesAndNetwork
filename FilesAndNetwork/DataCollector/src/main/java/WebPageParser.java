import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;

public class WebPageParser {
    public static void main(String[] args) throws IOException {

        String url = "https://skillbox-java.github.io/";
        System.out.println(parsePage(getHtml(url)));

    }

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
            for (Element station : element.select(".single-station")) {
                if (station.children().attr("title").startsWith("переход")) {
                    stations.add(new Station(station.select(".name").text(),
                            station.parent().attr("data-line"), true));
                } else {
                    stations.add(new Station(station.select(".name").text()
                            , station.parent().attr("data-line")));
                }
            }
            metroMap.put("stations", stations);
        }
        return metroMap;
    }

    //public static TreeMap<> getConnections(){


}
