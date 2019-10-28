package db.school;

import java.sql.Date;

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

    public void setId(int id) {
        this.id = id;
    }

    public String getLName() {
        return this.lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
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

    public void setMName(String mName) {
        this.mName = mName;
    }

    public Date getBirthDate() {
        return this.birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Override
    public String toString() {
        return "{ Last name: " + this.lName + "\nFirst name: " + this.fName + "\nMiddle name: " + this.mName
                + "\n ID: " + this.id + " }";
    }
}
