package com.example.librarymanagement.controller;
import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.service.AuthorService;
import com.example.librarymanagement.dto.AuthorRequestDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping
    public List<Author> getAllAuthors() {
        return authorService.getAllAuthors();
    }
    @GetMapping("/{id}")
    public ResponseEntity<Author> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
        if (author == null) {
            return new ResponseEntity<> (HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(author, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Author> createAuthor(@Valid @RequestBody AuthorRequestDto requestDto) {
        Author author = new Author();
        author.setName(requestDto.getName());
        author.setBiography(requestDto.getBiography());

        Author savedAuthor = authorService.saveAuthor(author);
        return new ResponseEntity<>(savedAuthor, HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
