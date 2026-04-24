package com.cts.mfrp.bkin.dto;

import jakarta.validation.constraints.Size;
import java.util.List;

public class UpdateProfileRequest {
    @Size(max = 100)
    private String displayName;

    @Size(max = 500)
    private String bio;

    private String profilePictureUrl;
    private List<String> interests;

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }
    public String getProfilePictureUrl() { return profilePictureUrl; }
    public void setProfilePictureUrl(String profilePictureUrl) { this.profilePictureUrl = profilePictureUrl; }
    public List<String> getInterests() { return interests; }
    public void setInterests(List<String> interests) { this.interests = interests; }
}
