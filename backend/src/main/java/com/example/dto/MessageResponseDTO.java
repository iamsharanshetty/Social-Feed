package com.example.dto;

public class MessageResponseDTO {
    private Integer messageId;
    private Integer postedBy;
    private String username;  // This is what we want to display
    private String messageText;
    private Long timePostedEpoch;
    
    // Default constructor
    public MessageResponseDTO() {}
    
    // Constructor to convert from Message entity
    public MessageResponseDTO(Integer messageId, Integer postedBy, String username, 
                             String messageText, Long timePostedEpoch) {
        this.messageId = messageId;
        this.postedBy = postedBy;
        this.username = username;
        this.messageText = messageText;
        this.timePostedEpoch = timePostedEpoch;
    }
    
    // Getters and Setters
    public Integer getMessageId() {
        return messageId;
    }
    
    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }
    
    public Integer getPostedBy() {
        return postedBy;
    }
    
    public void setPostedBy(Integer postedBy) {
        this.postedBy = postedBy;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getMessageText() {
        return messageText;
    }
    
    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    
    public Long getTimePostedEpoch() {
        return timePostedEpoch;
    }
    
    public void setTimePostedEpoch(Long timePostedEpoch) {
        this.timePostedEpoch = timePostedEpoch;
    }
}