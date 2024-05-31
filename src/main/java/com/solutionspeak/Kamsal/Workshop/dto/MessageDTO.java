package com.solutionspeak.Kamsal.Workshop.dto;

import com.solutionspeak.Kamsal.Workshop.entity.Message;
import com.solutionspeak.Kamsal.Workshop.entity.User;
import lombok.Builder;
import lombok.Getter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Builder
public class MessageDTO implements Serializable {

    private int id;

    private String content;

    private LocalDateTime timestamp;

    private int senderId;

    private UserDTO sender;

    private int recipientId;

    private UserDTO recipient;


    public static MessageDTO build(final Message message) {
        return MessageDTO.builder()
                .id(message.getId())
                .content(message.getContent())
                .timestamp(message.getTimestamp())
                .senderId(message.getSenderId())
                .recipientId(message.getRecipientId())
                .build();
    }

    public static MessageDTO build(final Message message,
                                   final UserDTO sender,
                                   final UserDTO recipient) {
        return MessageDTO.builder()
                .id(message.getId())
                .content(message.getContent())
                .timestamp(message.getTimestamp())
                .senderId(message.getSenderId())
                .sender(sender)
                .recipientId(message.getRecipientId())
                .recipient(recipient)
                .build();
    }

}