package com.example.max.androidchat.contactList;

public interface ContactListInteractor {

    void subscribe();
    void unsubscribe();
    void destroyListener();
    void removeContact(String email);

}
