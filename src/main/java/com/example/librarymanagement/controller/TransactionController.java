package com.example.librarymanagement.controller;
import com.example.librarymanagement.model.BorrowingTransaction;
import com.example.librarymanagement.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping
    public List<BorrowingTransaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }
    @PostMapping("/borrow")
    public ResponseEntity<BorrowingTransaction> borrowBook(
            @RequestParam Long bookId,
            @RequestParam Long borrowerId) {
        BorrowingTransaction transaction = transactionService.borrowBook(bookId, borrowerId);
        return ResponseEntity.ok(transaction);
    }
    @PutMapping("/return/{transactionId}")
    public ResponseEntity<BorrowingTransaction> returnBook(@PathVariable Long transactionId) {
        BorrowingTransaction transaction = transactionService.returnBook(transactionId);
        return ResponseEntity.ok(transaction);
    }
}
