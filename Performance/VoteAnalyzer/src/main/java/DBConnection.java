import java.sql.*;

public class DBConnection {

    private static Connection connection;

    private static String dbName = "learn";
    private static String dbUser = "root";
    private static String dbPass = "Qwer1234";
    private static StringBuilder insertQueryVoters = new StringBuilder();
    private static StringBuilder insertQueryStations = new StringBuilder();
    private static boolean isBigFile = false;
    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass);
                connection.createStatement().execute("DROP TABLE IF EXISTS voter_count");
                connection.createStatement().execute("CREATE TABLE voter_count(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "name TINYTEXT NOT NULL, " +
                        "birthDate DATE NOT NULL, " +
                        "`count` INT NOT NULL, " +
                        "PRIMARY KEY(id) " +
                        (isBigFile? ")" : ", UNIQUE KEY name_date(name(50), birthDate))"));
                connection.createStatement().execute("DROP TABLE IF EXISTS station_work");
                connection.createStatement().execute("CREATE TABLE station_work(" +
                        "id INT NOT NULL AUTO_INCREMENT, " +
                        "station TINYTEXT NOT NULL, " +
                        "workTime TINYTEXT NOT NULL, " +
                        "PRIMARY KEY(id))");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }

    public static void executeMultiInsertVoters() throws SQLException {
        String sql = "INSERT INTO voter_count(name, birthDate, `count`) "
                + "VALUES" + insertQueryVoters.toString()
                + " ON DUPLICATE KEY UPDATE `count`=`count` + 1";
        DBConnection.getConnection().createStatement().execute(sql);
    }

    public static void executeMultiInsertStations() throws SQLException {
        String sql = "INSERT INTO station_work(station, workTime) "
                .concat("VALUES")
                .concat(insertQueryStations.toString());
        DBConnection.getConnection().createStatement().execute(sql);
    }

    public static void countVoter(String name, String birthDay) {
        birthDay = birthDay.replace('.', '-');

        insertQueryVoters.append(insertQueryVoters.length() == 0 ? "" : ",")
                .append("('")
                .append(name)
                .append("', '")
                .append(birthDay)
                .append("', 1)");
    }

    public static void stationWorkTime(String station, String workTime) {
        insertQueryStations.append(insertQueryStations.length() == 0 ? "" : ",")
                .append("('")
                .append(station)
                .append("', '")
                .append(workTime)
                .append("')");
    }

    public static void clearInsertQueryVoters(){
        insertQueryVoters.setLength(0);
    }

    public static void hasBigFileParse(){
        isBigFile = true;
    }
    public static void printVoterCounts() throws SQLException {
        String sql = "SELECT name, birthDate, `count` FROM voter_count WHERE `count` > 1";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t" + rs.getString("name") + " (" +
                    rs.getString("birthDate") + ") - " + rs.getInt("count"));
        }
        rs.close();
    }

    public static void printVoteStationTimeWorks() throws SQLException {
        String sql = "SELECT station, workTime FROM station_work " +
                "ORDER BY length(station), station";
        ResultSet rs = DBConnection.getConnection().createStatement().executeQuery(sql);
        while (rs.next()) {
            System.out.println("\t Station: " + rs.getString("station")
                    + ": " + rs.getString("workTime"));
        }
        rs.close();
    }
}
