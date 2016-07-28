package com.example.max.androidchat.login.events;

public class LoginEvent {

    public final static int onSignInError = 0;
    public final static int onSignInSuccess = 1;
        public final static int onFailedToRecoverSession = 2;

    private int eventType;
    private String errorMessage;

    public int getEventType() {
        return eventType;
    }

    public void setEventType(int eventType) {
        this.eventType = eventType;
    }

    public String getErrorMessage() {
        return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
        this.errorMessage = errorMessage;
    }

}
