package com.example.max.androidchat.login;

public interface LoginInteractor {

    void checkSession();
    void doSignIn(String email, String password);

}
