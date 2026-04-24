package com.cts.mfrp.bkin.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_books")
public class UserBook {

    @EmbeddedId
    private UserBookId id;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("userId")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @MapsId("bookId")
    @JoinColumn(name = "book_id")
    private Book book;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReadingStatus status = ReadingStatus.WANT_TO_READ;

    @Column
    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String review;

    @Column(name = "added_at", updatable = false)
    private LocalDateTime addedAt = LocalDateTime.now();

    public enum ReadingStatus { WANT_TO_READ, READING, COMPLETED }

    public UserBook() {}

    public UserBook(User user, Book book, ReadingStatus status) {
        this.id = new UserBookId(user.getId(), book.getId());
        this.user = user;
        this.book = book;
        this.status = status;
    }

    public UserBookId getId() { return id; }
    public User getUser() { return user; }
    public Book getBook() { return book; }
    public ReadingStatus getStatus() { return status; }
    public void setStatus(ReadingStatus status) { this.status = status; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }
    public LocalDateTime getAddedAt() { return addedAt; }
}
