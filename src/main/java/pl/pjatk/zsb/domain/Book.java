package pl.pjatk.zsb.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "books")
@ApiModel(value = "Book", description = "Book from DB")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Book id", required = true)
    private Integer id;
    @ApiModelProperty(value = "Book title", required = true)
    private String title;
    @ApiModelProperty(value = "Book author", required = true)
    private String author;
    @ApiModelProperty(value = "Book genre", required = true)
    @Enumerated(EnumType.STRING)
    private Genres genre;
    @ApiModelProperty(value = "Book language", required = true)
    private String language;
    @ApiModelProperty(value = "Published in year", required = true)
    private Integer pubyear;
    @ApiModelProperty(value = "Book published by", required = true)
    private String publisher;
    @ApiModelProperty(value = "E-mail adress of current owner", required = true)
    private String owner_mail;
    @ApiModelProperty(value = "Reservation date", required = true)
    private Date beginning;
    @ApiModelProperty(value = "Reservation end date", required = true)
    private Date end;


    public Book() {
    }

    public Book(Integer id, String title, String author, Genres genre, String language,
                Integer pubyear, String publisher, String owner_mail, Date beginning,
                Date end) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.language = language;
        this.pubyear = pubyear;
        this.publisher = publisher;
        this.owner_mail = owner_mail;
        this.beginning = beginning;
        this.end = end;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Genres getGenre() {
        return genre;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public Integer getPubyear() {
        return pubyear;
    }

    public void setPubyear(Integer pubyear) {
        this.pubyear = pubyear;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getOwner_mail() {
        return owner_mail;
    }

    public void setOwner_mail(String owner_mail) {
        this.owner_mail = owner_mail;
    }

    public Date getBeginning() {
        return beginning;
    }

    public void setBeginning(Date beginning) {
        this.beginning = beginning;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }


    public void setAvailable(boolean b) {
        if (b) {
            this.owner_mail = null;
            this.beginning = null;
            this.end = null;
        }
    }

    public boolean isAvailable() {
        if (beginning == null && end == null) {
            return true;
        } else {
            return false;
        }
    }
}
