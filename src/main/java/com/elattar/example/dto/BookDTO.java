package com.elattar.example.dto;

public class BookDTO {

    private Long id;
    private String title;
    private Integer year;
    private Double price;
    private Long authorId;

    public BookDTO() {
    }

    public BookDTO(Long id, String title, Integer year, Double price, Long authorId) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.price = price;
        this.authorId = authorId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getAuthorId() {
        return authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    @Override
    public String toString() {
        return "BooksResponse [bookId=" + id + ", title=" + title + ", year=" + year + ", price=" + price
                + ", authorId=" + authorId + "]";
    }
}