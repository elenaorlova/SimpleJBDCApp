package db.school;

import java.sql.Date;
import java.util.Map;

public class Student extends Person{
    private int formId;

    public void setFormId(int formId) {
        this.formId = formId;
    }

    public int getFormId() {
        return this.formId;
    }
}
