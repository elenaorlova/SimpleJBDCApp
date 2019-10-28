package db.school;

public class Student extends Person {
    private int formId;

    public Student() { }

    public Student(String lName, String fName, String mName) {
        this.lName = lName;
        this.fName = fName;
        this.mName = mName;

        System.out.println(lName + fName + mName);

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
        return "Student:\n{ Last name: " + this.lName
                + "\nFirst name: " + this.fName
                + "\nMiddle name: " + this.mName
                + "\nID: " + this.id + " }";
    }
}
