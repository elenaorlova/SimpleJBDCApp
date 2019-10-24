package db.school;

public class Teacher extends Person {
    private int titleId;

    public Teacher(String name, String fName, String mName) {
        this.name = name;
        this.fName = fName;
        this.mName = mName;
    }

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public int getTitleId() {
        return this.titleId;
    }
}
