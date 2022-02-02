package com.elattar.example.mapper;

import com.elattar.example.domain.Author;
import com.elattar.example.dto.AuthorDTO;

import java.util.ArrayList;
import java.util.List;

public class AuthorMapper {

    public static AuthorDTO createAuthorResponse(Author author) {
        AuthorDTO response = new AuthorDTO();
        response.setId(author.getId());
        response.setFirstName(author.getFirstName());
        response.setLastName(author.getLastName());
        response.setMail(author.getMail());
        response.setTelephone(author.getTelephone());
        response.setBooks(BookMapper.createBookResponseList(author.getBooks()));

        return response;
    }

    public static List<AuthorDTO> createAuthorResponseList(List<Author> authors) {
        List<AuthorDTO> response = new ArrayList<>();
        for (Author author : authors)
            response.add(createAuthorResponse(author));

        return response;
    }


}
