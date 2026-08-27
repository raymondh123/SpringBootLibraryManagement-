package com.example.librarymanagement.controller;
import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.service.AuthorService;
import com.example.librarymanagement.dto.AuthorRequestDto;
import com.example.librarymanagement.dto.AuthorResponseDto;
import com.example.librarymanagement.dto.BookResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/authors")
public class AuthorController {
    private final AuthorService authorService;

    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }
    @GetMapping
    public List<AuthorResponseDto> getAllAuthors() {
        return authorService.getAllAuthors().stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> getAuthorById(@PathVariable Long id) {
        Author author = authorService.getAuthorById(id);
            return  ResponseEntity.ok(convertToDto(author));
    }
    @PostMapping
    public ResponseEntity<AuthorResponseDto> createAuthor(@Valid @RequestBody AuthorRequestDto requestDto) {
        Author author = new Author();
        author.setName(requestDto.getName());
        author.setBiography(requestDto.getBiography());

        Author savedAuthor = authorService.saveAuthor(author);
        return new ResponseEntity<>(convertToDto(savedAuthor), HttpStatus.CREATED);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAuthor(@PathVariable Long id) {
        authorService.deleteAuthor(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
    private AuthorResponseDto convertToDto(Author author) {
        return new AuthorResponseDto(
                author.getId(),
                author.getName()
        );
    }
    @PutMapping("/{id}")
    public ResponseEntity<AuthorResponseDto> updateAuthor(
            @PathVariable Long id,
            @Valid @RequestBody AuthorRequestDto requestDto) {

        Author author = new Author();
        author.setName(requestDto.getName());
        author.setBiography(requestDto.getBiography());

        Author updatedAuthor = authorService.updateAuthor(id, author);

        return ResponseEntity.ok(convertToDto(updatedAuthor));
    }
    @GetMapping("/{id}/books")
    public ResponseEntity<List<BookResponseDto>> getBooksByAuthor(
            @PathVariable Long id) {

        return ResponseEntity.ok(authorService.getBooksByAuthor(id));
    }
}
