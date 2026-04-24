package com.cts.mfrp.bkin.service;

import com.cts.mfrp.bkin.entity.Book;
import com.cts.mfrp.bkin.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Book not found: " + id));
    }

    public List<Book> getTrending() {
        return bookRepository.findTop10ByOrderByAverageRatingDesc();
    }

    public List<Book> searchBooks(String query) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
    }

    public List<Book> getBooksByGenre(String genreName) {
        return bookRepository.findByGenre_Name(genreName);
    }
}
