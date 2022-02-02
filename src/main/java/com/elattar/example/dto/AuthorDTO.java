package com.elattar.example.dto;

import java.util.ArrayList;
import java.util.List;

public class AuthorDTO {

    private Long id;
    private String firstName;
    private String lastName;
    private String telephone;
    private String mail;
    private List<BookDTO> books = new ArrayList<>();

    public AuthorDTO() {
    }

    public AuthorDTO(Long id, String firstName, String lastName, String telephone, String mail, List<BookDTO> books) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.telephone = telephone;
        this.mail = mail;
        this.books = books;
    }

    //Getters and setters removed for simplicity

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public List<BookDTO> getBooks() {
        return books;
    }

    public void setBooks(List<BookDTO> books) {
        this.books = books;
    }

    @Override
    public String toString() {
        return "Author [authorId=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", telephone=" + telephone
                + ", mail=" + mail + "]";
    }
}