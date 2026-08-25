package  com.example.librarymanagement.service;
import com.example.librarymanagement.model.Borrower;
import com.example.librarymanagement.repository.BorrowerRepository;
<<<<<<< HEAD
import com.example.librarymanagement.dto.BorrowerRequestDto;
=======
>>>>>>> 608869bda7becb0e3512de53f11d4e0940828524
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
<<<<<<< HEAD
    public Borrower addBorrower(BorrowerRequestDto requestDto) {
        Borrower borrower = new Borrower();
        borrower.setName(requestDto.getName());
        borrower.setEmail(requestDto.getEmail());
        borrower.setPhoneNumber(requestDto.getPhoneNumber());
=======
    public Borrower addBorrower(Borrower borrower) {
>>>>>>> 608869bda7becb0e3512de53f11d4e0940828524
        return borrowerRepository.save(borrower);
    }
}
