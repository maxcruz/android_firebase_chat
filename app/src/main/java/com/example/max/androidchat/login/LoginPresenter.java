package com.example.max.androidchat.login;

import com.example.max.androidchat.login.events.LoginEvent;

public interface LoginPresenter {

    void onCreate();
    void onDestroy();
    void checkForAuthenticatedUser();
    void validateLogin(String email, String password);
    void onEventMainThread(LoginEvent event);

}
