package com.cts.mfrp.bkin.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class LibraryEntryDTO {
    private Long id;
    private String shelf;
    private Integer progressPct;
    private String bookTitle;
    private String bookCoverUrl;
    private String bookAuthor;
    private LocalDate startedAt;
}