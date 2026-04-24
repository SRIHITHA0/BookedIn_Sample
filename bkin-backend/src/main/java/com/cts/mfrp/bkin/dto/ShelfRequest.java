package com.cts.mfrp.bkin.dto;

import jakarta.validation.constraints.NotNull;

public class ShelfRequest {
    @NotNull
    private Long bookId;
    @NotNull
    private String status;

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
