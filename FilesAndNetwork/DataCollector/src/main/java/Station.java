import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class Station {
    private String name;
    private String lineNumber;
    @JsonIgnore
    private boolean hasConnection;

    public Station(String name, String lineNumber) {
        this.name = name;
        this.lineNumber = lineNumber;
    }

    public Station(String name, String lineNumber, boolean hasConnection){
        this.name = name;
        this.lineNumber = lineNumber;
        this.hasConnection = hasConnection;
    }

    public Station() {

    }

    @Override
    public String toString() {
        return "Station{" +
                "name='" + name + '\'' +
                ", number=" + lineNumber +
                ", hasConnection=" +hasConnection +
                '}';
    }
}
