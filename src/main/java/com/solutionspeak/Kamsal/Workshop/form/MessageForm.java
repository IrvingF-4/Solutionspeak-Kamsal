package com.solutionspeak.Kamsal.Workshop.form;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class MessageForm implements Serializable {

    @Size(max = 255)
    @NotBlank
    private String content;

    @FutureOrPresent
    private LocalDateTime timestamp;

    @Positive
    private int senderId;

    @Positive
    private int recipientId;
}