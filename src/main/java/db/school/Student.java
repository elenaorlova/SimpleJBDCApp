package db.school;

public class Student extends Person {
    private int formId;

    public Student() { }

    public Student(String lName, String fName, String mName) {
        this.lName = lName;
        this.fName = fName;
        this.mName = mName;

        if (verbose)
            System.out.println(this.toString() + " constructed");
    }

    public int getFormId() {
        return this.formId;
    }

    public void setFormId(int formId) {
        this.formId = formId;
    }

    @Override
    public String toString() {
        return "Student: { Last name: " + this.lName + " First name: " + this.fName + " Middle name: " + this.mName
                + " }";

    }
}
