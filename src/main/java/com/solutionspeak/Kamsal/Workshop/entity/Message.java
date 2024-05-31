package com.solutionspeak.Kamsal.Workshop.entity;

import com.solutionspeak.Kamsal.Workshop.form.MessageForm;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "messages")
@NoArgsConstructor
public class Message {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "content")
    private String content;

    @Column(name = "timestamp")
    private LocalDateTime timestamp;

    @Column(name = "sender_id")
    private int senderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false, updatable = false, insertable = false)
    private User sender;

    @Column(name = "recipient_id")
    private int recipientId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "recipient_id", insertable = false, updatable = false, nullable = false)
    private User recipient;

    public Message(final MessageForm form) {
        this.content = form.getContent();
        this.timestamp = form.getTimestamp();
        this.senderId = form.getSenderId();
        this.recipientId = form.getRecipientId();
    }

    public void updateMessage(final MessageForm form) {
        this.content = form.getContent();
        this.timestamp = form.getTimestamp();
        this.senderId = form.getSenderId();
        this.recipientId = form.getRecipientId();
    }

}
