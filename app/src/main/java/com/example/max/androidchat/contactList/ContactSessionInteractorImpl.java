package com.example.max.androidchat.contactList;

public class ContactSessionInteractorImpl implements ContactListSessionInteractor {

    ContactListRepository repository;

    public ContactSessionInteractorImpl() {
        repository = new ContactListRepositoryImpl();
    }

    @Override
    public void signOff() {
        repository.signOff();
    }

    @Override
    public String getCurrentUserEmail() {
        return repository.getCurrentUserEmail();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        repository.changeConnectionStatus(online);
    }

}
