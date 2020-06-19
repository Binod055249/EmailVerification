package com.example.emailverification;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("9B6NE3xajzsz6dVZ3DKBA8NHCownVByd5QArslaP")
                .clientKey("34czQ27r2hZuox4pLypIOjfXvWAD9wwvLE6ObAil")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }

}
