package com.cts.mfrp.bkin.entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "chat_deletions",
       uniqueConstraints = @UniqueConstraint(columnNames = {"username", "room_id"}))
public class ChatDeletion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(name = "room_id", nullable = false)
    private String roomId;

    @Column(name = "deleted_at")
    private LocalDateTime deletedAt = LocalDateTime.now();

    public ChatDeletion() {}

    public ChatDeletion(String username, String roomId) {
        this.username = username;
        this.roomId   = roomId;
    }

    public Long getId()            { return id; }
    public String getUsername()    { return username; }
    public String getRoomId()      { return roomId; }
    public LocalDateTime getDeletedAt() { return deletedAt; }
}
