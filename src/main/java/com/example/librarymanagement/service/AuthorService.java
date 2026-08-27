package com.example.librarymanagement.service;
import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.repository.AuthorRepository;
import com.example.librarymanagement.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class AuthorService {
    private final AuthorRepository authorRepository;

    public AuthorService(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    public List<Author> getAllAuthors() {
        return authorRepository.findAll();
    }
    public Author getAuthorById(Long id) {
        return authorRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Author not found with id " + id));
    }
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
}
