package com.cts.mfrp.bkin.controller;

import com.cts.mfrp.bkin.dto.ShelfItemDto;
import com.cts.mfrp.bkin.dto.ShelfRequest;
import com.cts.mfrp.bkin.entity.*;
import com.cts.mfrp.bkin.repository.*;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/shelf")
public class ShelfController {

    private final UserBookRepository userBookRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    public ShelfController(UserBookRepository userBookRepository,
                           UserRepository userRepository,
                           BookRepository bookRepository) {
        this.userBookRepository = userBookRepository;
        this.userRepository = userRepository;
        this.bookRepository = bookRepository;
    }

    @PostMapping
    public ResponseEntity<?> addOrUpdateShelf(@Valid @RequestBody ShelfRequest request,
                                              Principal principal) {
        User user = userRepository.findByUsername(principal.getName())
            .orElseThrow(() -> new RuntimeException("User not found"));
        Book book = bookRepository.findById(request.getBookId())
            .orElseThrow(() -> new RuntimeException("Book not found"));

        UserBook.ReadingStatus status = UserBook.ReadingStatus.valueOf(request.getStatus());

        Optional<UserBook> existing = userBookRepository
            .findByUser_UsernameAndBook_Id(user.getUsername(), book.getId());

        UserBook userBook = existing.orElse(new UserBook(user, book, status));
        userBook.setStatus(status);
        userBookRepository.save(userBook);

        return ResponseEntity.ok(Map.of("message", "Shelf updated", "status", status.name()));
    }

    @GetMapping("/{bookId}")
    public ResponseEntity<?> getStatus(@PathVariable Long bookId, Principal principal) {
        return userBookRepository
            .findByUser_UsernameAndBook_Id(principal.getName(), bookId)
            .<ResponseEntity<?>>map(ub -> ResponseEntity.ok(Map.of(
                "status", ub.getStatus().name(),
                "rating", ub.getRating() != null ? ub.getRating() : 0,
                "review", ub.getReview() != null ? ub.getReview() : ""
            )))
            .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<ShelfItemDto>> getMyShelf(Principal principal) {
        List<ShelfItemDto> items = userBookRepository.findByUser_Username(principal.getName())
            .stream()
            .map(ShelfItemDto::from)
            .toList();
        return ResponseEntity.ok(items);
    }
}
