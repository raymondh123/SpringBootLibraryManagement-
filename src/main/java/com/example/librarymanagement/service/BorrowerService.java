package  com.example.librarymanagement.service;
import com.example.librarymanagement.model.Borrower;
import com.example.librarymanagement.repository.BorrowerRepository;
import com.example.librarymanagement.dto.BorrowerRequestDto;
import com.example.librarymanagement.exception.ResourceNotFoundException;
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
    public Borrower getBorrowerById(Long id) {
        return borrowerRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Borrower not found with id: " + id));
    }

    public Borrower addBorrower(BorrowerRequestDto requestDto) {
        Borrower borrower = new Borrower();
        borrower.setName(requestDto.getName());
        borrower.setEmail(requestDto.getEmail());
        borrower.setPhoneNumber(requestDto.getPhoneNumber());
        return borrowerRepository.save(borrower);
    }
}
