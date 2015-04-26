package com.arao.hwyt.controller;

import android.app.Application;
import android.content.Context;

import com.arao.hwyt.model.User;

public class HwytApplication extends Application {

    private User mLoggedUser;
    private static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        mContext = this;
    }

    public static Context getContext() {
        return mContext;
    }


    public User getLoggedUser() {
        return mLoggedUser;
    }

    public void setLoggedUser(User loggedUser) {
        mLoggedUser = loggedUser;
    }

    public void clearUserSession() {
        mLoggedUser = null;
    }
}
