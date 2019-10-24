import org.jetbrains.annotations.NotNull;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JdbcDemo {
    private static String dbUrl;
    private static String dbUser;
    private static String dbPassword;

    public static void getDataFromTeachersTable(@NotNull Statement statement, String query)
            throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        Map<String, String> teacherInfo = new HashMap<>();

        print("\nRetrieving data from database...");
        while (resultSet.next()) {
            teacherInfo.put("name", resultSet.getString("TeacherName"));
            teacherInfo.put("fName", resultSet.getString("TeacherFName"));
            teacherInfo.put("mName", resultSet.getString("TeacherMName"));

            System.out.println("Teacher: "
                    + teacherInfo.get("fName")
                    + " " + teacherInfo.get("name")
                    + " " + teacherInfo.get("mName"));
        }
        resultSet.close();
        System.out.println();
    }

    public static void addTeacher(@NotNull Statement statement, String query)
            throws SQLException {
        System.out.println("Adding record...");
        int rowsAffected = statement.executeUpdate(query);
        print("Updated items: " + rowsAffected);
    }

    public static void renameTeacher(@NotNull Statement statement, String oldName, String newName)
            throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM teachers");
        while (resultSet.next()) {
            if (oldName.equals(resultSet.getString("TeacherName"))) {
                print("Renaming teacher...");
                resultSet.updateString("TeacherName", newName);
                resultSet.updateRow();
                break ;
            }
        }
        resultSet.close();
    }

    public static void deleteTeacher(@NotNull Statement statement, String name, String fName)
            throws SQLException {
        print("Deleting " + name + " " + fName);
        int rowsAffected = statement.executeUpdate(
                "DELETE FROM teachers " +
                "WHERE TeacherName=\'"
                + name + "\' AND TeacherFName=\'" + fName + "\'");
        print("Successfully deleted " + rowsAffected);
    }

    public static Map<String, PreparedStatement> createPreparedStatementList(Connection connection)
            throws SQLException {
        Map<String, PreparedStatement> statements = new HashMap<>();
        statements.put("deleteStatement", connection.prepareStatement(
                "DELETE FROM teachers "
                + "WHERE TeacherName=?"
                + " AND TeacherFName=?"));
        statements.put("selectStatement", connection.prepareStatement(
                "SELECT * FROM ?"
        ));

        //statements.get("deleteStatement").setString(1, "Anastasia");
        // statements.get("deleteStatement").setString(2, "Osipova");
        //ResultSet res = statements.get("deleteStatement").executeQuery();

        statements.put("insertStatement", connection.prepareStatement(
                "INSERT INTO `school`.`teachers` " +
                   "(`TeacherName`, `TeacherFName`, `TeacherMName`," +
                   " `TeacherBorn`, `TeacherSex`, `TitleID`) " +
                   "VALUES (?, ?, ?, ?, ?, ?);"
        ));
        return statements;
    }

    private static void print(String str) {
        System.out.println(str);
    }

    public static void main(String[] args) {
        Properties properties = new Properties();
        try {
            String PROPERTIES_PATH = "C:\\Users\\Elena\\Documents" +
                    "\\GitHub\\SimpleJBDCApp" +
                    "\\src\\main\\resources\\db.properties";
            properties.load(new FileInputStream(PROPERTIES_PATH));
            dbUser = properties.getProperty("user");
            dbPassword = properties.getProperty("password");
            dbUrl = properties.getProperty("dburl");
            print("User " + dbUser + " is connecting to " + dbUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        print("Creating database connection...");

        try (Connection connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
             Statement statement = connection.createStatement(
                     ResultSet.TYPE_FORWARD_ONLY,
                     ResultSet.CONCUR_UPDATABLE
             )) {

            try {
                String querySelect = "SELECT * FROM teachers";
                String queryInsert = "INSERT INTO `school`.`teachers` " +
                        "(`TeacherName`, `TeacherFName`, `TeacherMName`," +
                        " `TeacherBorn`, `TeacherSex`, `TitleID`) " +
                        "VALUES ('Vladislav', 'Smirnov', 'Ivanovich', '1989-10-14', 'M', '1');";

                //addTeacher(statement, queryInsert);
                getDataFromTeachersTable(statement, querySelect);
                //renameTeacher(statement, "Vladislav", "Oleg");
                deleteTeacher(statement, "Valislav", "Smirnov");
                getDataFromTeachersTable(statement, querySelect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



