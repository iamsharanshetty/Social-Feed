package com.example.entity;

import javax.persistence.*;

@Entity
@Table(name = "message")
public class Message {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "messageId")
    private Integer messageId;
    
    @Column(name = "postedBy")
    private Integer postedBy;  // Foreign key to Account
    
    @Column(name = "messageText", length = 255)
    private String messageText;
    
    @Column(name = "timePostedEpoch")
    private Long timePostedEpoch;
    
    // Default constructor
    public Message() {}
    
    // Constructor for creating messages
    public Message(Integer postedBy, String messageText, Long timePostedEpoch) {
        this.postedBy = postedBy;
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