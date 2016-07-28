package com.example.max.androidchat.addcontact.ui;


import com.example.max.androidchat.addcontact.AddContactInteractor;
import com.example.max.androidchat.addcontact.AddContactInteractorImpl;
import com.example.max.androidchat.addcontact.AddContactPresenter;
import com.example.max.androidchat.addcontact.events.AddContactEvent;
import com.example.max.androidchat.lib.EventBus;
import com.example.max.androidchat.lib.GreenRobotEventBus;

import org.greenrobot.eventbus.Subscribe;

public class AddContactPresenterImpl implements AddContactPresenter {

    private EventBus eventBus;
    private AddContactView view;
    private AddContactInteractor interactor;

    public AddContactPresenterImpl(AddContactView view) {
        this.view = view;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.interactor = new AddContactInteractorImpl();
    }

    @Override
    public void onShow() {
        eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        view = null;
        eventBus.unregister(this);
    }

    @Override
    public void addContact(String email) {
        if (view != null) {
            view.hideInput();
            view.showProgress();
        }
        interactor.execute(email);
    }

    @Override
    @Subscribe
    public void onEventMainThread(AddContactEvent event) {
        if (view != null) {
            view.hideProgress();
            view.showInput();
            if (event.isError()) {
                view.contactNotAdded();
            } else {
                view.contactAdded();
            }
        }

    }
}
