import java.sql.*;

public class JdbcDemo {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/school?serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "1122";

    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Connection connection = null;
        Statement statement = null;

        System.out.println("Creating database connection...");
        connection = DriverManager.getConnection(DATABASE_URL, USER, PASSWORD);

        System.out.println("Executing statement...");
        statement = connection.createStatement();

        String query;

        query = "SELECT * FROM teachers";

        ResultSet resultSet = statement.executeQuery(query);
        System.out.println("Retrieving data from database...");
        while (resultSet.next()) {
            String name = resultSet.getString("TeacherName");
            String fName = resultSet.getString("TeacherFName");
            String mName = resultSet.getString("TeacherMName");

            System.out.println("Teacher: " + fName + " " + name + " " + mName);
        }

        System.out.println("Closing all connections...");
        resultSet.close();
        statement.close();
        connection.close();
    }
}
