package com.cts.mfrp.bkin.service;

import com.cts.mfrp.bkin.dto.LibraryEntryDTO;
import com.cts.mfrp.bkin.model.LibraryEntry;
import com.cts.mfrp.bkin.repo.LibraryEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {

    @Autowired
    private LibraryEntryRepo libraryEntryRepo;

    public List<LibraryEntryDTO> getUserLibrary(Long userId) {
        List<LibraryEntry> entries = libraryEntryRepo.findByUserId(userId);

        // Convert Entities to DTOs
        return entries.stream().map(entry -> {
            LibraryEntryDTO dto = new LibraryEntryDTO();
            dto.setId(entry.getId());
            dto.setShelf(entry.getShelf());
            dto.setProgressPct(entry.getProgressPct());
            dto.setBookTitle(entry.getBook().getTitle());
            dto.setBookCoverUrl(entry.getBook().getCoverUrl());
            dto.setBookAuthor(entry.getBook().getAuthor());
            dto.setStartedAt(entry.getStartedAt());
            return dto;
        }).collect(Collectors.toList());
    }
}