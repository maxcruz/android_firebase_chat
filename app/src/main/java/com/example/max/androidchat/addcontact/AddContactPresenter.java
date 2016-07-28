package com.example.max.androidchat.addcontact;

import com.example.max.androidchat.addcontact.events.AddContactEvent;

public interface AddContactPresenter {

    void onShow();
    void onDestroy();
    void addContact(String email);
    void onEventMainThread(AddContactEvent event);

}
