package com.example.max.androidchat.chat.ui;

import com.example.max.androidchat.entities.ChatMessage;

public interface ChatView {

    void onMessageReceived(ChatMessage msg);

}
