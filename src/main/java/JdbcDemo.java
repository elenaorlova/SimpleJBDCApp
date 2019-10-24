import java.sql.*;

public class JdbcDemo {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/school?serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "1122";

    private static void getDataFromTeachersTable(Statement statement, String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("\nRetrieving data from database...");
        while (resultSet.next()) {
            String name = resultSet.getString("TeacherName");
            String fName = resultSet.getString("TeacherFName");
            String mName = resultSet.getString("TeacherMName");

            System.out.println("Teacher: " + fName + " " + name + " " + mName);
        }
        System.out.println();
    }

    private static void addRecordToTeachersTable(Statement statement, String query) throws SQLException {
        System.out.println("Adding record...");
        statement.executeUpdate(query);
    }

    public static void main(String[] args) {
        System.out.println("Creating database connection...");

        try (Connection connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);
             Statement statement = connection.createStatement(
                     ResultSet.TYPE_FORWARD_ONLY,
                     ResultSet.CONCUR_UPDATABLE
             )) {

            try {
                String querySelect = "SELECT * FROM teachers";

                getDataFromTeachersTable(statement, querySelect);
                String queryInsert = "INSERT INTO `school`.`teachers` (`TeacherName`, `TeacherFName`, `TeacherMName`, `TeacherBorn`, `TeacherSex`, `TitleID`) VALUES ('Valislav', 'Smirnov', 'Ivanovich', '1989-10-14', 'M', '1');";
                addRecordToTeachersTable(statement, queryInsert);
                getDataFromTeachersTable(statement, querySelect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("Closing all connections...");
    }
}



