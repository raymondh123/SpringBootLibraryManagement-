package com.example.librarymanagement.service;
import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.repository.AuthorRepository;
import com.example.librarymanagement.exception.ResourceNotFoundException;
import com.example.librarymanagement.dto.BookResponseDto;
import java.util.stream.Collectors;
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
    public List<BookResponseDto> getBooksByAuthor(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Author not found with id " + id));

        return author.getBooks().stream()
                .map(book -> new BookResponseDto(
                        book.getId(),
                        book.getTitle(),
                        book.getIsbn(),
                        book.getCategory(),
                        author.getName(),
                        book.isAvailable()
                ))
                .collect(Collectors.toList());
    }
    public Author saveAuthor(Author author) {
        return authorRepository.save(author);
    }
    public void deleteAuthor(Long id) {
        authorRepository.deleteById(id);
    }
    public Author updateAuthor(Long id, Author updatedAuthor) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Author not found with id " + id));

        author.setName(updatedAuthor.getName());
        author.setBiography(updatedAuthor.getBiography());

        return authorRepository.save(author);
    }
}
