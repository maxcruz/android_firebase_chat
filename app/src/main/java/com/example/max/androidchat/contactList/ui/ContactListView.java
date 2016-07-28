package com.example.max.androidchat.contactList.ui;

import com.example.max.androidchat.entities.User;

public interface ContactListView {

    void onContactAdded(User user);
    void onContactChanged(User user);
    void onContactRemoved(User user);

}
