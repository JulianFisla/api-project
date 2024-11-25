package com.socialapp.restful.repository;

import com.socialapp.restful.domain.Message;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

/**
 * Message Repository for CRUD operations and custom queries
 *
 * @author JulianFisla
 */
@Repository
public interface MessageRepository extends MongoRepository<Message, Integer> {

    /**
     * Find a message by its ID
     * @param messageId the ID of the message
     * @return an Optional containing the message if found
     */
    Optional<Message> findById(Integer messageId);

    /**
     * Check if a message exists by its ID
     * @param messageId the ID of the message
     * @return true if the message exists, false otherwise
     */
    boolean existsById(Integer messageId);

    /**
     * Delete a message by its ID
     * @param messageId the ID of the message
     */
    void deleteById(Integer messageId);

    /**
     * Find all messages sent by a specific sender
     * @param senderId the ID of the sender
     * @return a list of messages sent by the sender
     */
    List<Message> findAllBySenderId(Integer senderId);

    /**
     * Find all messages received by a specific receiver
     * @param receiverId the ID of the receiver
     * @return a list of messages received by the receiver
     */
    List<Message> findAllByReceiverId(Integer receiverId);

    /**
     * Find all messages containing specific content
     * @param content the content to search for
     * @return a list of messages containing the specified content
     */
    List<Message> findAllByMessageContentContaining(String content);

    /**
     * Find all messages created before a specific date
     * @param createDate the creation date threshold
     * @return a list of messages created before the specified date
     */
    List<Message> findAllByCreateDateBefore(LocalDateTime createDate);

    /**
     * Find all messages updated after a specific date
     * @param updateDate the update date threshold
     * @return a list of messages updated after the specified date
     */
    List<Message> findAllByUpdateDateAfter(LocalDateTime updateDate);

    /**
     * Delete all messages sent by a specific sender
     * @param senderId the ID of the sender
     */
    void deleteAllBySenderId(Integer senderId);

    /**
     * Delete all messages received by a specific receiver
     * @param receiverId the ID of the receiver
     */
    void deleteAllByReceiverId(Integer receiverId);
}
