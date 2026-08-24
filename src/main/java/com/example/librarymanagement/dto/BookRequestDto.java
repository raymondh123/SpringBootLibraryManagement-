package com.example.librarymanagement.dto;
import com.example.librarymanagement.model.BookCategory;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class BookRequestDto {

    @NotBlank(message = "Title is mandatory")
    private String title;

    @NotBlank(message = "ISBN is mandatory and cannot be null")
    private String isbn;

    @NotNull(message = "Category must be a valid enum value")
    private BookCategory category;
    @NotNull(message = "Author ID id mandatory")
    private Long authorId;

    public String getTitle() {return title;}
    public void setTitle(String title) {this.title = title;}

    public String getIsbn() {return isbn;}
    public void setIsbn(String isbn) {this.isbn = isbn;}

    public BookCategory getCategory() {return category;}
    public void setCategory(BookCategory category) {this.category = category;}

    public Long getAuthorId() {return authorId;}
    public void setAuthorId(Long authorId) {this.authorId = authorId;}
}
