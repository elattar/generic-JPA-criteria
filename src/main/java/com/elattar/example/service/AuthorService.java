package com.elattar.example.service;

import com.elattar.example.domain.Author;
import com.elattar.example.exception.ObjectNotFoundException;
import com.elattar.example.repository.AuthorRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {

    private final AuthorRepository authorRepository;

    private final FilterService<Author> filterService;

    public AuthorService(AuthorRepository authorRepository, FilterService<Author> filterService) {
        this.authorRepository = authorRepository;
        this.filterService = filterService;
    }

    public Author findById(Long authorId) {
        Optional<Author> authorOptional = authorRepository.findById(authorId);
        if (authorOptional.isEmpty())
            throw new ObjectNotFoundException("Could not find author with id '" + authorId + "'");
        return authorOptional.get();
    }

    public List<Author> findAll() {
        return this.authorRepository.findAll();
    }

    public Page<Author> findAll(Pageable page) {
        return this.authorRepository.findAll(page);
    }

    public Page<Author> findAll(Specification<Author> specification, Pageable page) {
        return this.authorRepository.findAll(specification, page);
    }

    public void save(Author author) {
        this.authorRepository.save(author);
    }

    public void update(Author author) {
        Author entity = findById(author.getId());

        if (author.getLastName() == null)
            author.setLastName(entity.getLastName());
        if (author.getMail() == null)
            author.setMail(entity.getMail());
        if (author.getFirstName() == null)
            author.setFirstName(entity.getFirstName());
        if (author.getTelephone() == null)
            author.setTelephone(entity.getTelephone());

        this.save(author);
    }

    public void delete(Long authorId) {
        Author author = findById(authorId);
        this.authorRepository.delete(author);
    }

    public Page<Author> search(String filter, Pageable pageable) {
        return filterService.search(filter, pageable, Author.class);
    }
}