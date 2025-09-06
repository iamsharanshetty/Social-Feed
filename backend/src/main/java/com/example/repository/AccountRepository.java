package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository  // Tells Spring this handles data access
public interface AccountRepository extends JpaRepository<Account, Integer> {
    
    // Spring automatically creates this method based on the name!
    // Finds account by username
    Optional<Account> findByUsername(String username);
    
    // Spring also creates these automatically:
    // save(Account account) - saves/updates account
    // findById(Integer id) - finds by ID
    // findAll() - gets all accounts
    // deleteById(Integer id) - deletes by ID
}