package pl.pjatk.zsb.domain;

import java.util.Date;

public class RentDTO {
    private String mail;
    private Date beginning;
    private int days;

    public RentDTO(String mail, Date beginning, int days) {
        this.mail = mail;
        this.beginning = beginning;
        this.days = days;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public Date getBeginning() {
        return beginning;
    }

    public void setBeginning(Date beginning) {
        this.beginning = beginning;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }
}