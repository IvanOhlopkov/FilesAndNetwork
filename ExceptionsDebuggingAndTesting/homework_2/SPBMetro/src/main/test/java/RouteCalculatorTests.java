import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RouteCalculatorTests extends TestCase {

    List<Station> route;
    StationIndex testStationIndex = new StationIndex();
    RouteCalculator routeCalculator = new RouteCalculator(testStationIndex);
    Line line1 = new Line(1, "Калужско-Рижская линия");
    Line line2 = new Line(2, "Кольцевая линия");
    Line line3 = new Line(3, "Серпуховско-Тимирязевская линия");
    Station station1 = new Station("Сухаревская", line1);
    Station station2 = new Station("Проспект Мира", line1);
    Station station3 = new Station("Проспект Мира", line2);
    Station station4 = new Station("Новослободская", line2);
    Station station5 = new Station("Менделеевская", line3);
    Station station6 = new Station("Савёловская", line3);
    Station station7 = new Station("Дмитровская", line3);
    List<Station> connectionsStations1 = new ArrayList<>();
    List<Station> connectionsStations2 = new ArrayList<>();

    @Override
    protected void setUp() {
        route = new ArrayList<>();

        route.add(new Station("Петровская", line1));
        route.add(new Station("Арбузная", line1));
        route.add(new Station("Огуречная", line1));
        route.add(new Station("Морковная", line2));
        route.add(new Station("Яблочная", line2));

        testStationIndex.addStation(station1);
        testStationIndex.addStation(station2);
        testStationIndex.addStation(station3);
        testStationIndex.addStation(station4);
        testStationIndex.addStation(station5);
        testStationIndex.addStation(station6);
        testStationIndex.addStation(station7);
        testStationIndex.addLine(line1);
        testStationIndex.addLine(line2);
        testStationIndex.addLine(line3);

        line1.addStation(station1);
        line1.addStation(station2);
        line2.addStation(station3);
        line2.addStation(station4);
        line3.addStation(station5);
        line3.addStation(station6);
        line3.addStation(station7);

        connectionsStations1.add(station2);
        connectionsStations1.add(station3);
        testStationIndex.addConnection(connectionsStations1);

        connectionsStations2.add(station4);
        connectionsStations2.add(station5);
        testStationIndex.addConnection(connectionsStations2);


    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(route);
        double expected = 11;
        assertEquals(expected, actual);
    }

    public void testRouteOnTheLine() {
        List<Station> actualRoute = routeCalculator.getShortestRoute(station1, station2);
        List<Station> expectedRoute = new ArrayList<>();
        expectedRoute.add(new Station("Сухаревская", new Line(1, "Калужско-Рижская линия")));
        expectedRoute.add(new Station("Проспект Мира", new Line(1, "Калужско-Рижская линия")));
        assertEquals(expectedRoute, actualRoute);
    }

    public void testRouteWithOneConnection() {
        List<Station> actualRoute = routeCalculator.getShortestRoute(station1, station4);
        List<Station> expectedRoute = new ArrayList<>();
        expectedRoute.add(new Station("Сухаревская", new Line(1, "Калужско-Рижская линия")));
        expectedRoute.add(new Station("Проспект Мира", new Line(1, "Калужско-Рижская линия")));
        expectedRoute.add(new Station("Проспект Мира", new Line(2, "Кольцевая линия")));
        expectedRoute.add(new Station("Новослободская", new Line(2, "Кольцевая линия")));
        assertEquals(expectedRoute, actualRoute);
    }

    public void testRouteWithTwoConnection() {
        List<Station> actualRoute = routeCalculator.getShortestRoute(station1, station7);
        List<Station> expectedRoute = new ArrayList<>();
        expectedRoute.add(new Station("Сухаревская", new Line(1, "Калужско-Рижская линия")));
        expectedRoute.add(new Station("Проспект Мира", new Line(1, "Калужско-Рижская линия")));
        expectedRoute.add(new Station("Проспект Мира", new Line(2, "Кольцевая линия")));
        expectedRoute.add(new Station("Новослободская", new Line(2, "Кольцевая линия")));
        expectedRoute.add(new Station("Менделеевская", new Line(3, "Серпухово-Тимирязевская линия")));
        expectedRoute.add(new Station("Савёловская", new Line(3, "Серпухово-Тимирязевская линия")));
        expectedRoute.add(new Station("Дмитровская", new Line(3, "Серпухово-Тимирязевская линия")));
        assertEquals(expectedRoute, actualRoute);
    }

    @Override
    protected void tearDown() {
    }
}
