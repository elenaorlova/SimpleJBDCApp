package db.school;

import java.sql.Date;
import java.util.Map;

public class Student extends Person{
    private int formId;

    public Student(String name, String fName) {
        this.name = name;
        this.fName = fName;
        System.out.println("Student: " + this.name + " " + this.fName + " constructed");
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public int getFormId() {
        return this.formId;
    }
}
