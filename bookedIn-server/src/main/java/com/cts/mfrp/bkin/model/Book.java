package com.cts.mfrp.bkin.model;

import jakarta.persistence.Entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String title;
    @Column(nullable = false)
    private String author;
    @Column(nullable = false)
    private String coverUrl;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Integer totalPages;
    @Column(nullable = false)
    private String isbn;
    @Column(nullable = false)
    private LocalDateTime publishedAt;

    private LocalDateTime memberSince;
    @OneToMany(mappedBy = "book", cascade = CascadeType.ALL)
    private List<Review> reviewList = new ArrayList<>();


}
