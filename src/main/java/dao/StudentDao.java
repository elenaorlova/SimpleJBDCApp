package dao;

import db.school.Student;
import oracle.ucp.proxy.annotation.Pre;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

// todo: understand why getConnection() doesn't work

public class StudentDao implements Dao<Student> {
    private List<Student> students = new ArrayList<>();

    public StudentDao() { }

    private static final String USER = "root";
    private static final String PASSWORD = "1122";
    private static final String DB_URL = "jdbc:mysql://localhost/school?serverTimezone=UTC&useSSL=false";

    @Override
    public Student get(int id) {
//        Connection connection = ConnectionFactory.getConnection("C:\\Users\\Elena\\Documents" +
//                "\\GitHub\\SimpleJBDCApp" +
//                "\\src\\main\\resources\\db.properties");

        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet result_set = statement.executeQuery("SELECT * FROM student WHERE StudentId=" + id);) {
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

        student.setMName(result_set.getString("StudentMName"));
        student.setFName(result_set.getString("StudentFName"));
        student.setLName(result_set.getString("StudentLName"));
        student.setBirthDate(result_set.getDate("StudentBorn"));
        return student;
    }

    @Override
    public List<Student> getAll() {
        try (Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet result_set = statement.executeQuery("SELECT * FROM student")) {
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
             PreparedStatement prepared_statement = connection.prepareStatement("INSERT INTO student VALUES (NULL, ?, ?, ?, ?, ?)");) {
            prepared_statement.setString(1, student.getName());
            prepared_statement.setString(2, student.getMName());
            prepared_statement.setString(3, student.getFName());
            int i = prepared_statement.executeUpdate();

            if (i == 1)
                return true;
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public void update(Student student, String[] params) {
        student.setMName(Objects.requireNonNull(params[0], "Name cannot be null"));
        student.setFName(Objects.requireNonNull(params[1], "First Name cannot be null"));
        students.add(student);
    }

    @Override
    public void delete(Student student) {
        students.remove(student);
    }
}
