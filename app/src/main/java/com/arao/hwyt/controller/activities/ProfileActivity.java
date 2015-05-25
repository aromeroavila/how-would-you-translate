package com.arao.hwyt.controller.activities;

import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.arao.hwyt.R;
import com.arao.hwyt.controller.HwytApplication;
import com.arao.hwyt.controller.broadreceivers.LogoutBroadcastReceiver;
import com.arao.hwyt.model.Language;
import com.arao.hwyt.model.User;
import com.arao.hwyt.util.DialogHelper;
import com.arao.hwyt.util.Util;

import java.util.List;

/**
 * User: angelromero
 * Date: 21/05/2014
 * Time: 13:41
 */
public class ProfileActivity extends AppCompatActivity {

    private Toolbar mToolbar;

    private ImageView mAvatarImage;
    private EditText mUsernameEditText;
    private EditText mPasswordEditText;
    private ImageView mMotherLanguageFlagImage;
    private LinearLayout mSpokenLanguagesLinearLayout;
    /**
     * Used to listen to the Logout Broadcast message sent when logging out the app so this activity is finished
     */
    private LogoutBroadcastReceiver logoutBroadcastReceiver;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile_screen);

        logoutBroadcastReceiver = new LogoutBroadcastReceiver(this);
        registerReceiver(logoutBroadcastReceiver, new IntentFilter(LogoutBroadcastReceiver.BROADCAST_ACTION_LOGOUT));

        initActionBar();

        User loggedUser = ((HwytApplication) getApplication()).getLoggedUser();
        if (loggedUser != null) {
           initViews(loggedUser);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(logoutBroadcastReceiver);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile_screen_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.action_logout:
                DialogHelper.displayLogoutConfirmationDialog(this);
                return true;
            case R.id.action_about: // TODO to be deleted
                Util.displayErrorScreenAndLogout(ProfileActivity.this, "Test error message asdf;ajsdflkajsdflkajsdfoiajs ofjasd ofjaosfjaois jdfoiasjfoiajs df", true);
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void initViews(User loggedUser) {
        mAvatarImage = (ImageView) findViewById(R.id.profile_avatar_i);
        if (loggedUser.getAvatarImage() != -1) {
            mAvatarImage.setImageResource(loggedUser.getAvatarImage());
        }
        mUsernameEditText = (EditText) findViewById(R.id.profile_user_et);
        if (loggedUser.getName() != null) {
            mUsernameEditText.setText(loggedUser.getName());
        }
        mPasswordEditText = (EditText) findViewById(R.id.profile_pass_et);
        if (loggedUser.getPassword() != null) {
            mPasswordEditText.setText(loggedUser.getPassword());
        }
        mMotherLanguageFlagImage = (ImageView) findViewById(R.id.profile_m_lang_i);
        if (loggedUser.getMotherLanguage() != null) {
            mMotherLanguageFlagImage.setImageResource(loggedUser.getMotherLanguage().getLanguageConstant().getResImageId());
        }
        mSpokenLanguagesLinearLayout = (LinearLayout) findViewById(R.id.profile_s_lang_ll);
        List<Language> spokenLanguages = loggedUser.getSpokenLanguages();
        if (spokenLanguages != null) {
            for (int i=0; i<spokenLanguages.size(); i++) {
                ImageView flagImage = new ImageView(this);
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                flagImage.setLayoutParams(layoutParams);
                flagImage.setImageResource(spokenLanguages.get(i).getLanguageConstant().getResImageId());
                mSpokenLanguagesLinearLayout.addView(flagImage);
            }
        }
    }

    @SuppressWarnings("ConstantConditions")
    private void initActionBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        setTitle(getString(R.string.menu_item_title_profile));
    }
}