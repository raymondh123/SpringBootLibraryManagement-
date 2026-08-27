package  com.example.librarymanagement.controller;

import com.example.librarymanagement.model.Borrower;

import com.example.librarymanagement.dto.BorrowerRequestDto;
import com.example.librarymanagement.dto.BorrowerResponseDto;
import com.example.librarymanagement.service.BookService;

import com.example.librarymanagement.service.BorrowerService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/borrowers")
public class BorrowerController {
    private final BorrowerService borrowerService;

    public BorrowerController(BorrowerService borrowerService) {
        this.borrowerService = borrowerService;
    }

    @GetMapping
    public List<BorrowerResponseDto> getAllBorrowers() {
        return borrowerService.getAllBorrowers().stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @GetMapping("/{id}")
    public ResponseEntity<BorrowerResponseDto> getBorrowerById(
            @PathVariable Long id) {

        Borrower borrower = borrowerService.getBorrowerById(id);

        return ResponseEntity.ok(convertToDto(borrower));
    }
    @PutMapping("/{id}")
    public ResponseEntity<BorrowerResponseDto> updateBorrower(
            @PathVariable Long id,
            @Valid @RequestBody BorrowerRequestDto requestDto) {

        Borrower updatedBorrower =
                borrowerService.updateBorrower(id, requestDto);

        return ResponseEntity.ok(convertToDto(updatedBorrower));
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBorrower(@PathVariable Long id) {
        borrowerService.deleteBorrower(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping

    public ResponseEntity<BorrowerResponseDto> addBorrower(@Valid @RequestBody BorrowerRequestDto requestDto) {
        Borrower savedBorrower = borrowerService.addBorrower(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(convertToDto(savedBorrower));
    }
    private BorrowerResponseDto convertToDto(Borrower borrower) {
        return new BorrowerResponseDto(
                borrower.getId(),
                borrower.getName(),
                borrower.getEmail(),
                borrower.getPhoneNumber()
        );
    }
}
