import dao.Dao;
import dao.StudentDao;
import db.school.Student;

import java.util.List;

public class UsageExample {

    public static void main(String[] args) {
        Dao studentDao = new StudentDao();
        Student.verbose = false;
        int idToGet = 14;
        int idToDelete = 11;

        System.out.println("\nGetting student...");
        Student student = (Student) studentDao.get(idToGet);
        System.out.println(student.toString());
//        Student student1 = new Student("Petrov", "Vladislav", "Ilich");
//        System.out.println("\nInserting student...");
//        studentDao.insert(student1);

        System.out.println("\nGetting all students...");
        List students = studentDao.getAll();
        System.out.println("Number of students: " + students.size());
        students.forEach(std -> System.out.println(std.toString()));

        System.out.println("\nDeleting student by id...");
        if (!studentDao.delete(idToDelete))
            System.out.println("Can't delete the student with id = " + idToDelete);
        else
            System.out.println("Student with id = " + idToDelete + " was deleted.");

        System.out.println("\nGetting all students...");
        List new_students = studentDao.getAll();
        System.out.println("Number of students: " + new_students.size());
        new_students.forEach(std -> System.out.println(std.toString()));
    }
}
