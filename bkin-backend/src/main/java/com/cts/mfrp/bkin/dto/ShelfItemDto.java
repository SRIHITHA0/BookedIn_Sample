package com.cts.mfrp.bkin.dto;

import com.cts.mfrp.bkin.entity.Book;
import com.cts.mfrp.bkin.entity.UserBook;

public class ShelfItemDto {
    private BookSummary book;
    private String status;
    private Integer rating;
    private String review;
    private String addedAt;

    public static ShelfItemDto from(UserBook ub) {
        ShelfItemDto dto = new ShelfItemDto();
        dto.book = BookSummary.from(ub.getBook());
        dto.status = ub.getStatus().name();
        dto.rating = ub.getRating();
        dto.review = ub.getReview();
        dto.addedAt = ub.getAddedAt().toString();
        return dto;
    }

    public static class BookSummary {
        private Long id;
        private String title;
        private String author;
        private String coverImageUrl;
        private GenreSummary genre;
        private double averageRating;

        public static BookSummary from(Book book) {
            BookSummary bs = new BookSummary();
            bs.id = book.getId();
            bs.title = book.getTitle();
            bs.author = book.getAuthor();
            bs.coverImageUrl = book.getCoverImageUrl();
            bs.genre = book.getGenre() != null ? new GenreSummary(book.getGenre().getName()) : null;
            bs.averageRating = book.getAverageRating() != null ? book.getAverageRating().doubleValue() : 0.0;
            return bs;
        }

        public Long getId() { return id; }
        public String getTitle() { return title; }
        public String getAuthor() { return author; }
        public String getCoverImageUrl() { return coverImageUrl; }
        public GenreSummary getGenre() { return genre; }
        public double getAverageRating() { return averageRating; }
    }

    public static class GenreSummary {
        private String name;
        public GenreSummary(String name) { this.name = name; }
        public String getName() { return name; }
    }

    public BookSummary getBook() { return book; }
    public String getStatus() { return status; }
    public Integer getRating() { return rating; }
    public String getReview() { return review; }
    public String getAddedAt() { return addedAt; }
}
