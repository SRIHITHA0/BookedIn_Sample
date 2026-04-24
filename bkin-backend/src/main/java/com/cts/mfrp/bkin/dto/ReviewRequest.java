package com.cts.mfrp.bkin.dto;

import jakarta.validation.constraints.*;

public class ReviewRequest {
    @NotNull
    private Long bookId;

    @NotNull @Min(1) @Max(5)
    private Integer rating;

    private String review;

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public Integer getRating() { return rating; }
    public void setRating(Integer rating) { this.rating = rating; }
    public String getReview() { return review; }
    public void setReview(String review) { this.review = review; }
}
