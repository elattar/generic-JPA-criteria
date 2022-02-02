package com.elattar.example.controller;

import com.elattar.example.domain.Author;
import com.elattar.example.dto.AuthorDTO;
import com.elattar.example.dto.JsonResponseCreator;
import com.elattar.example.mapper.AuthorMapper;
import com.elattar.example.service.AuthorService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/author")
public class AuthorController {

    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping
    public ResponseEntity<JsonResponseCreator<AuthorDTO>> search(@RequestParam(required = false) String filter, Pageable pageable) {
        Page<Author> pagedContent = authorService.search(filter, pageable);
        List<AuthorDTO> response = AuthorMapper.createAuthorResponseList(pagedContent.getContent());
        JsonResponseCreator<AuthorDTO> jsonResponseCreator = new JsonResponseCreator<>(pagedContent.getTotalElements(), pageable, response);
        return new ResponseEntity<>(jsonResponseCreator, HttpStatus.OK);
    }

    @GetMapping(value = "/{authorId}")
    public ResponseEntity<AuthorDTO> findOne(@PathVariable Long authorId) {
        Author author = authorService.findById(authorId);

        AuthorDTO response = AuthorMapper.createAuthorResponse(author);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<AuthorDTO> create(@RequestBody Author author) {
        authorService.save(author);

        AuthorDTO response = AuthorMapper.createAuthorResponse(author);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping(value = "/{authorId}")
    public ResponseEntity<AuthorDTO> update(@PathVariable Long authorId, @RequestBody Author author) {
        authorService.findById(authorId);

        author.setId(authorId);
        authorService.update(author);

        AuthorDTO response = AuthorMapper.createAuthorResponse(author);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    @DeleteMapping(value = "/{authorId}")
    @ResponseBody
    public ResponseEntity<AuthorDTO> delete(@PathVariable Long authorId) {
        authorService.findById(authorId);
        authorService.delete(authorId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}