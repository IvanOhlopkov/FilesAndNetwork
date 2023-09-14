import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import java.util.HashMap;

@Data
public class Station {
    private String name;
    private String lineNumber;
    @JsonIgnore
    private String date;
    @JsonIgnore
    private double depth;
    @JsonIgnore
    private boolean hasConnection;
    @JsonIgnore
    private HashMap<String, String> connections;

    public Station(String name, String lineNumber, boolean hasConnection){
        this.name = name;
        this.lineNumber = lineNumber;
        this.hasConnection = hasConnection;
    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", number=" + lineNumber +
                ", date=" + date +
                ", depth=" + depth +
                ", hasConnection=" +hasConnection +
                '}';
    }
}
