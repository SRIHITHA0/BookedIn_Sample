package com.cts.mfrp.bkin.dto;

import com.cts.mfrp.bkin.entity.UserBook;

public class ReviewResponseDto {
    private String username;
    private String displayName;
    private Integer rating;
    private String review;
    private String addedAt;

    public static ReviewResponseDto from(UserBook ub) {
        ReviewResponseDto dto = new ReviewResponseDto();
        dto.username = ub.getUser().getUsername();
        dto.displayName = ub.getUser().getDisplayName();
        dto.rating = ub.getRating();
        dto.review = ub.getReview();
        dto.addedAt = ub.getAddedAt().toString();
        return dto;
    }

    public String getUsername() { return username; }
    public String getDisplayName() { return displayName; }
    public Integer getRating() { return rating; }
    public String getReview() { return review; }
    public String getAddedAt() { return addedAt; }
}
