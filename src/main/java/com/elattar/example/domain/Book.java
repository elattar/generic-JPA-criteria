package com.elattar.example.domain;

import javax.persistence.*;

@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id_book", unique = true, nullable = false)
    private Long id;

    @Column(name = "title", nullable = false, length = 60)
    private String title;

    @Column(name = "year", nullable = false, length = 4)
    private Integer year;

    @Column(name = "price", nullable = false, length = 10)
    private Double price;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_author", nullable = false)
    private Author author;

    public Book() {
    }

    public Book(Long id, String title, Integer year, Double price, Author author) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.price = price;
        this.author = author;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long bookId) {
        this.id = bookId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Author getAuthor() {
        return author;
    }

    public void setAuthor(Author author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return "Books [bookId=" + id + ", title=" + title + ", year=" + year + ", price=" + price + "]";
    }
}