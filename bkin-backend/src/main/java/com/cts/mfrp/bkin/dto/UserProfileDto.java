package com.cts.mfrp.bkin.dto;

import com.cts.mfrp.bkin.entity.User;
import java.util.List;

public class UserProfileDto {
    private Long id;
    private String username;
    private String displayName;
    private String bio;
    private String profilePictureUrl;
    private List<String> interests;
    private String createdAt;

    public static UserProfileDto from(User user) {
        UserProfileDto dto = new UserProfileDto();
        dto.id = user.getId();
        dto.username = user.getUsername();
        dto.displayName = user.getDisplayName();
        dto.bio = user.getBio();
        dto.profilePictureUrl = user.getProfilePictureUrl();
        dto.interests = user.getInterests().stream().map(g -> g.getName()).toList();
        dto.createdAt = user.getCreatedAt().toString();
        return dto;
    }

    public Long getId() { return id; }
    public String getUsername() { return username; }
    public String getDisplayName() { return displayName; }
    public String getBio() { return bio; }
    public String getProfilePictureUrl() { return profilePictureUrl; }
    public List<String> getInterests() { return interests; }
    public String getCreatedAt() { return createdAt; }
}
