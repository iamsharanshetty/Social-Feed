package com.example.service;

import com.example.dto.MessageResponseDTO;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {
    
    @Autowired
    private MessageRepository messageRepository;
    
    @Autowired
    private AccountService accountService;  // To check if user exists
    
    /**
     * Create a new message
     * Returns the message if successful, null if unsuccessful
     */
    public Message createMessage(Message message) {
        // Validation rules:
        // 1. Message text cannot be blank
        if (message.getMessageText() == null || message.getMessageText().trim().isEmpty()) {
            return null;
        }
        
        // 2. Message text cannot be over 255 characters
        if (message.getMessageText().length() > 255) {
            return null;
        }
        
        // 3. Posted by must refer to existing user
        if (message.getPostedBy() == null || !accountService.accountExists(message.getPostedBy())) {
            return null;
        }
        
        // Set timestamp to current time
        message.setTimePostedEpoch(System.currentTimeMillis());
        
        // Save and return the message
        return messageRepository.save(message);
    }
    
    /**
     * Get all messages with usernames
     */
    public List<MessageResponseDTO> getAllMessagesWithUsernames() {
        List<Message> messages = messageRepository.findAll();
        return messages.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Get all messages (original method for backward compatibility)
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }
    
    /**
     * Get message by ID with username
     */
    public MessageResponseDTO getMessageByIdWithUsername(Integer messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        if (message.isPresent()) {
            return convertToResponseDTO(message.get());
        }
        return null;
    }
    
    /**
     * Get message by ID (original method)
     * Returns the message if found, null if not found
     */
    public Message getMessageById(Integer messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        return message.orElse(null);
    }
    
    /**
     * Delete message by ID
     * Returns 1 if deleted, 0 if message didn't exist
     */
    public int deleteMessage(Integer messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        if (message.isPresent()) {
            messageRepository.deleteById(messageId);
            return 1;  // One row affected
        }
        return 0;  // No rows affected
    }
    
    /**
     * Update message text
     * Returns 1 if updated, 0 if unsuccessful
     */
    public int updateMessage(Integer messageId, String newMessageText) {
        // Validation: new text cannot be blank or over 255 characters
        if (newMessageText == null || newMessageText.trim().isEmpty() || 
            newMessageText.length() > 255) {
            return 0;
        }
        
        Optional<Message> messageOptional = messageRepository.findById(messageId);
        if (messageOptional.isPresent()) {
            Message message = messageOptional.get();
            message.setMessageText(newMessageText);
            messageRepository.save(message);
            return 1;  // One row affected
        }
        return 0;  // Message not found
    }
    
    /**
     * Get all messages by a specific user with usernames
     */
    public List<MessageResponseDTO> getMessagesByUserWithUsernames(Integer accountId) {
        List<Message> messages = messageRepository.findByPostedBy(accountId);
        return messages.stream()
                .map(this::convertToResponseDTO)
                .collect(Collectors.toList());
    }
    
    /**
     * Get all messages by a specific user (original method)
     */
    public List<Message> getMessagesByUser(Integer accountId) {
        return messageRepository.findByPostedBy(accountId);
    }
    
    /**
     * Helper method to convert Message entity to MessageResponseDTO
     */
    private MessageResponseDTO convertToResponseDTO(Message message) {
        String username = "Unknown User"; // Default value
        
        // Try to get username from the account relationship first
        if (message.getAccount() != null) {
            username = message.getAccount().getUsername();
        } else {
            // Fallback: manually fetch the account if relationship didn't work
            try {
                Account account = accountService.getAccountById(message.getPostedBy());
                if (account != null) {
                    username = account.getUsername();
                }
            } catch (Exception e) {
                // Keep default "Unknown User" if something goes wrong
            }
        }
        
        return new MessageResponseDTO(
            message.getMessageId(),
            message.getPostedBy(),
            username,
            message.getMessageText(),
            message.getTimePostedEpoch()
        );
    }
}