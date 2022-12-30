package pl.pjatk.zsb.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import javax.persistence.*;

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
    @ApiModelProperty(value = "Is book available",required = true)
    private Boolean isAvailable;

    public Book(Integer id, String title, String author, Genres genre, String language,
                Integer pubyear, String publisher, Boolean isAvailable) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.language = language;
        this.pubyear = pubyear;
        this.publisher = publisher;
        this.isAvailable = isAvailable;
    }

    public Book() {
    }

    public Book(Integer id, String title, String author, Genres genre) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
    }

    public Integer getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Enum getGenre() {
        return genre;
    }

    public Boolean getAvailable() {
        return isAvailable;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String description) {
        this.author = description;
    }

    public void setGenre(Genres genre) {
        this.genre = genre;
    }

    public void setAvailable(Boolean available) {
        isAvailable = available;
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
}
