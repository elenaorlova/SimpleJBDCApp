package dao;

import db.school.Student;

public class TestDao {
    private static Dao studentDao;
    public static void main(String[] args) {
        studentDao = new StudentDao();

        studentDao.getAll();
    }
}
