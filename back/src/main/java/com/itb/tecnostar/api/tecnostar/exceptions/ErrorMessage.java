package com.itb.tecnostar.api.tecnostar.exceptions;

import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ErrorMessage {

    private LocalDateTime timestamp;
    private String[] messages;
    private HttpStatus title;
    private int status;

    public ErrorMessage(LocalDateTime timestamp, String[] messages, HttpStatus title) {
        this.timestamp = timestamp;
        this.messages = messages;
        this.title = title;
        this.status = title.value();
    }

    public LocalDateTime getTimestamp() { return timestamp; }
    public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }

    public String[] getMessages() { return messages; }
    public void setMessages(String[] messages) { this.messages = messages; }

    public HttpStatus getTitle() { return title; }
    public void setTitle(HttpStatus title) { this.title = title; }

    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}
