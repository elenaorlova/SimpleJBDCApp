package db.school;

import java.sql.Date;

public class Form {
    private int id;
    private Date creationDate;
    private String letter;

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLetter() {
        return this.letter;
    }

    public void setLetter(String letter) {
        this.letter = letter;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
}
