package  com.example.librarymanagement.controller;
import com.example.librarymanagement.dto.BookRequestDto;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.BookCategory;
import com.example.librarymanagement.service.BookService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @PostMapping
    public ResponseEntity<Book> addBook(@Valid @RequestBody BookRequestDto dto) {
        Book savedBook = bookService.addBook(dto);
        return ResponseEntity.ok(savedBook);
    }
    @GetMapping("/search")
    public List<Book> searchBooks(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) BookCategory category,
            @RequestParam(required = false) String authorName) {
        return bookService.searchBooks(title, category, authorName);
    }
}
