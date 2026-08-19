package com.example.librarymanagement.repository;
import com.example.librarymanagement.model.BorrowingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BorrowingTransactionRepository extends JpaRepository<BorrowingTransaction, Long> {
    List<BorrowingTransaction> findByBorrowerId(Long borrowerId);
}

