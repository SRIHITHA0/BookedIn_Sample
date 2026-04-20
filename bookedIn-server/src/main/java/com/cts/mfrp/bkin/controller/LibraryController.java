package com.cts.mfrp.bkin.controller;

import com.cts.mfrp.bkin.dto.LibraryEntryDTO;
import com.cts.mfrp.bkin.model.LibraryEntry;
import com.cts.mfrp.bkin.service.LibraryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/library")
@CrossOrigin(origins = "http://localhost:4200")
public class LibraryController {

    @Autowired
    private LibraryService libraryService;

    // Use ResponseEntity for better API response handling
    @GetMapping("/{userId}")
    public ResponseEntity<List<LibraryEntryDTO>> getLibrary(@PathVariable Long userId) {
        List<LibraryEntryDTO> library = libraryService.getUserLibrary(userId);
        return ResponseEntity.ok(library);
    }
}