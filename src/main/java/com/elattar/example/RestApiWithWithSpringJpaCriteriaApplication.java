package com.elattar.example;

import com.elattar.example.domain.Author;
import com.elattar.example.domain.Book;
import com.elattar.example.service.AuthorService;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class RestApiWithWithSpringJpaCriteriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(RestApiWithWithSpringJpaCriteriaApplication.class, args);
    }

    @Bean
    InitializingBean authorInitializer(final AuthorService authorService) {

        return () -> {
            List<Book> books = new ArrayList<>();
            Book book1 = new Book();
            Book book2 = new Book();
            Book book3 = new Book();

            // save first record
            Author elattar = new Author();
            elattar.setFirstName("Abdelrahman");
            elattar.setLastName("Elattar");
            elattar.setMail("abdelrahman@gmail.com");
            elattar.setTelephone("0100123");

            book1.setTitle("Java Applications");
            book1.setPrice(10.0);
            book1.setYear(2010);
            book1.setAuthor(elattar);
            books.add(book1);

            book2.setTitle("Java Design Pattern");
            book2.setPrice(23.50);
            book2.setYear(2020);
            book2.setAuthor(elattar);
            books.add(book2);

            book3.setTitle("Java OOP");
            book3.setPrice(20.65);
            book3.setYear(2015);
            book3.setAuthor(elattar);
            books.add(book3);

            elattar.setBooks(books);
            authorService.save(elattar);

            // save second record
            books = new ArrayList<>();
            book1 = new Book();
            book2 = new Book();
            book3 = new Book();

            Author aly = new Author();
            aly.setFirstName("Ahmed");
            aly.setLastName("Aly");
            aly.setMail("Ahmed@hotmail.com");
            aly.setTelephone("0120145");

            book1.setTitle("C++ Application");
            book1.setPrice(15.5);
            book1.setYear(2017);
            book1.setAuthor(aly);
            books.add(book1);

            book2.setTitle("C++ Algorithms");
            book2.setPrice(19.76);
            book2.setYear(2000);
            book2.setAuthor(aly);
            books.add(book2);

            book3.setTitle("C++ Patterns");
            book3.setPrice(5.60);
            book3.setYear(2019);
            book3.setAuthor(aly);
            books.add(book3);

            aly.setBooks(books);
            authorService.save(aly);

            // save third record
            books = new ArrayList<>();
            book1 = new Book();
            book2 = new Book();
            book3 = new Book();

            Author amr = new Author();
            amr.setFirstName("Amr");
            amr.setLastName("Ahmed");
            amr.setMail("Amr@yahoo.com");
            amr.setTelephone("0150155");

            book1.setTitle("Python programming");
            book1.setPrice(9.00);
            book1.setYear(2010);
            book1.setAuthor(amr);
            books.add(book1);

            book2.setTitle("Python Developer");
            book2.setPrice(8.56);
            book2.setYear(1993);
            book2.setAuthor(amr);
            books.add(book2);

            book3.setTitle("Python Design pattern");
            book3.setPrice(14.25);
            book3.setYear(1985);
            book3.setAuthor(amr);
            books.add(book3);

            amr.setBooks(books);
            authorService.save(amr);
        };
    }
}
