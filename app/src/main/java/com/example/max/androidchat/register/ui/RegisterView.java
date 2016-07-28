package com.example.max.androidchat.register.ui;

public interface RegisterView {

    void enableInputs();
    void disableInputs();
    void showProgress();
    void hideProgress();
    void handleSignUp();
    void handleCancel();
    void newUserSuccess();
    void newUserError(String error);

}
