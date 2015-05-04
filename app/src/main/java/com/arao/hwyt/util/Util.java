package com.arao.hwyt.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.widget.EditText;

import com.arao.hwyt.R;
import com.arao.hwyt.controller.HwytApplication;
import com.arao.hwyt.controller.activities.ErrorActivity;
import com.arao.hwyt.controller.activities.LoginActivity;
import com.arao.hwyt.controller.broadreceivers.LogoutBroadcastReceiver;

public class Util {

    public static int getAvatarResourceIdFromPosition(Context context, int position) {
        return getImageResourceIdFromString(context, "avatar_" + position);
    }

    public static int getImageResourceIdFromString(Context context, String resourceString) {
        return context.getResources().getIdentifier(resourceString, "drawable", context.getApplicationContext().getPackageName());
    }

    public static String getTextOrEmptyStringFromView(EditText editText) {
        String text = "";

        if (editText != null && editText.getText() != null) {
            text = editText.getText().toString();
        }

        return text;
    }

    public static void displayErrorScreenAndLogout(Activity contextActivity, String errorMessage, boolean isLogged) {
        Intent errorScreenIntent = new Intent(contextActivity, ErrorActivity.class);
        errorScreenIntent.putExtra(contextActivity.getString(R.string.BUNDLE_ERROR_ACTIVITY_ERROR_MESSAGE), errorMessage);
        errorScreenIntent.putExtra(contextActivity.getString(R.string.BUNDLE_ERROR_ACTIVITY_IS_LOGGED), isLogged);
        contextActivity.startActivity(errorScreenIntent);

        if (isLogged) {
            logout(contextActivity, false);
        }
    }

    public static void logout(Activity contextActivity, boolean goToLoginScreen) {
        if (goToLoginScreen) {
            Intent loginScreenIntent = new Intent(contextActivity, LoginActivity.class);
            contextActivity.startActivity(loginScreenIntent);
        }

        SharedPrefHelper.clearLoggedUser(contextActivity);
        ((HwytApplication) contextActivity.getApplication()).clearUserSession();
        // Send logout message to all the activities that listen to this Broadcast message so they are finished
        contextActivity.sendBroadcast(new Intent(LogoutBroadcastReceiver.BROADCAST_ACTION_LOGOUT));
        contextActivity.finish();
    }

    public static boolean isNetworkConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if (ni == null) {
            // There are no active networks.
            return false;
        } else
            return true;
    }
}
