package pl.pjatk.zsb.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Date;

@Entity
@Table(name = "books")
@ApiModel(value = "Book", description = "Book from DB")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "Book id",required = true)
    private Integer id;
    @ApiModelProperty(value = "Book title",required = true)
    private String title;
    @ApiModelProperty(value = "Book author",required = true)
    private String author;
    @ApiModelProperty(value = "Book genre",required = true)
    @Enumerated(EnumType.STRING)
    private Genres genre;
    @ApiModelProperty(value = "Book language",required = true)
    private String language;
    @ApiModelProperty(value = "Published in year",required = true)
    private Integer pubyear;
    @ApiModelProperty(value = "Book published by",required = true)
    private String publisher;
    @ApiModelProperty(value = "Id of current owner",required = true)
    private Integer owner_ID;
    @ApiModelProperty(value = "Id of current owner",required = true)
    private LocalDate beginning;
    @ApiModelProperty(value = "Id of current owner",required = true)
    private LocalDate end;


    public Book() {
    }

    public Book(Integer id, String title, String author, Genres genre, String language,
                Integer pubyear, String publisher, Integer owner_ID, LocalDate beginning,
                LocalDate end) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.language = language;
        this.pubyear = pubyear;
        this.publisher = publisher;
        this.owner_ID = owner_ID;
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

    public Integer getOwner_ID() {
        return owner_ID;
    }

    public void setOwner_ID(Integer owner_ID) {
        this.owner_ID = owner_ID;
    }

    public LocalDate getBeginning() {
        return beginning;
    }

    public void setBeginning(LocalDate beginning) {
        this.beginning = beginning;
    }

    public LocalDate getEnd() {
        return end;
    }

    public void setEnd(LocalDate end) {
        this.end = end;
    }
}
