package com.example.max.androidchat.chat;

public interface ChatRepository {

    void sendMessage(String msg);
    void setRecipient(String recipient);
    void subscribe();
    void unsubscribe();
    void destroyListener();
    void changeConnectionStatus(boolean online);

}
