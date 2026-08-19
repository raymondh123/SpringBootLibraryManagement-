package  com.example.librarymanagement.repository;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.BookCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
public interface BookRepository extends JpaRepository<Book,Long> {
    List<Book> findByTitleContainingIgnoreCase(String title);
    List<Book> findByCategory(BookCategory category);
    List<Book> findByAuthorNameContainingIgnoreCase(String authorName);
}

