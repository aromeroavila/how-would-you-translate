package com.arao.hwyt.util;

import android.content.Context;
import android.text.TextUtils;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.arao.hwyt.model.User;

/**
 * User: angelromero
 * Date: 09/04/2014
 * Time: 13:44
 */
public class SharedPrefHelper {

    private final static String SHARED_PREFERENCES_KEY = "arao.com.hwyt";

    private final static String SHARED_PREFERENCES_LOGGED_USER = "logged_user";

    /**
     *
     * @param context
     * @param user This has to be different to null. Id and password have to be different to null
     */
    public static void setLoggedUser(Context context, User user) {
        Gson gson = new GsonBuilder().create();
        String jsonUser = gson.toJson(user);
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE).edit().putString(SHARED_PREFERENCES_LOGGED_USER, jsonUser).commit();
    }

    public static User getLoggedUser(Context context) {
        User loggedUser = null;

        String jsonUser = context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE).getString(SHARED_PREFERENCES_LOGGED_USER, null);

        if (!TextUtils.isEmpty(jsonUser)) {
            Gson gson = new Gson();
            loggedUser = gson.fromJson(jsonUser, User.class);
        }

        return loggedUser;
    }

    public static void clearLoggedUser(Context context) {
        context.getSharedPreferences(SHARED_PREFERENCES_KEY, Context.MODE_PRIVATE).edit().remove(SHARED_PREFERENCES_LOGGED_USER).commit();
    }
}
