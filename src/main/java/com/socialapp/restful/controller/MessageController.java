package com.socialapp.restful.controller;

import com.socialapp.restful.domain.Message;
import com.socialapp.restful.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * MessageController for managing message resources.
 *
 * @author JulianFisla
 */
@RestController
@RequestMapping("/messages")
public class MessageController {

    @Autowired
    private MessageRepository messageRepository;

    /**
     * Create a new message
     * @param message
     * @return
     */
    @PostMapping
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        // If messageId is not provided, generate it
        if (message.getMessageId() == null || message.getMessageId() == 0) {
            message.setMessageId(generateUniqueMessageId());
        }
        // Set default timestamps if not provided
        if (message.getCreateDate() == null) {
            message.setCreateDate(LocalDateTime.now());
        }
        if (message.getUpdateDate() == null) {
            message.setUpdateDate(LocalDateTime.now());
        }
        Message savedMessage = messageRepository.save(message);
        return new ResponseEntity<>(savedMessage, HttpStatus.CREATED);
    }

    private int generateUniqueMessageId() {
        // Simple method to generate a unique message ID
        return (int) (messageRepository.count() + 1);
    }

    /**
     * Retrieve all messages
     * @return
     */
    @GetMapping
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageRepository.findAll();
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    /**
     * Retrieve a message by ID
     * @param messageId
     * @return
     */
    @GetMapping("/{messageId}")
    public ResponseEntity<Message> getMessageById(@PathVariable int messageId) {
        Optional<Message> message = messageRepository.findById(messageId);
        return message.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Update an existing message
     * @param messageId
     * @param messageDetails
     * @return
     */
    @PutMapping("/{messageId}")
    public ResponseEntity<Message> updateMessage(@PathVariable int messageId, @RequestBody Message messageDetails) {
        Optional<Message> optionalMessage = messageRepository.findById(messageId);
        if (!optionalMessage.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Message message = optionalMessage.get();
        message.setMessageContent(messageDetails.getMessageContent());
        message.setSenderId(messageDetails.getSenderId());
        message.setReceiverId(messageDetails.getReceiverId());
        message.setUpdateDate(LocalDateTime.now());
        Message updatedMessage = messageRepository.save(message);
        return new ResponseEntity<>(updatedMessage, HttpStatus.OK);
    }

    /**
     * Delete a message
     * @param messageId
     * @return
     */
    @DeleteMapping("/{messageId}")
    public ResponseEntity<Void> deleteMessage(@PathVariable int messageId) {
        if (!messageRepository.existsById(messageId)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        messageRepository.deleteById(messageId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    /**
     * Retrieve all messages sent by a specific sender
     * @param senderId
     * @return
     */
    @GetMapping("/sender/{senderId}")
    public ResponseEntity<List<Message>> getMessagesBySender(@PathVariable int senderId) {
        List<Message> messages = messageRepository.findAllBySenderId(senderId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }

    /**
     * Retrieve all messages received by a specific receiver
     * @param receiverId
     * @return
     */
    @GetMapping("/receiver/{receiverId}")
    public ResponseEntity<List<Message>> getMessagesByReceiver(@PathVariable int receiverId) {
        List<Message> messages = messageRepository.findAllByReceiverId(receiverId);
        return new ResponseEntity<>(messages, HttpStatus.OK);
    }
}
