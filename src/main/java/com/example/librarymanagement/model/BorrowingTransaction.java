package com.example.librarymanagement.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
@Entity
@Table(name = "borrowing_transactions")
public class BorrowingTransaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Book is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    private Book book;

    @NotNull(message = "Borrower is mandatory")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "borrower_id")
    private Borrower borrower;

    @NotNull(message = "Borrower Date is mandatory")
    private LocalDate borrowDate;

    private LocalDate dueDate;

    private LocalDate returnDate;

    private boolean returned =  false;
    public BorrowingTransaction() {}

    public Long getId() {
        return id;
    }
    public void setId(Long id) {this.id = id;}

    public Book getBook() {return book;}
    public void setBook(Book book) {this.book = book;}

    public Borrower getBorrower() {return borrower;}
    public void setBorrower(Borrower borrower) {this.borrower = borrower;}

    public LocalDate getBorrowDate() {return borrowDate;}
    public void setBorrowDate(LocalDate borrowDate) {this.borrowDate = borrowDate;}

    public LocalDate getDueDate() {return dueDate;}
    public void setDueDate(LocalDate dueDate) {this.dueDate = dueDate;}

    public LocalDate getReturnDate() {return returnDate;}
    public void setReturnDate(LocalDate returnDate) {this.returnDate = returnDate;}

    public boolean isReturned() {return returned;}
    public void setReturned(boolean returned) {this.returned = returned;}

}
