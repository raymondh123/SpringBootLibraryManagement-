package  com.example.librarymanagement.service;
import com.example.librarymanagement.model.Borrower;
import com.example.librarymanagement.repository.BorrowerRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class BorrowerService {
    private final BorrowerRepository borrowerRepository;

    public BorrowerService(BorrowerRepository borrowerRepository) {
        this.borrowerRepository = borrowerRepository;
    }
    public List<Borrower> getAllBorrowers() {
        return borrowerRepository.findAll();
    }
    public Borrower addBorrower(Borrower borrower) {
        return borrowerRepository.save(borrower);
    }
}
