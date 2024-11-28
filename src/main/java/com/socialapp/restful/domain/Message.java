package com.socialapp.restful.domain;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDateTime;
import java.util.Date;
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

    private Date createDate;

    private Date updateDate;

    private String messageContent;

    private String senderUsername;

    private String receiverUsername;

    // Constructors
    public Message() {
    }

    public Message(Integer messageId, String messageContent, String senderUsername, String receiverUsername) {
        this.messageId = messageId;
        this.createDate = new Date(); // Defaults to current time
        this.updateDate = new Date();   // Defaults to current time
        this.messageContent = messageContent;
        this.senderUsername = senderUsername;
        this.receiverUsername = receiverUsername;
    }

    // Getters and Setters
    public Integer getMessageId() {
        return messageId;
    }

    public void setMessageId(Integer messageId) {
        this.messageId = messageId;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public String getMessageContent() {
        return messageContent;
    }

    public void setMessageContent(String messageContent) {
        this.messageContent = messageContent;
    }

    public String getSenderUsername() {
        return senderUsername;
    }

    public void setSenderId(String senderUsername) {
        this.senderUsername = senderUsername;
    }

    public String getReceiverUsername() {
        return receiverUsername;
    }

    public void setReceiverId(String receiverUsername) {
        this.receiverUsername = receiverUsername;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + (messageId != null ? messageId.hashCode() : 0);
        hash = 89 * hash + Objects.hashCode(messageContent);
        hash = 89 * hash + Objects.hashCode(senderUsername);
        hash = 89 * hash + Objects.hashCode(receiverUsername);
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
                Objects.equals(senderUsername, message.senderUsername) &&
                Objects.equals(receiverUsername, message.receiverUsername);
    }

}
