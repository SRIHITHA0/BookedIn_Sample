package com.cts.mfrp.bkin.model;

import com.cts.mfrp.bkin.model.Book;
import com.cts.mfrp.bkin.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class LibraryEntry {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false)
    private String shelf; // e.g., "Reading", "Completed", "Want to Read"

    private Integer progressPct = 0;
    private Integer pagesRead = 0;

    private LocalDate startedAt;
    private LocalDate completedAt;

    @UpdateTimestamp
    private LocalDateTime updatedAt;
}