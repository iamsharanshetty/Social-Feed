package com.example.controller;

import com.example.dto.MessageResponseDTO;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController  // Tells Spring this handles web requests
public class SocialMediaController {
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private MessageService messageService;
    
    /**
     * 1. User Registration
     * POST /register
     */
    @PostMapping("/register")
    public ResponseEntity<Account> register(@RequestBody Account account) {
        Account registeredAccount = accountService.registerAccount(account);
        
        if (registeredAccount != null) {
            // Success - return 200 OK with account data
            return ResponseEntity.ok(registeredAccount);
        } else {
            // Check if it's a duplicate username
            if (accountService.accountExists(account.getAccountId())) {
                return ResponseEntity.status(409).build();  // 409 Conflict
            } else {
                return ResponseEntity.status(400).build();  // 400 Bad Request
            }
        }
    }
    
    /**
     * 2. User Login  
     * POST /login
     */
    @PostMapping("/login")
    public ResponseEntity<Account> login(@RequestBody Account account) {
        Account loggedInAccount = accountService.loginAccount(
            account.getUsername(), account.getPassword());
        
        if (loggedInAccount != null) {
            return ResponseEntity.ok(loggedInAccount);  // 200 OK
        } else {
            return ResponseEntity.status(401).build();  // 401 Unauthorized
        }
    }
    
    /**
     * 3. Create Message
     * POST /messages
     */
    @PostMapping("/messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        Message createdMessage = messageService.createMessage(message);
        
        if (createdMessage != null) {
            return ResponseEntity.ok(createdMessage);  // 200 OK
        } else {
            return ResponseEntity.status(400).build();  // 400 Bad Request
        }
    }
    
    /**
     * 4. Get All Messages WITH USERNAMES
     * GET /messages
     */
    @GetMapping("/messages")
    public ResponseEntity<List<MessageResponseDTO>> getAllMessages() {
        List<MessageResponseDTO> messages = messageService.getAllMessagesWithUsernames();
        return ResponseEntity.ok(messages);  // Always 200 OK
    }
    
    /**
     * 5. Get Message by ID WITH USERNAME
     * GET /messages/{messageId}
     */
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<MessageResponseDTO> getMessageById(@PathVariable Integer messageId) {
        MessageResponseDTO message = messageService.getMessageByIdWithUsername(messageId);
        
        if (message != null) {
            return ResponseEntity.ok(message);
        } else {
            return ResponseEntity.ok().build();  // 200 OK with empty body
        }
    }
    
    /**
     * 6. Delete Message
     * DELETE /messages/{messageId}
     */
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable Integer messageId) {
        int rowsAffected = messageService.deleteMessage(messageId);
        
        if (rowsAffected > 0) {
            return ResponseEntity.ok(rowsAffected);  // 200 OK with count
        } else {
            return ResponseEntity.ok().build();  // 200 OK with empty body
        }
    }
    
    /**
     * 7. Update Message
     * PATCH /messages/{messageId}
     */
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<Integer> updateMessage(@PathVariable Integer messageId, 
                                               @RequestBody Message message) {
        int rowsAffected = messageService.updateMessage(messageId, message.getMessageText());
        
        if (rowsAffected > 0) {
            return ResponseEntity.ok(rowsAffected);  // 200 OK
        } else {
            return ResponseEntity.status(400).build();  // 400 Bad Request
        }
    }
    
    /**
     * 8. Get Messages by User WITH USERNAMES
     * GET /accounts/{accountId}/messages
     */
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<MessageResponseDTO>> getMessagesByUser(@PathVariable Integer accountId) {
        List<MessageResponseDTO> messages = messageService.getMessagesByUserWithUsernames(accountId);
        return ResponseEntity.ok(messages);  // Always 200 OK
    }
}