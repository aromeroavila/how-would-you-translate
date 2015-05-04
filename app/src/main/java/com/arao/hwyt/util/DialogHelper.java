package com.arao.hwyt.util;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;

import com.arao.hwyt.R;
import com.arao.hwyt.controller.activities.LoginActivity;

/**
 * User: angelromero
 * Date: 22/05/2014
 * Time: 09:28
 */
public class DialogHelper {

    public static void displayLogoutConfirmationDialog(final Activity contextActivity) {
        new AlertDialog.Builder(contextActivity)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.logout_dialog_title)
                .setMessage(R.string.logout_dialog_message)
                .setPositiveButton(R.string.dialog_option_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Util.logout(contextActivity, true);
                    }
                })
                .setNegativeButton(R.string.dialog_option_no, null)
                .setCancelable(false)
                .show();
    }

    public static void displayExitRegistrationConfirmationDialog(final Activity contextActivity) {
        new AlertDialog.Builder(contextActivity)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle(R.string.leave_registration_title)
                .setMessage(R.string.leave_registration_description)
                .setPositiveButton(R.string.dialog_option_yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent loginScreenIntent = new Intent(contextActivity, LoginActivity.class);
                        contextActivity.startActivity(loginScreenIntent);
                        contextActivity.finish();
                    }

                })
                .setNegativeButton(R.string.dialog_option_no, null)
                .setCancelable(false)
                .show();
    }

    public static ProgressDialog createAndDisplayCommProgressDialog(final Activity contextActivity,
                                                                    String title, String message) {
        ProgressDialog progressDialog = new ProgressDialog(contextActivity);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setCancelable(false);
        progressDialog.setIndeterminate(true);
        progressDialog.show();

        return progressDialog;
    }
}
