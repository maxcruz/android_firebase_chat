package com.example.max.androidchat.chat;

import com.example.max.androidchat.chat.events.ChatEvent;

public interface ChatPresenter {

    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();
    void setChatRecepient(String recepient);
    void sendMessage(String msg);
    void onEventMainThread(ChatEvent event);

}
