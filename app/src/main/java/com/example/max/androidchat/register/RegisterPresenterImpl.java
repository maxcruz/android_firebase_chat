package com.example.max.androidchat.register;

import com.example.max.androidchat.lib.EventBus;
import com.example.max.androidchat.lib.GreenRobotEventBus;
import com.example.max.androidchat.register.events.RegisterEvent;
import com.example.max.androidchat.register.ui.RegisterView;

import org.greenrobot.eventbus.Subscribe;

public class RegisterPresenterImpl implements RegisterPresenter {

    private RegisterView registerView;
    private EventBus eventBus;
    private RegisterInteractor registerInteractor;

    public RegisterPresenterImpl(RegisterView registerView) {
        this.registerView = registerView;
        this.eventBus = GreenRobotEventBus.getInstance();
        this.registerInteractor = new RegisterInteractorImpl();
    }

    @Override
    public void onCreate() {
        this.eventBus.register(this);
    }

    @Override
    public void onDestroy() {
        this.registerView = null;
        this.eventBus.unregister(this);
    }

    @Override
    public void registerNewUser(String email, String password) {
        if (registerView != null) {
            this.registerView.disableInputs();
            this.registerView.showProgress();
        }
        registerInteractor.doSignUp(email, password);
    }

    @Override
    @Subscribe
    public void onEventMainThread(RegisterEvent event) {
        switch (event.getEventType()) {
            case RegisterEvent.onSignUpSuccess:
                onSignUpSuccess();
                break;
            case RegisterEvent.onSignUpError:
                onSignUpError(event.getErrorMessage());
                break;
        }
    }

    private void onSignUpSuccess() {
        if (this.registerView != null) {
            this.registerView.newUserSuccess();

        }
    }

    private void onSignUpError(String error) {
        if (this.registerView != null) {
            registerView.hideProgress();
            registerView.enableInputs();
            registerView.newUserError(error);
        }
    }

}
