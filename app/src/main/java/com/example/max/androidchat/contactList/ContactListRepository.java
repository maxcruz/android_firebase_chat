package com.example.max.androidchat.contactList;

public interface ContactListRepository {

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void destroyListener();
    void subscribeToContactListEvents();
    void unsubscribeFromContactListEvents();
    void changeConnectionStatus(boolean online);

}
