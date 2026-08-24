package com.example.librarymanagement.service;
import com.example.librarymanagement.dto.BookRequestDto;
import com.example.librarymanagement.model.Author;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.BookCategory;
import com.example.librarymanagement.repository.AuthorRepository;
import com.example.librarymanagement.repository.BookRepository;
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
    public List<Book> getAllBooks() {return bookRepository.findAll();}

    public Book addBook(BookRequestDto dto){
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

        return bookRepository.save(book);
    }
    public List<Book> searchBooks(String title, BookCategory category,String authorName){
        if (title != null) return bookRepository.findByTitleContainingIgnoreCase(title);
        if (category != null) return bookRepository.findByCategory(category);
        if (authorName != null) return bookRepository.findByAuthorNameContainingIgnoreCase(authorName);
        return bookRepository.findAll();
    }
}
