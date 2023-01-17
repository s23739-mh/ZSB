package pl.pjatk.zsb.domain;

import javax.persistence.*;

@Entity
@Table(name = "favourites")
public class Favourite {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private Integer id_book;
    private String mail_user;

    public Favourite() {
    }

    public Favourite(Integer id, Integer id_book, String mail_user) {
        this.id = id;
        this.id_book = id_book;
        this.mail_user = mail_user;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId_book() {
        return id_book;
    }

    public void setId_book(Integer id_book) {
        this.id_book = id_book;
    }

    public String getMail_user() {
        return mail_user;
    }

    public void setMail_user(String mail_user) {
        this.mail_user = mail_user;
    }
}
