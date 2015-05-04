package com.arao.hwyt.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import com.arao.hwyt.R;
import com.arao.hwyt.controller.HwytApplication;
import com.arao.hwyt.model.User;
import com.arao.hwyt.util.SharedPrefHelper;
import com.arao.hwyt.util.Util;
import com.arao.hwyt.view.ui.SplashActivityUi;

import static com.arao.hwyt.view.ui.UiModule.splashActivityUi;

public class SplashActivity extends Activity {

    private final SplashActivityUi mSplashActivityUi;

    private TextView mProgressText;

    private InternetConnectionChecker mInternetConnectionChecker;
    private UserSessionChecker mUserSessionChecker;


    public SplashActivity() {
        this(splashActivityUi());
    }

    public SplashActivity(SplashActivityUi splashActivityUi) {
        mSplashActivityUi = splashActivityUi;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        mSplashActivityUi.initialise(findViewById(android.R.id.content));

        mProgressText = (TextView) findViewById(R.id.progress_message_tv);
        mProgressText.setText(getString(R.string.progress_checking_connection));
    }

    @Override
    protected void onResume() {
        super.onResume();

        mInternetConnectionChecker = new InternetConnectionChecker();
        mInternetConnectionChecker.start();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mInternetConnectionChecker != null && mInternetConnectionChecker.isAlive()) {
            mInternetConnectionChecker.interrupt();
        }

        if (mUserSessionChecker != null && mUserSessionChecker.isAlive()) {
            mUserSessionChecker.interrupt();
        }
    }

    private void checkUserSession() {
        mProgressText.setText(getString(R.string.progress_checking_logged_user));

        mUserSessionChecker = new UserSessionChecker();
        mUserSessionChecker.start();
    }


    private class InternetConnectionChecker extends Thread {
        @Override
        public void run() {

            try {
                sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (Util.isNetworkConnected(SplashActivity.this)) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        checkUserSession();
                    }
                });
            } else {
                runOnUiThread(new ErrorScreenLoader(getString(R.string.no_internet_connection_error_message)));
            }
        }
    }

    // TODO AuthenticationRequest here. If success run this code
    private void authenticateUser(User user) {
        mProgressText.setText(getString(R.string.progress_authenticating_logged_user));
        ((HwytApplication) getApplication()).setLoggedUser(user);

        Intent goToHomeScreenIntent = new Intent(SplashActivity.this, HomeActivity.class);
        startActivity(goToHomeScreenIntent);
        finish();
    }

    private class UserSessionChecker extends Thread {
        @Override
        public void run() {
            final User loggedUser = SharedPrefHelper.getLoggedUser(SplashActivity.this);

            if (loggedUser != null) {
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        authenticateUser(loggedUser);
                    }
                });
            } else {
                runOnUiThread(new ActivityLoader(LoginActivity.class));
            }
        }
    }

    private class ActivityLoader implements Runnable {

        private Class mClazz;

        public ActivityLoader(Class clazz) {
            mClazz = clazz;
        }

        @Override
        public void run() {
            Intent intent = new Intent(SplashActivity.this, mClazz);
            startActivity(intent);
            overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
            SplashActivity.this.finish();
        }
    }

    private class ErrorScreenLoader implements Runnable {

        private String mErrorMessage;

        public ErrorScreenLoader(String errorMessage) {
            mErrorMessage = errorMessage;
        }

        @Override
        public void run() {
            Util.displayErrorScreenAndLogout(SplashActivity.this, mErrorMessage, false);
        }
    }
}
