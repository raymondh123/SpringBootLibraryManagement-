package com.example.librarymanagement.service;
import com.example.librarymanagement.dto.BookRequestDto;
import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.BookCategory;
import com.example.librarymanagement.repository.AuthorRepository;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.dto.BookResponseDto;
import com.example.librarymanagement.exception.ResourceNotFoundException;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private final OpenLibraryService openLibraryService;

    public BookService(BookRepository bookRepository, AuthorRepository authorRepository, OpenLibraryService openLibraryService) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.openLibraryService = openLibraryService;

    }
    public List<BookResponseDto> getAllBooks() {
        return bookRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    public BookResponseDto getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id));

        return convertToDto(book);
    }

    public BookResponseDto addBook(BookRequestDto dto){
        String fetchedAuthorName = openLibraryService.getAuthorNameByIsbn(dto.getIsbn());
        Author author = authorRepository.findByName(fetchedAuthorName)
                .orElseGet(() -> {
                    Author newAuthor = new Author();
                    newAuthor.setName(fetchedAuthorName);
                    return authorRepository.save(newAuthor);
                });

        Book book = new Book();
        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setCategory(dto.getCategory());
        book.setAuthor(author);
        book.setAvailable(true);

        Book savedBook = bookRepository.save(book);
        return convertToDto(savedBook);
    }
    public BookResponseDto updateBook(Long id, BookRequestDto dto) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id));

        book.setTitle(dto.getTitle());
        book.setIsbn(dto.getIsbn());
        book.setCategory(dto.getCategory());

        Book updatedBook = bookRepository.save(book);

        return convertToDto(updatedBook);
    }
    public void deleteBook(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Book not found with id: " + id));

        bookRepository.delete(book);
    }
    public List<BookResponseDto> searchBooks(String title, BookCategory category,String authorName){
        List<Book>books;
        if (title != null){ books =  bookRepository.findByTitleContainingIgnoreCase(title);
        } else if (category != null){
            books = bookRepository.findByCategory(category);
        }else if (authorName != null){
            books = bookRepository.findByAuthorNameContainingIgnoreCase(authorName);
        }else {
            books = bookRepository.findAll();
        }
        return books.stream().map(this::convertToDto).collect(Collectors.toList());
    }
    private BookResponseDto convertToDto(Book book){
        String authorName = (book.getAuthor() != null) ? book.getAuthor().getName() : "Unknown";
        return new BookResponseDto(
            book.getId(),
            book.getTitle(),
            book.getIsbn(),
            book.getCategory(),
            authorName,
            book.isAvailable()
        );
    }
}
