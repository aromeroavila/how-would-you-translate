package com.arao.hwyt.controller.broadreceivers;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * User: angelromero
 * Date: 21/05/2014
 * Time: 14:11
 */
public class LogoutBroadcastReceiver extends BroadcastReceiver {

    public final static String BROADCAST_ACTION_LOGOUT = "broadcast_action_logout";

    private Activity mActivity;

    public LogoutBroadcastReceiver(Activity activity) {
        super();
        mActivity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(BROADCAST_ACTION_LOGOUT)) {
            mActivity.finish();
        }
    }
}
