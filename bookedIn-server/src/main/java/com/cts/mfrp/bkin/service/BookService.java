package com.cts.mfrp.bkin.service;

import com.cts.mfrp.bkin.model.Book;
import com.cts.mfrp.bkin.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Optional<Book> getBookById(Long id) {
        return bookRepository.findById(id);
    }

    public List<Book> getBooksByGenre(String genreName) {
        return bookRepository.findByGenres_NameIgnoreCase(genreName);
    }

    public List<Book> getBooksByAuthor(String author) {
        return bookRepository.findByAuthorIgnoreCase(author);
    }


    public Book createBook(Book book) {
        return bookRepository.save(book);
    }

    public Book updateBook(Long id, Book bookDetails) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found for id: " + id));

        book.setTitle(bookDetails.getTitle());
        book.setAuthor(bookDetails.getAuthor());
        book.setCoverUrl(bookDetails.getCoverUrl());
        book.setDescription(bookDetails.getDescription());
        book.setTotalPages(bookDetails.getTotalPages());
        book.setIsbn(bookDetails.getIsbn());
        book.setPublishedAt(bookDetails.getPublishedAt());
        book.setGenres(bookDetails.getGenres());

        return bookRepository.save(book);
    }

    public void deleteBook(Long id) {
        bookRepository.deleteById(id);
    }
}