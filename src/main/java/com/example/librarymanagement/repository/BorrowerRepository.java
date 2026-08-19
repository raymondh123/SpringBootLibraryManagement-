package  com.example.librarymanagement.repository;
import com.example.librarymanagement.model.Borrower;
import org.springframework.data.jpa.repository.JpaRepository;
public interface BorrowerRepository extends JpaRepository<Borrower,Long> {
}
