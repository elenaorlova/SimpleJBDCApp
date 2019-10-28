package dao;

import db.school.Student;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class StudentDaoTest {
    private Dao studentDao = new StudentDao();

    @Test
    void get() {
        int idToGet = 14;
        Student student = (Student) studentDao.get(idToGet);
        assertNotNull(student);
    }

    @Test
    void getAll() {
        List students = studentDao.getAll();
        assertNotNull(students);
    }

    @Test
    void insert() {
        assertTrue(studentDao.insert(new Student(
                "test",
                "test",
                "test")));
    }
}