package com.example.librarymanagement.dto;

import java.time.LocalDate;

public class TransactionResponseDto {
    private Long id;
    private String bookTitle;
    private String borrowerName;
    private LocalDate borrowDate;
    private LocalDate returnDate;

    public TransactionResponseDto() {}

    public TransactionResponseDto(Long id, String bookTitle, String borrowerName, LocalDate borrowDate, LocalDate returnDate) {
        this.id = id;
        this.bookTitle = bookTitle;
        this.borrowerName = borrowerName;
        this.borrowDate = borrowDate;
        this.returnDate = returnDate;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getBookTitle() {
        return bookTitle;
    }
    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }
    public String getBorrowerName() {
        return borrowerName;
    }
    public void setBorrowerName(String borrowerName) {
        this.borrowerName = borrowerName;
    }
    public LocalDate getBorrowDate() {
        return borrowDate;
    }
    public void setBorrowDate(LocalDate borrowDate) {
        this.borrowDate = borrowDate;
    }
    public LocalDate getReturnDate() {
        return returnDate;
    }
    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
}
