package com.elattar.example.mapper;

import com.elattar.example.domain.Book;
import com.elattar.example.dto.BookDTO;

import java.util.ArrayList;
import java.util.List;

public class BookMapper {
    public static BookDTO createBookResponse(Book book) {
        BookDTO response = new BookDTO();
        response.setId(book.getId());
        response.setPrice(book.getPrice());
        response.setTitle(book.getTitle());
        response.setYear(book.getYear());
        response.setAuthorId(book.getAuthor().getId());

        return response;
    }

    public static List<BookDTO> createBookResponseList(List<Book> books) {
        List<BookDTO> response = new ArrayList<>();
        for (Book book : books)
            response.add(createBookResponse(book));

        return response;
    }
}
