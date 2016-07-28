package com.example.max.androidchat.contactList;

import com.example.max.androidchat.contactList.events.ContactListEvent;

public interface ContactListPresenter {

    void onPause();
    void onResume();
    void onCreate();
    void onDestroy();

    void signOff();
    String getCurrentUserEmail();
    void removeContact(String email);
    void onEventMainThread(ContactListEvent event);


}
