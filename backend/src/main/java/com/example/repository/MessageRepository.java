package com.example.repository;

import com.example.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Integer> {
    
    // Find all messages by a specific user
    List<Message> findByPostedBy(Integer postedBy);
    
    // Spring automatically provides:
    // save(Message message) - saves/updates message
    // findById(Integer id) - finds by ID  
    // findAll() - gets all messages
    // deleteById(Integer id) - deletes by ID
}