package com.cts.mfrp.bkin.service;

import com.cts.mfrp.bkin.model.Review;
import com.cts.mfrp.bkin.repo.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.OptionalDouble;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    public List<Review> getReviewsByBook(Long bookId) {
        return reviewRepository.findByBookIdOrderByCreatedAtDesc(bookId);
    }

    public Review saveReview(Review review) {
        return reviewRepository.save(review);
    }

    public Double getAverageRating(Long bookId) {
        List<Review> reviews = reviewRepository.findByBookIdOrderByCreatedAtDesc(bookId);
        OptionalDouble average = reviews.stream()
                .mapToDouble(Review::getRating)
                .average();
        return average.isPresent() ? Math.round(average.getAsDouble() * 10.0) / 10.0 : 0.0;
    }

    public void deleteReview(Long id) {
        reviewRepository.deleteById(id);
    }

    public List<Review> getReviewsByUser(Long userId) {
        return reviewRepository.findByUserId(userId);
    }

    public Review updateReview(Long id, Review updatedReview) {
        Review existing = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found with id: " + id));

        // Update only the fields editable by the user
        existing.setRating(updatedReview.getRating());
        existing.setContent(updatedReview.getContent());

        return reviewRepository.save(existing);
    }
}