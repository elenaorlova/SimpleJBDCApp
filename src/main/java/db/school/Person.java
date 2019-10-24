package db.school;

import java.sql.Date;

abstract class Person {
    int id;
    String name;
    String fName;
    String mName;
    Date birthDate;

    public void setMName(String name) {
        this.name = name;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public void setLName(String mName) {
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
