package com.example.max.androidchat;

import android.app.Application;

//import com.google.firebase.database.Firebase;
import com.google.firebase.database.FirebaseDatabase;

public class AndroidChatApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupFirebase();
    }

    private void setupFirebase() {
        //Firebase.setAndroidContext(this);
        //Firebase.getDefaultConfig().setPersistenceEnabled(true);
        FirebaseDatabase.getInstance().setPersistenceEnabled(true);
    }
}
