package com.cts.mfrp.bkin.controller;

import com.cts.mfrp.bkin.entity.UserBlock;
import com.cts.mfrp.bkin.repository.UserBlockRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;

@RestController
public class BlockController {

    private final UserBlockRepository userBlockRepository;

    public BlockController(UserBlockRepository userBlockRepository) {
        this.userBlockRepository = userBlockRepository;
    }

    /** Block a user */
    @PostMapping("/api/users/{username}/block")
    @Transactional
    public ResponseEntity<Void> blockUser(@PathVariable String username, Principal principal) {
        String blocker = principal.getName();
        if (blocker.equals(username)) return ResponseEntity.badRequest().build();
        if (!userBlockRepository.existsByBlockerUsernameAndBlockedUsername(blocker, username)) {
            userBlockRepository.save(new UserBlock(blocker, username));
        }
        return ResponseEntity.ok().build();
    }

    /** Unblock a user */
    @DeleteMapping("/api/users/{username}/block")
    @Transactional
    public ResponseEntity<Void> unblockUser(@PathVariable String username, Principal principal) {
        userBlockRepository.deleteByBlockerUsernameAndBlockedUsername(principal.getName(), username);
        return ResponseEntity.noContent().build();
    }

    /** Check if the current user has blocked the given user */
    @GetMapping("/api/users/{username}/block")
    public ResponseEntity<Map<String, Boolean>> isBlocked(@PathVariable String username, Principal principal) {
        boolean blocked = userBlockRepository
            .existsByBlockerUsernameAndBlockedUsername(principal.getName(), username);
        return ResponseEntity.ok(Map.of("blocked", blocked));
    }
}
