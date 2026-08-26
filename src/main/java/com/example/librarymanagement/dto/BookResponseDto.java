package com.example.librarymanagement.dto;
import com.example.librarymanagement.model.BookCategory;
public class BookResponseDto {
    private Long id;
    private String title;
    private String isbn;
    private BookCategory category;
    private String authorName;
    private boolean available;

    public BookResponseDto(){}

    public BookResponseDto(Long id, String title, String isbn, BookCategory category, String authorName, boolean available){
        this.id = id;
        this.title = title;
        this.isbn = isbn;
        this.category = category;
        this.authorName = authorName;
        this.available = available;

    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getIsbn() {
        return isbn;
    }
    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
    public BookCategory getCategory() {
        return category;
    }
    public void setCategory(BookCategory category) {
        this.category = category;
    }
    public String getAuthorName() {
        return authorName;
    }
    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }
    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
