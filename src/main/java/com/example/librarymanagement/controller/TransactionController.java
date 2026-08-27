package com.example.librarymanagement.controller;
import com.example.librarymanagement.model.BorrowingTransaction;
import com.example.librarymanagement.service.TransactionService;
import com.example.librarymanagement.dto.TransactionResponseDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping
    public List<TransactionResponseDto> getAllTransactions() {
        return transactionService.getAllTransactions().stream().map(this::convertToDto).collect(Collectors.toList());
    }
    @PostMapping("/borrow")
    public ResponseEntity<TransactionResponseDto> borrowBook(
            @RequestParam Long bookId,
            @RequestParam Long borrowerId) {
        BorrowingTransaction transaction = transactionService.borrowBook(bookId, borrowerId);
        return ResponseEntity.ok(convertToDto(transaction));
    }
    @PutMapping("/return/{transactionId}")
    public ResponseEntity<TransactionResponseDto> returnBook(@PathVariable Long transactionId) {
        BorrowingTransaction transaction = transactionService.returnBook(transactionId);
        return ResponseEntity.ok(convertToDto(transaction));
    }
    private TransactionResponseDto convertToDto(
            BorrowingTransaction transaction) {

        return new TransactionResponseDto(
                transaction.getId(),
                transaction.getBook().getTitle(),
                transaction.getBorrower().getName(),
                transaction.getBorrowDate(),
                transaction.getReturnDate()
        );
    }
}
