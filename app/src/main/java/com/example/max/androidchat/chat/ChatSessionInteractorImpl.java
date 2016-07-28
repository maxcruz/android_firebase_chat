package com.example.max.androidchat.chat;

public class ChatSessionInteractorImpl implements ChatSessionInteractor {

    private ChatRepository repository;

    public ChatSessionInteractorImpl() {
        this.repository = new ChatRepositoryImpl();
    }

    @Override
    public void changeConnectionStatus(boolean online) {
        repository.changeConnectionStatus(online);
    }

}
