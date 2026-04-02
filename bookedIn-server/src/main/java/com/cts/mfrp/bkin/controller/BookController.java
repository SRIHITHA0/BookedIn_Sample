package com.cts.mfrp.bkin.controller;

import com.cts.mfrp.bkin.model.Book;
import com.cts.mfrp.bkin.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        return bookService.getBookById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestBody Book book) {
        return ResponseEntity.status(HttpStatus.CREATED).body(bookService.createBook(book));
    }

    // PUT /api/books/{id} - Update existing metadata (e.g., Synopsis or Page Count)
    @PutMapping("/{id}")
    public ResponseEntity<Book> updateBook(@PathVariable Long id, @RequestBody Book bookDetails) {
        return ResponseEntity.ok(bookService.updateBook(id, bookDetails));
    }

    // DELETE /api/books/{id} - Remove a book from the platform
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/genre/{genreName}")
    public List<Book> getBooksByGenre(@PathVariable String genreName) {
        return bookService.getBooksByGenre(genreName);
    }

    @GetMapping("/author/{authorName}")
    public List<Book> getBooksByAuthor(@PathVariable String authorName) {
        return bookService.getBooksByAuthor(authorName);
    }
}