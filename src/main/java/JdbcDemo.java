import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class JdbcDemo {
    private static final String DATABASE_URL = "jdbc:mysql://localhost/school?serverTimezone=UTC&useSSL=false";

    private static final String USER = "root";
    private static final String PASSWORD = "1122";

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



