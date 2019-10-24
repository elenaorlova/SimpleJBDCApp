package dao;

import db.school.Student;

public class TestDao {
    private static Dao studentDao;
    public static void main(String[] args) {
        studentDao = new StudentDao();

        Student student = (Student) studentDao.get(5);
        System.out.println(student.toString());
    }
}
