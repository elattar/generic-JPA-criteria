package com.elattar.example.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 60)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 60)
    private String lastName;

    @Column(name = "telephone", nullable = false, length = 60)
    private String telephone;

    @Column(name = "mail", nullable = false, length = 60)
    private String mail;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "author", cascade = CascadeType.ALL)
    private List<Book> books = new ArrayList<>();

    public Author() {
    }

    public Author(Long id, String firstName, String lastName, String telephone, String mail, List<Book> books) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.mail = mail;
        this.books = books;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long authorId) {
        this.id = authorId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author [authorId=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", telephone=" + telephone
                + ", mail=" + mail + "]";
    }
}