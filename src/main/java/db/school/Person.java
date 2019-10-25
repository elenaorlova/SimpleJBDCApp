package db.school;

import java.sql.Date;

// todo: private protected or default ???

abstract class Person {
    int id;
    String lName;
    String fName;
    String mName;
    Date birthDate;
    public static boolean verbose = false;

    public int getId() {
        return this.id;
    }

    public String getLName() {
        return this.lName;
    }

    public void setLName(String mName) {
        this.lName = mName;
    }

    public String getFName() {
        return this.fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getMName() {
        return this.mName;
    }

    public void setMName(String name) {
        this.mName = name;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}
