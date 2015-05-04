package com.arao.hwyt.controller.activities;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import com.arao.hwyt.R;

/**
 * Created by Angel on 28/03/2014.
 */
public class LoginActivity extends Activity {

    private Button mLoginButton;
    private Button mRegisterButton;
    private CheckBox mRememberUserCheckbox;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen);

        setTitle(R.string.login_action_bar_title);
        initViews(this);
    }

    public void initViews(final Activity context) {
        mLoginButton = (Button) findViewById(R.id.login_login_b);
        mRegisterButton = (Button) findViewById(R.id.login_register_b);
        mRememberUserCheckbox = (CheckBox) findViewById(R.id.login_remember_user_ctv);

        mLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLoginButton(context);
            }
        });

        mRegisterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickRegisterButton(context);
            }
        });
    }

    private void onClickLoginButton(Context context) {
        if (mRememberUserCheckbox.isChecked()) {

        }

        Intent goToHomeScreenIntent = new Intent(context, HomeActivity.class);
        startActivity(goToHomeScreenIntent);
        finish();
    }

    private void onClickRegisterButton(Context context) {
        Intent goToRegistrationScreenIntent = new Intent(context, RegisterActivity.class);
        startActivity(goToRegistrationScreenIntent);
        finish();
    }
}