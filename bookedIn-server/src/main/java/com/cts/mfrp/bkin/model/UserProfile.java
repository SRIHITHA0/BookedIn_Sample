package com.cts.mfrp.bkin.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class UserProfile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TEMP user identifier until authentication is implemented
    private Long userId;

    // User name to display in profile
    private String displayName;

    // Bio text – LONGTEXT because user can type long paragraphs
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String bio;

    // Selected genres
    @ElementCollection
    private List<String> favoriteGenres;

    // Upload image – Base64 string is very large
    @Lob
    @Column(columnDefinition = "LONGTEXT")
    private String profileImageUrl;

    private boolean profileCompleted;

    public UserProfile() {}

    // Getters & Setters
    public Long getId() { return id; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public List<String> getFavoriteGenres() { return favoriteGenres; }
    public void setFavoriteGenres(List<String> favoriteGenres) {
        this.favoriteGenres = favoriteGenres;
    }

    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public boolean isProfileCompleted() { return profileCompleted; }
    public void setProfileCompleted(boolean profileCompleted) {
        this.profileCompleted = profileCompleted;
    }
}
