package com.cts.mfrp.bkin.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.engine.internal.Nullability;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnoreProperties("reviewList")
    private User user;

    @ManyToOne
    @JoinColumn(nullable = false)
    @JsonIgnoreProperties("reviewList")
    private Book book;
    private double rating;
    private String content;
    @CreationTimestamp
    private LocalDateTime createdAt;
}
