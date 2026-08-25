package com.example.librarymanagement.service;
import com.example.librarymanagement.dto.OpenLibraryResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
@Service
public class OpenLibraryService {
    private final RestTemplate restTemplate;

    public OpenLibraryService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getAuthorNameByIsbn(String isbn) {
        try {
            String url = "https://openLibrary.org/api/books?bibkeys=ISBN:" + isbn + "&format=json&jscmd=data";
            OpenLibraryResponse response = restTemplate.getForObject(url, OpenLibraryResponse.class);

            if (response != null && response.getBooks() != null) {
                String key = "ISBN:" + isbn;
                OpenLibraryResponse.BookDetails bookDetails = response.getBooks().get(key);

                if (bookDetails != null && bookDetails.getAuthors() != null && !bookDetails.getAuthors().isEmpty()) {
                    return bookDetails.getAuthors().get(0).getName();
                }
            }
        } catch (Exception e) {
            System.err.println("Could not fetch author from Open Library API:" + e.getMessage());
        }
        return "Unknown Author";
    }
}
