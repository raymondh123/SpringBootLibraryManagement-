package com.example.librarymanagement.dto;
import com.example.librarymanagement.model.Author;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenLibraryResponse {
    private Map<String, BookDetails> books;

    public Map<String, BookDetails> getBooks() {
        return books;
    }
    public void setBooks(Map<String, BookDetails> books){
        this.books = books;
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class BookDetails {
        private List<Author> authors;

        public List<Author> getAuthors() {
            return authors;
        }
        public void setAuthors(List<Author> authors) {
            this.authors = authors;
        }
    }
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Author {
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }
    }
}
