import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import java.sql.SQLException;

public class XMLHandlerForBigFiles extends DefaultHandler {
    private int count;

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) {
        if (qName.equals("voter")) {
            DBConnection.countVoter(attributes.getValue("name")
                    , attributes.getValue("birthDay"));
        }
        count += 1;
        if (count == 1_000_000) {
            try {
                DBConnection.executeMultiInsertVoters();
                DBConnection.clearInsertQueryVoters();
                count = 0;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) {
        if (qName.equals("voters")) {
            try {
                DBConnection.executeMultiInsertVoters();
            } catch (SQLException e) {

            }
        }
    }
}
