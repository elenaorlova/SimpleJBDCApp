import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class JdbcDemo {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/school?serverTimezone=UTC";

    private static final String USER = "root";
    private static final String PASSWORD = "1122";

    private static void getDataFromTeachersTable(@NotNull Statement statement, String query) throws SQLException {
        ResultSet resultSet = statement.executeQuery(query);
        Map<String, String> teacherInfo = new HashMap<>();

        System.out.println("\nRetrieving data from database...");
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

    private static void addRecordToTeachersTable(@NotNull Statement statement, String query) throws SQLException {
        System.out.println("Adding record...");
        statement.executeUpdate(query);
    }

    private static void renameTeacher(@NotNull Statement statement, String oldName, String newName) throws SQLException {
        ResultSet resultSet = statement.executeQuery("SELECT * FROM teachers");
        while (resultSet.next()) {
            if (oldName.equals(resultSet.getString("TeacherName"))) {
                System.out.println("Renaming teacher...");
                resultSet.updateString("TeacherName", newName);
                resultSet.updateRow();
                break ;
            }
        }
        resultSet.close();
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
                String queryInsert = "INSERT INTO `school`.`teachers` " +
                        "(`TeacherName`, `TeacherFName`, `TeacherMName`," +
                        " `TeacherBorn`, `TeacherSex`, `TitleID`) " +
                        "VALUES ('Vladislav', 'Smirnov', 'Ivanovich', '1989-10-14', 'M', '1');";

                addRecordToTeachersTable(statement, queryInsert);
                getDataFromTeachersTable(statement, querySelect);
                renameTeacher(statement, "Vladislav", "Oleg");
                getDataFromTeachersTable(statement, querySelect);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}



