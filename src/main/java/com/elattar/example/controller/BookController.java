package com.elattar.example.controller;

import com.elattar.example.domain.Book;
import com.elattar.example.dto.BookDTO;
import com.elattar.example.dto.JsonResponseCreator;
import com.elattar.example.mapper.BookMapper;
import com.elattar.example.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/book")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public ResponseEntity<JsonResponseCreator<BookDTO>> search(@RequestParam(required = false) String filter, Pageable pageable) {
        Page<Book> pagedContent = bookService.search(filter, pageable);
        List<BookDTO> response = BookMapper.createBookResponseList(pagedContent.getContent());
        JsonResponseCreator<BookDTO> jsonResponseCreator = new JsonResponseCreator<>(pagedContent.getTotalElements(), pageable, response);
        return new ResponseEntity<>(jsonResponseCreator, HttpStatus.OK);
    }

    @GetMapping(value = "/{bookId}")
    public ResponseEntity<BookDTO> findOne(@PathVariable Long bookId) {
        Book book = bookService.findById(bookId);

        BookDTO response = BookMapper.createBookResponse(book);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<BookDTO> create(@RequestBody Book book) {
        bookService.save(book);

        BookDTO response = BookMapper.createBookResponse(book);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{bookId}")
    public ResponseEntity<BookDTO> update(@PathVariable Long bookId, @RequestBody Book book) {
        bookService.findById(bookId);

        book.setId(bookId);
        bookService.update(book);

        BookDTO response = BookMapper.createBookResponse(book);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{bookId}")
    @ResponseBody
    public ResponseEntity<BookDTO> delete(@PathVariable Long bookId) {
        bookService.findById(bookId);
        bookService.delete(bookId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}