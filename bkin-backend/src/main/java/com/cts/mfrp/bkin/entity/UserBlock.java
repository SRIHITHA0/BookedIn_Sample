package com.cts.mfrp.bkin.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "user_blocks",
       uniqueConstraints = @UniqueConstraint(columnNames = {"blocker_username", "blocked_username"}))
public class UserBlock {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "blocker_username", nullable = false)
    private String blockerUsername;

    @Column(name = "blocked_username", nullable = false)
    private String blockedUsername;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public UserBlock() {}

    public UserBlock(String blockerUsername, String blockedUsername) {
        this.blockerUsername = blockerUsername;
        this.blockedUsername = blockedUsername;
    }

    public Long getId()                 { return id; }
    public String getBlockerUsername()  { return blockerUsername; }
    public String getBlockedUsername()  { return blockedUsername; }
    public LocalDateTime getCreatedAt() { return createdAt; }
}
