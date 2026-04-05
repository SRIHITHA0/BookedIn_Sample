package com.cts.mfrp.bkin.controller;

import com.cts.mfrp.bkin.model.UserProfile;
import com.cts.mfrp.bkin.service.UserProfileService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/profile")
public class UserProfileController {

    private final UserProfileService service;

    public UserProfileController(UserProfileService service) {
        this.service = service;
    }

    // Save profile
    @PostMapping("/onboarding")
    public void saveProfile(@RequestBody UserProfile profile) {
        service.saveProfile(profile);
    }

    // Fetch profile
    @GetMapping("/me")
    public UserProfile getProfile() {
        return service.getProfile();
    }
}