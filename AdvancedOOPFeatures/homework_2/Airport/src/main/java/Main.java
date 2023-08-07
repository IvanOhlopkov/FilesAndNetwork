import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {


    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        return airport.getTerminals().stream()
                .map(Terminal::getFlights)
                .flatMap(Collection::stream)
                .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE))
                .filter(f -> f.getDate().after(new Date(System.currentTimeMillis())))
                .filter(f -> f.getDate().before(new Date(System.currentTimeMillis() + 7200000)))
                .collect(Collectors.toList());
    }

}