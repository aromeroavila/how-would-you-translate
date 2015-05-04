package com.arao.hwyt.controller.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import com.arao.hwyt.R;

/**
 * Created by Angel on 28/03/2014.
 */
public class ErrorActivity extends Activity {

    private String mErrorMessage;
    private boolean isLogged;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.error_screen);

        setTitle(getString(R.string.error_screen_title));

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            mErrorMessage = bundle.getString(getString(R.string.BUNDLE_ERROR_ACTIVITY_ERROR_MESSAGE));
            isLogged = bundle.getBoolean(getString(R.string.BUNDLE_ERROR_ACTIVITY_IS_LOGGED));
            TextView errorMessageTextView = (TextView) findViewById(R.id.error_message_tv);
            errorMessageTextView.setText(mErrorMessage);
        }
    }

    @Override
    public void onBackPressed() {
        if (isLogged) {
            Intent loginScreenIntent = new Intent(this, LoginActivity.class);
            startActivity(loginScreenIntent);
            finish();
        } else {
            super.onBackPressed();
        }
    }
}