package com.example.service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service  // Tells Spring this contains business logic
public class AccountService {
    
    @Autowired  // Spring automatically provides the repository
    private AccountRepository accountRepository;
    
    /**
     * Register a new account
     * Returns the account if successful, null if unsuccessful
     */
    public Account registerAccount(Account account) {
        // Validation rules:
        // 1. Username cannot be blank
        if (account.getUsername() == null || account.getUsername().trim().isEmpty()) {
            return null;
        }
        
        // 2. Password must be at least 4 characters
        if (account.getPassword() == null || account.getPassword().length() < 4) {
            return null;
        }
        
        // 3. Username must be unique
        Optional<Account> existingAccount = accountRepository.findByUsername(account.getUsername());
        if (existingAccount.isPresent()) {
            return null;  // Username already exists
        }
        
        // If all validations pass, save the account
        return accountRepository.save(account);
    }
    
    /**
     * Login - verify username and password match
     * Returns the account if successful, null if unsuccessful
     */
    public Account loginAccount(String username, String password) {
        Optional<Account> account = accountRepository.findByUsername(username);
        
        // Check if account exists and password matches
        if (account.isPresent() && account.get().getPassword().equals(password)) {
            return account.get();
        }
        
        return null;  // Login failed
    }
    
    /**
     * Check if an account exists by ID
     */
    public boolean accountExists(Integer accountId) {
        return accountRepository.findById(accountId).isPresent();
    }
}