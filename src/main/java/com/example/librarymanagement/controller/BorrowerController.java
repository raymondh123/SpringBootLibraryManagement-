package  com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Borrower;
import com.example.librarymanagement.service.BookService;
import com.example.librarymanagement.service.BorrowerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @GetMapping
    public List<Borrower> getAllBorrowers() {
        return borrowerService.getAllBorrowers();
    }

    @PostMapping
    public ResponseEntity<Borrower> addBorrower(@Valid @RequestBody Borrower borrower) {
        Borrower savedBorrower = borrowerService.addBorrower(borrower);
        return ResponseEntity.ok(savedBorrower);
    }
}
