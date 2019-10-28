package db.school;

public class Teacher extends Person {
    private int titleId;

    public Teacher() {}

    public Teacher(String lName, String fName, String mName) {
        this.lName = lName;
        this.fName = fName;
        this.mName = mName;

        if (verbose)
            System.out.println(this.toString() + " constructed");
    }

    public int getTitleId() {
        return this.titleId;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    @Override
    public String toString() {
        return "Student:\n{ Last name: " + this.lName
                + "\nFirst name: " + this.fName
                + "\nMiddle name: " + this.mName
                + "\nID: " + this.id + " }";
    }
}
