package com.example.entity;

import javax.persistence.*;

@Entity  // Tells Spring this is a database table
@Table(name = "account")
public class Account {
    
    @Id  // This is the primary key
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Auto-increment
    @Column(name = "accountId")
    private Integer accountId;
    
    @Column(name = "username", nullable = false, unique = true)
    private String username;
    
    @Column(name = "password")
    private String password;
    
    // Default constructor (required by JPA)
    public Account() {}
    
    // Constructor for creating accounts
    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }
    
    // Getters and Setters (required for Spring to access fields)
    public Integer getAccountId() {
        return accountId;
    }
    
    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
}