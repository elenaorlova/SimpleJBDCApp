package db.school;

public class Teacher extends Person {
    private int titleId;

    public void setTitleId(int titleId) {
        this.titleId = titleId;
    }

    public int getTitleId() {
        return this.titleId;
    }
}
