package com.example.max.androidchat.login.ui;

public interface LoginView {

    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();
    void handleGoSignUp();
    void handleSignIn();
    void navigateToMainScreen();
    void loginError(String error);

}
