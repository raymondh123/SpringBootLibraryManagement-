package  com.example.librarymanagement.service;
import com.example.librarymanagement.model.Book;
import com.example.librarymanagement.model.Borrower;
import com.example.librarymanagement.model.BorrowingTransaction;
import com.example.librarymanagement.repository.BookRepository;
import com.example.librarymanagement.repository.BorrowerRepository;
import com.example.librarymanagement.repository.BorrowingTransactionRepository;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.util.List;

@Service
public class TransactionService {

    private final BorrowingTransactionRepository transactionRepository;
    private final BookRepository bookRepository;
    private final BorrowerRepository borrowerRepository;

    public TransactionService(BorrowingTransactionRepository transactionRepository, BookRepository bookRepository, BorrowerRepository borrowerRepository) {
        this.transactionRepository = transactionRepository;
        this.bookRepository = bookRepository;
        this.borrowerRepository = borrowerRepository;
    }
    public BorrowingTransaction borrowBook(Long bookId,Long borrowerId){
        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new RuntimeException("Book Not Found"));
        if (!book.isAvailable()){
            throw new RuntimeException("Book is already borrowed");
        }
        Borrower borrower = borrowerRepository.findById(borrowerId)
                .orElseThrow(() -> new RuntimeException("Borrower not found"));
        book.setAvailable(false);
        bookRepository.save(book);

        BorrowingTransaction transaction = new BorrowingTransaction();
        transaction.setBook(book);
        transaction.setBorrower(borrower);
        transaction.setBorrowDate(LocalDate.now());
        transaction.setDueDate(LocalDate.now().plusWeeks(2));
        transaction.setReturned(false);

        return transactionRepository.save(transaction);
    }
    public BorrowingTransaction returnBook(Long transactionId) {
        BorrowingTransaction transaction = transactionRepository.findById(transactionId)
                .orElseThrow(() -> new RuntimeException("Transaction Not Found"));
        if (transaction.isReturned()){
            throw new RuntimeException("Book has already been returned");
        }
        transaction.setReturned(true);
        transaction.setReturnDate(LocalDate.now());

        Book book = transaction.getBook();
        book.setAvailable(true);
        bookRepository.save(book);

        return transactionRepository.save(transaction);
    }
    public List<BorrowingTransaction> getAllTransactions(){
        return transactionRepository.findAll();
    }
}
