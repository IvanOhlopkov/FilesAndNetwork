import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

public class XMLHandler extends DefaultHandler {
    private Voter voter;
    private WorkTime workTime;
    private static SimpleDateFormat birthDayFormat = new SimpleDateFormat("yyyy.MM.dd");
    private static SimpleDateFormat visitDateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm:ss");
    private ArrayList<Voter> voterCounts;
    private HashMap<String, WorkTime> voteStationWorkTimes;

    public XMLHandler() {
        voterCounts = new ArrayList<>();
        voteStationWorkTimes = new HashMap<>();
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        try {
            if (qName.equals("voter") && voter == null) {
                Date birthDay = birthDayFormat.parse(attributes.getValue("birthDay"));
                voter = new Voter(attributes.getValue("name"), birthDay);
            } else if (qName.equals("visit") && voter != null) {
                voterCounts.add(voter);
            }
            if (qName.equals("visit")) {
                Date time = visitDateFormat.parse(attributes.getValue("time"));
                workTime = voteStationWorkTimes.get(attributes.getValue("station"));
                if (workTime == null) {
                    workTime = new WorkTime();
                    voteStationWorkTimes.put(attributes.getValue("station"),
                            workTime);
                }
                workTime.addVisitTime(time.getTime());
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        if (qName.equals("voter")) {
            voter = null;
        }
        if (qName.equals("visit")) {
            workTime = null;
        }
    }

    public ArrayList<Voter> getVoterCounts() {
        return voterCounts;
    }

    public HashMap<String, WorkTime> getVoteStationWorkTimes() {
        return voteStationWorkTimes;
    }
}
