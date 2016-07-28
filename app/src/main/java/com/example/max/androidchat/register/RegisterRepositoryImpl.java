package com.example.max.androidchat.register;

import android.support.annotation.NonNull;

import com.example.max.androidchat.lib.EventBus;
import com.example.max.androidchat.lib.GreenRobotEventBus;
import com.example.max.androidchat.register.events.RegisterEvent;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class RegisterRepositoryImpl implements RegisterRepository {

    @Override
    public void signUp(final String email, final String password) {
        if (!email.isEmpty() && !password.isEmpty()) {
            final FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.createUserWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {

                @Override
                public void onSuccess(AuthResult authResult) {
                    postEvent(RegisterEvent.onSignUpSuccess);
                }

            }). addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception e) {
                    postEvent(RegisterEvent.onSignUpError);
                }

            });
        } else {
            postEvent(RegisterEvent.onSignUpError);
        }
    }

    private void postEvent(int type, String errorMessage) {
        RegisterEvent registerEvent = new RegisterEvent();
        registerEvent.setEventType(type);
        if (errorMessage != null) {
            registerEvent.setErrorMessage(errorMessage);
        } else {
            registerEvent.setErrorMessage("");
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(registerEvent);
    }

    private void postEvent(int type) {
        postEvent(type, null);
    }

}
