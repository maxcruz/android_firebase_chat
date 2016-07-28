package com.example.max.androidchat.register;

import com.example.max.androidchat.register.events.RegisterEvent;

public interface RegisterPresenter {

    void onCreate();
    void onDestroy();
    void registerNewUser(String email, String password);
    void onEventMainThread(RegisterEvent event);

}
