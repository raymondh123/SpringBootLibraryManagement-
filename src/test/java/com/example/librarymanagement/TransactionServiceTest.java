package com.example.librarymanagement;

import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Borrower;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.BorrowerRepository;
import com.example.librarymanagement.repository.BorrowingTransactionRepository;
import com.example.librarymanagement.service.TransactionService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceTest {

    @Mock
    private BorrowingTransactionRepository transactionRepository;

    @Mock
    private BookRepository bookRepository;

    @Mock
    private BorrowerRepository borrowerRepository;

    @InjectMocks
    private TransactionService transactionService;

    @BeforeEach
    void setUp() {
        ReflectionTestUtils.setField(
                transactionService,
                "maxBorrowLimit",
                5
        );
    }

    @Test
    void borrowerCannotBorrowWhenLimitIsReached() {
        Book book = mock(Book.class);
        Borrower borrower = mock(Borrower.class);

        when(bookRepository.findById(1L))
                .thenReturn(Optional.of(book));

        when(book.isAvailable())
                .thenReturn(true);

        when(borrowerRepository.findById(1L))
                .thenReturn(Optional.of(borrower));

        when(transactionRepository
                .countByBorrowerIdAndReturnDateIsNull(1L))
                .thenReturn(5L);

        assertThrows(
                RuntimeException.class,
                () -> transactionService.borrowBook(1L, 1L)
        );

        verify(bookRepository, never()).save(any(Book.class));
        verify(transactionRepository, never()).save(any());
    }
}
