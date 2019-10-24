package db.school;

import java.sql.Date;

abstract class Person {
    private int id;
    private String name;
    private String fName;
    private String mName;
    private Date birthDate;

    public void setName(String name) {
        this.name = name;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public void setMName(String mName) {
        this.mName = mName;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getName() {
        return this.name;
    }

    public String getFName() {
        return this.fName;
    }

    public String getMName() {
        return this.mName;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }
}
