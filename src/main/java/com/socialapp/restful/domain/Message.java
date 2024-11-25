package com.socialapp.restful.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Represents a message object in the social app.
 *
 * @author JulianFisla
 */
@Document(collection = "messages")
public class Message {

    @Id
    private Integer messageId;

    private LocalDateTime createDate;

    private LocalDateTime updateDate;

    private String messageContent;

    private Integer senderId;

    private Integer receiverId;

    // Constructors
    public Message() {
    }

    public Message(Integer messageId, String messageContent, Integer senderId, Integer receiverId) {
        this.messageId = messageId;
        this.createDate = LocalDateTime.now(); // Defaults to current time
        this.updateDate = LocalDateTime.now();   // Defaults to current time
        this.messageContent = messageContent;
        this.senderId = senderId;
        this.receiverId = receiverId;
    }

    // Getters and Setters
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    public LocalDateTime getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(LocalDateTime updateDate) {
        this.updateDate = updateDate;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public Integer getSenderId() {
        return senderId;
    }

    public void setSenderId(Integer senderId) {
        this.senderId = senderId;
    }

    public Integer getReceiverId() {
        return receiverId;
    }

    public void setReceiverId(Integer receiverId) {
        this.receiverId = receiverId;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (messageId != null ? messageId.hashCode() : 0);
        hash = 89 * hash + Objects.hashCode(messageContent);
        hash = 89 * hash + Objects.hashCode(senderId);
        hash = 89 * hash + Objects.hashCode(receiverId);
        hash = 89 * hash + Objects.hashCode(createDate);
        hash = 89 * hash + Objects.hashCode(updateDate);
        return hash;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Message message = (Message) o;
        return Objects.equals(messageId, message.messageId) &&
                Objects.equals(messageContent, message.messageContent) &&
                Objects.equals(senderId, message.senderId) &&
                Objects.equals(receiverId, message.receiverId);
    }

}
