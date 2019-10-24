package dao;

import db.school.Student;

public class TestDao {
    private static Dao studentDao;
    public static void main(String[] args) {
        studentDao = new StudentDao();

        Student student = new Student("Masha", "3");
        //studentDao.getAll().forEach(students -> System.out.println(student.getName() + " " + student.getFName()));
    }
}
