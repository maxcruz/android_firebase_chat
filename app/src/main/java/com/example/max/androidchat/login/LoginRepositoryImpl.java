package com.example.max.androidchat.login;

import android.support.annotation.NonNull;

import com.example.max.androidchat.domain.FirebaseHelper;
import com.example.max.androidchat.entities.User;
import com.example.max.androidchat.lib.EventBus;
import com.example.max.androidchat.lib.GreenRobotEventBus;
import com.example.max.androidchat.login.events.LoginEvent;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

public class LoginRepositoryImpl implements LoginRepository {

    private FirebaseHelper helper;
    private DatabaseReference myUserReference;

    public LoginRepositoryImpl() {
        this.helper = FirebaseHelper.getInstance();
        this.myUserReference = helper.getMyUserReference();
    }

    @Override
    public void signIn(String email, String password) {
        if (!email.isEmpty() && !password.isEmpty()) {
            final FirebaseAuth auth = FirebaseAuth.getInstance();
            auth.signInWithEmailAndPassword(email, password).addOnSuccessListener(new OnSuccessListener<AuthResult>() {

                @Override
                public void onSuccess(AuthResult authResult) {
                    initSignIn();
                }

            }).addOnFailureListener(new OnFailureListener() {

                @Override
                public void onFailure(@NonNull Exception e) {
                    postEvent(LoginEvent.onSignInError, e.getMessage());
                }

            });
        } else {
            postEvent(LoginEvent.onSignInError);
        }
    }

    @Override
    public void checkSession() {
        FirebaseAuth auth = FirebaseAuth.getInstance();
        if (auth.getCurrentUser() != null) {
            initSignIn();
        } else {
            postEvent(LoginEvent.onFailedToRecoverSession);
        }
    }

    private void initSignIn() {
        myUserReference = helper.getMyUserReference();
        myUserReference.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                User currentUser = dataSnapshot.getValue(User.class);
                if (currentUser == null) {
                    registerNewUser();
                }
                helper.changeUserConnectionStatus(User.ONLINE);
                postEvent(LoginEvent.onSignInSuccess);
            }

            @Override
            public void onCancelled(DatabaseError firebaseError) {
            }

        });
    }

    private void registerNewUser() {
        String email = helper.getAuthUserEmail();
        if (email != null) {
            User currentUser = new User();
            currentUser.setEmail(email);
            myUserReference.setValue(currentUser);
        }
    }

    private void postEvent(int type, String errorMessage) {
        LoginEvent loginEvent = new LoginEvent();
        loginEvent.setEventType(type);
        if (errorMessage != null) {
            loginEvent.setErrorMessage(errorMessage);
        } else {
            loginEvent.setErrorMessage("");
        }
        EventBus eventBus = GreenRobotEventBus.getInstance();
        eventBus.post(loginEvent);
    }

    private void postEvent(int type) {
        postEvent(type, null);
    }

}
