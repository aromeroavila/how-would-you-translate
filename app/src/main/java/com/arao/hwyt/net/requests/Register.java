package com.arao.hwyt.net.requests;

import com.android.volley.Request;
import com.arao.hwyt.model.User;
import com.arao.hwyt.util.PasswordEncrypter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.UnsupportedEncodingException;
import java.security.GeneralSecurityException;

public class Register extends BaseRequest<Integer> {

    private User mUser;

    public Register(User user) {
        mUser = user;
    }

    @Override
    public String getUrl() {
        return mUrlProvider.getRegisterUrl();
    }

    @Override
    public int getMethod() {
        return Request.Method.POST;
    }

    @Override
    public Class<Integer> getClazz() {
        return Integer.class;
    }

    private static String getUserJsonWithEncryptedPassword(User user) throws GeneralSecurityException, UnsupportedEncodingException {
        user.setPassword(PasswordEncrypter.encrypt(user.getPassword()));
        Gson gson = new GsonBuilder().create();
        return gson.toJson(user, User.class);
    }
}
