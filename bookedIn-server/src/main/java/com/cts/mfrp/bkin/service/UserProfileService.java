package com.cts.mfrp.bkin.service;

import com.cts.mfrp.bkin.model.UserProfile;
import com.cts.mfrp.bkin.repo.UserProfileRepository;
import org.springframework.stereotype.Service;
@Service
public class UserProfileService {

    private final UserProfileRepository repository;

    public UserProfileService(UserProfileRepository repository) {
        this.repository = repository;
    }

    // Save profile (already working)
    public void saveProfile(UserProfile profile) {
        profile.setUserId(1L); // TEMP
        profile.setDisplayName("User");
        profile.setProfileCompleted(true);
        repository.save(profile);
    }

    // ✅ UPDATED: Fetch safely (NO 500)

    public UserProfile getProfile() {
        return repository.findAll()
                .stream()
                .findFirst()
                .orElse(null);
    }
}