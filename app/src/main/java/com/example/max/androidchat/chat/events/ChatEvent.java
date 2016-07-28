package com.example.max.androidchat.chat.events;

import com.example.max.androidchat.entities.ChatMessage;

public class ChatEvent {

    private ChatMessage message;

    public void setMessage(ChatMessage message) {
        this.message = message;
    }

    public ChatMessage getMessage() {
        return message;
    }

}
