package com.example.max.androidchat.login;

public interface LoginRepository {

    void signIn(String email, String password);
    void checkSession();

}
