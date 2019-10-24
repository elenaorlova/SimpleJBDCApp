package dao;

import db.school.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class StudentDao implements Dao<Student> {
    private List<Student> students = new ArrayList<>();

    public StudentDao() {
        students.add(new Student("Valeria", "1"));
        students.add(new Student("Oleg", "2"));
    }

    @Override
    public Optional<Student> get(int id) {
        return Optional.ofNullable(students.get(id));
    }

    @Override
    public List<Student> getAll() {
        return students;
    }

    @Override
    public void save(Student student) {
        students.add(student);
    }

    @Override
    public void update(Student student, String[] params) {
        student.setName(Objects.requireNonNull(params[0], "Name cannot be null"));
        student.setFName(Objects.requireNonNull(params[1], "First Name cannot be null"));
        students.add(student);
    }

    @Override
    public void delete(Student student) {
        students.remove(student);
    }
}
