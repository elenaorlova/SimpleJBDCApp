package db.school;

import java.sql.Date;

public class Form {
    private int id;
    private Date creationDate;
    private String letter;

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return this.id;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public String getLetter() {
        return this.letter;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }
}
