package com.cts.mfrp.bkin.controller;

import com.cts.mfrp.bkin.dto.UpdateProfileRequest;
import com.cts.mfrp.bkin.dto.UserProfileDto;
import com.cts.mfrp.bkin.entity.Genre;
import com.cts.mfrp.bkin.entity.User;
import com.cts.mfrp.bkin.repository.GenreRepository;
import com.cts.mfrp.bkin.repository.UserRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Set;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserRepository userRepository;
    private final GenreRepository genreRepository;

    public UserController(UserRepository userRepository, GenreRepository genreRepository) {
        this.userRepository = userRepository;
        this.genreRepository = genreRepository;
    }

    @GetMapping("/me")
    @Transactional(readOnly = true)
    public ResponseEntity<UserProfileDto> getMyProfile(Principal principal) {
        User user = userRepository.findByUsernameWithInterests(principal.getName())
            .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(UserProfileDto.from(user));
    }

    @GetMapping("/{username}")
    @Transactional(readOnly = true)
    public ResponseEntity<UserProfileDto> getProfile(@PathVariable String username) {
        User user = userRepository.findByUsernameWithInterests(username)
            .orElseThrow(() -> new RuntimeException("User not found"));
        return ResponseEntity.ok(UserProfileDto.from(user));
    }

    @PutMapping("/me")
    @Transactional
    public ResponseEntity<UserProfileDto> updateProfile(@Valid @RequestBody UpdateProfileRequest request,
                                                         Principal principal) {
        User user = userRepository.findByUsernameWithInterests(principal.getName())
            .orElseThrow(() -> new RuntimeException("User not found"));

        if (request.getDisplayName() != null) user.setDisplayName(request.getDisplayName());
        if (request.getBio() != null) user.setBio(request.getBio());
        if (request.getProfilePictureUrl() != null) user.setProfilePictureUrl(request.getProfilePictureUrl());

        if (request.getInterests() != null && !request.getInterests().isEmpty()) {
            Set<Genre> genres = genreRepository.findByNameIn(request.getInterests());
            user.setInterests(genres);
        }

        userRepository.save(user);
        return ResponseEntity.ok(UserProfileDto.from(user));
    }
}
