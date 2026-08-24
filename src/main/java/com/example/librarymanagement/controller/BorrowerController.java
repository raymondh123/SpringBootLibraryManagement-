package  com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Borrower;
import com.example.librarymanagement.dto.BorrowerRequestDto;
import com.example.librarymanagement.service.BorrowerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<Borrower> addBorrower(@Valid @RequestBody BorrowerRequestDto borrowerDto) {
        Borrower savedBorrower = borrowerService.addBorrower(borrowerDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBorrower);
    }
}
