/**
 * Created by balex on 19.09.2016.
 */
package com.shiba.chatbox.models;

public class ChatModel {
    private String nickName;
    private MessageModel messageModel;
    ////

    public MessageModel getMessageModel() {
        return messageModel;
    }

    public void setMessage(MessageModel message) {
        this.messageModel = message;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String username) {
        this.nickName = username;
    }
}

