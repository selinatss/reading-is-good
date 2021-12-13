package com.bookstore.response;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode
public class Message {
    private int statusCode;
    private String message;

    public Message(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "{" +
                ", message='" + message + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}
