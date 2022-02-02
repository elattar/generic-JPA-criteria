package com.elattar.example.service;

import com.elattar.example.domain.Book;
import com.elattar.example.exception.ObjectNotFoundException;
import com.elattar.example.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;

    private final FilterService<Book> filterService;

    public BookService(BookRepository bookRepository, FilterService<Book> filterService) {
        this.bookRepository = bookRepository;
        this.filterService = filterService;
    }

    public Book findById(Long bookId) {
        Optional<Book> bookOptional = bookRepository.findById(bookId);
        if (bookOptional.isEmpty())
            throw new ObjectNotFoundException("Could not find book with id '" + bookId + "'");
        return bookOptional.get();
    }

    public List<Book> findAll() {
        return this.bookRepository.findAll();
    }

    public Page<Book> findAll(Pageable page) {
        return this.bookRepository.findAll(page);
    }

    public Page<Book> findAll(Specification<Book> specification, Pageable page) {
        return this.bookRepository.findAll(specification, page);
    }

    public void save(Book book) {
        this.bookRepository.save(book);
    }

    public void update(Book book) {
        Book entity = findById(book.getId());

        if (book.getTitle() == null)
            book.setTitle(entity.getTitle());
        if (book.getPrice() == null)
            book.setPrice(entity.getPrice());
        if (book.getYear() == null)
            book.setYear(entity.getYear());
    }

    public void delete(Long bookId) {
        Book book = this.findById(bookId);
        this.bookRepository.delete(book);
    }

    public Page<Book> search(String filter, Pageable pageable) {
        return filterService.search(filter, pageable, Book.class);
    }

}