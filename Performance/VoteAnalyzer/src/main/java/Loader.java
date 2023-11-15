import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Loader {

    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");

    public static void main(String[] args) throws Exception {
        String fileName = "res/data-1572M.xml";

        long start = System.currentTimeMillis();
        parseFile(fileName);
        System.out.println("Parsing duration: "
                + (System.currentTimeMillis() - start) + " ms");
        DBConnection.printVoterCounts();
        DBConnection.printVoteStationTimeWorks();
    }

    private static void parseFile(String fileName) throws Exception {
        SAXParserFactory factory = SAXParserFactory.newInstance();
        SAXParser parser = factory.newSAXParser();

        File file = new File(fileName);
        long fileSize = file.length();

        if (fileSize > 104857600) {
            DBConnection.hasBigFileParse();
            XMLHandlerForBigFiles handler = new XMLHandlerForBigFiles();
            parser.parse(file, handler);
        } else {
            XMLHandler handler = new XMLHandler();
            parser.parse(new File(fileName), handler);
            ArrayList<Voter> voterCount = handler.getVoterCounts();
            HashMap<String, WorkTime> voteStationWorkTime = handler.getVoteStationWorkTimes();

            findEqualVoters(voterCount);
            fixWorkTimes(voteStationWorkTime);
        }
    }

    private static void findEqualVoters(ArrayList<Voter> voterCount) throws Exception {
        for (Voter voter : voterCount) {
            String name = voter.getName();
            String birthDay = birthDayFormat.format(voter.getBirthDay());
            DBConnection.countVoter(name, birthDay);
        }
        DBConnection.executeMultiInsertVoters();
    }

    private static void fixWorkTimes(HashMap<String, WorkTime> voteStationWorkTimes) throws Exception {
        for (Map.Entry<String, WorkTime> voteStation : voteStationWorkTimes.entrySet()) {
            String station = voteStation.getKey();
            String workTime = voteStation.getValue().toString();
            DBConnection.stationWorkTime(station, workTime);
        }
        DBConnection.executeMultiInsertStations();
    }
}