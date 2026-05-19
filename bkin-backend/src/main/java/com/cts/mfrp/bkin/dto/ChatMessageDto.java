package com.cts.mfrp.bkin.dto;

public class ChatMessageDto {
    private Long id;
    private String content;
    private String type = "TEXT";
    private String senderUsername;
    private String senderDisplayName;
    private String senderProfilePictureUrl;
    private String sentAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
    public String getSenderUsername() { return senderUsername; }
    public void setSenderUsername(String senderUsername) { this.senderUsername = senderUsername; }
    public String getSenderDisplayName() { return senderDisplayName; }
    public void setSenderDisplayName(String senderDisplayName) { this.senderDisplayName = senderDisplayName; }
    public String getSenderProfilePictureUrl() { return senderProfilePictureUrl; }
    public void setSenderProfilePictureUrl(String url) { this.senderProfilePictureUrl = url; }
    public String getSentAt() { return sentAt; }
    public void setSentAt(String sentAt) { this.sentAt = sentAt; }
}
