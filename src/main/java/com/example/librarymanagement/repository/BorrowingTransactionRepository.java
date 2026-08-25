package com.example.librarymanagement.repository;
import com.example.librarymanagement.model.BorrowingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BorrowingTransactionRepository extends JpaRepository<BorrowingTransaction, Long> {
    List<BorrowingTransaction> findByBorrowerId(Long borrowerId);
<<<<<<< HEAD
    long countByBorrowerIdAndReturnDateIsNull(Long borrowerId);
=======
>>>>>>> 608869bda7becb0e3512de53f11d4e0940828524
}

