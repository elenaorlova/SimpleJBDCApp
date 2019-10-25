package dao;

import db.school.Student;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDao implements Dao<Student> {
    private List<Student> students = new ArrayList<>();

    public StudentDao() { }

    private static final String USER = "root";
    private static final String PASSWORD = "1122";
    private static final String DB_URL = "jdbc:mysql://localhost/school?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

    @Override
    public Student get(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet result_set = statement.executeQuery("SELECT * FROM student WHERE StudentId=" + id)
        ) {
            if (result_set.next()) {
                return getFromResultSet(result_set);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Student getFromResultSet(ResultSet result_set) throws SQLException {
        Student student = new Student();

        student.setLName(result_set.getString("StudentLName"));
        student.setFName(result_set.getString("StudentFName"));
        student.setMName(result_set.getString("StudentMName"));
        student.setBirthDate(result_set.getDate("StudentBorn"));
        student.setId(result_set.getInt("StudentID"));
        return student;
    }

    @Override
    public List<Student> getAll() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet result_set = statement.executeQuery("SELECT * FROM student")) {
            if (students.size() > 0)
                students.removeAll(students);
            while (result_set.next()) {
                Student student = getFromResultSet(result_set);
                students.add(student);
            }

            return students;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public boolean insert(Student student) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement prepared_statement = connection.prepareStatement("INSERT INTO student " +
                     "VALUES (NULL, ?, ?, ?, ?, ?)")) {
            prepared_statement.setString(1, student.getLName());
            prepared_statement.setString(2, student.getFName());
            prepared_statement.setString(3, student.getMName());

            prepared_statement.setDate(4, new Date(111));
            prepared_statement.setInt(5, 1);

            int i = prepared_statement.executeUpdate();
            if (i == 1)
                return true;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean update(Student student, String[] params) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             PreparedStatement prepared_statement = connection.prepareStatement("UPDATE student SET StudentMName=?, " +
                     "StudentFName=?, StudentLName=?, StudentBorn=?")) {
            prepared_statement.setString(1, student.getLName());
            prepared_statement.setString(3, student.getFName());
            prepared_statement.setString(2, student.getMName());

            int i = prepared_statement.executeUpdate();
            if (i == 1)
                return true;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean delete(int id) {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement()) {
                int i = statement.executeUpdate("DELETE FROM student WHERE StudentId=" + id);
                if (i == 1)
                    return true;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }
}
