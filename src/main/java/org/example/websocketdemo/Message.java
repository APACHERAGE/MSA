package org.example.websocketdemo;

public class Message {
    private String user;
    private String message;

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }

    public Message(String message, String user) {
        this.message = message;
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public String getUser() {
        return user;
    }
}
