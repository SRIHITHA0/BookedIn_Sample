package com.cts.mfrp.bkin.entity;

import jakarta.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class UserBookId implements Serializable {

    private Long userId;
    private Long bookId;

    public UserBookId() {}

    public UserBookId(Long userId, Long bookId) {
        this.userId = userId;
        this.bookId = bookId;
    }

    public Long getUserId() { return userId; }
    public Long getBookId() { return bookId; }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserBookId that)) return false;
        return Objects.equals(userId, that.userId) && Objects.equals(bookId, that.bookId);
    }

    @Override
    public int hashCode() { return Objects.hash(userId, bookId); }
}
