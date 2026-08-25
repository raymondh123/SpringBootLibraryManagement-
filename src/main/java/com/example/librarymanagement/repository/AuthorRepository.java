package  com.example.librarymanagement.repository;
import com.example.librarymanagement.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
<<<<<<< HEAD

import java.util.Optional;

public interface AuthorRepository extends JpaRepository<Author,Long> {
    Optional<Author> findByName(String name);
=======
public interface AuthorRepository extends JpaRepository<Author,Long> {
>>>>>>> 608869bda7becb0e3512de53f11d4e0940828524
}

