package  com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Borrower;
<<<<<<< HEAD
import com.example.librarymanagement.dto.BorrowerRequestDto;
=======
import com.example.librarymanagement.service.BookService;
>>>>>>> 608869bda7becb0e3512de53f11d4e0940828524
import com.example.librarymanagement.service.BorrowerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
<<<<<<< HEAD
import org.springframework.http.HttpStatus;
=======
>>>>>>> 608869bda7becb0e3512de53f11d4e0940828524
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
<<<<<<< HEAD
    public ResponseEntity<Borrower> addBorrower(@Valid @RequestBody BorrowerRequestDto requestDto) {
        Borrower savedBorrower = borrowerService.addBorrower(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBorrower);
=======
    public ResponseEntity<Borrower> addBorrower(@Valid @RequestBody Borrower borrower) {
        Borrower savedBorrower = borrowerService.addBorrower(borrower);
        return ResponseEntity.ok(savedBorrower);
>>>>>>> 608869bda7becb0e3512de53f11d4e0940828524
    }
}
