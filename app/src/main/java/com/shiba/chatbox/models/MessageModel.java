package com.shiba.chatbox.models;

public class MessageModel {

    @Deprecated //TODO: deja avem sender-ul sub forma "String nickName" în clasa ChatModel ce conține clasa MessageModel
    private String sender;
    private String message;
    private String clock;
    private String to;
    private String receiver;

    public MessageModel(String sender, String message, String clock, String to, String receiver) {
        this.sender = sender;
        this.message = message;
        this.clock = clock;
        this.to = to;
        this.receiver = receiver;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getClock() {
        return clock;
    }

    public void setClock(String clock) {
        this.clock = clock;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }
}