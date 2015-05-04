package com.arao.hwyt.controller.fragments.register;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.arao.hwyt.R;
import com.arao.hwyt.model.User;
import com.arao.hwyt.net.RequestManager;
import com.arao.hwyt.util.DialogHelper;
import com.arao.hwyt.util.Util;

import static com.arao.hwyt.net.NetModule.requestManager;

public class RegUserDetailsFragment extends RegisterFragment implements TextWatcher,
        Response.Listener<Boolean>, Response.ErrorListener {

    private final static String BUNDLE_USERNAME_TEXT = "bundle_username_text";
    private final static String BUNDLE_PASSWORD_TEXT = "bundle_password_text";
    private final static String BUNDLE_CONFIRM_PASSWORD_TEXT = "bundle_confirm_password_text";

    private final RequestManager mRequestManager;

    private EditText mUsernameEditText;
    private String mEnteredUsername;
    private EditText mPasswordEditText;
    private String mEnteredPassword;
    private EditText mConfirmPasswordEditText;
    private String mEnteredConfirmPassword;
    private ProgressDialog mCommProgressDialog;

    private String mCheckedUsername = "";

    public RegUserDetailsFragment() {
        this(requestManager());
    }

    @SuppressLint("ValidFragment")
    public RegUserDetailsFragment(RequestManager requestManager) {
        mRequestManager = requestManager;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.registration_user_details_fragment, container, false);

        if (rootView != null) {
            mUsernameEditText = (EditText) rootView.findViewById(R.id.registration_user_et);
            mUsernameEditText.addTextChangedListener(this);
            mUsernameEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
                @Override
                public void onFocusChange(View v, boolean hasFocus) {
                    if (!hasFocus && mUsernameEditText.getText() != null &&
                            !TextUtils.isEmpty(mUsernameEditText.getText().toString())) {
                        checkUsernameAvailability(mUsernameEditText.getText().toString());
                    }
                }
            });
            mPasswordEditText = (EditText) rootView.findViewById(R.id.registration_pass_et);
            mPasswordEditText.addTextChangedListener(this);
            mConfirmPasswordEditText = (EditText) rootView.findViewById(R.id.registration_pass_confirm_et);
            mConfirmPasswordEditText.addTextChangedListener(this);

            if (savedInstanceState != null) {
                restoreSavedState(savedInstanceState);
            }
        }

        return rootView;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        dismissCommunicationProgressDialog();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        updateCurrentEnteredValues();

        if (mEnteredUsername != null) {
            outState.putString(BUNDLE_USERNAME_TEXT, mEnteredUsername);
        }
        if (mEnteredPassword != null) {
            outState.putString(BUNDLE_PASSWORD_TEXT, mEnteredPassword);
        }
        if (mEnteredConfirmPassword != null) {
            outState.putString(BUNDLE_CONFIRM_PASSWORD_TEXT, mEnteredConfirmPassword);
        }

        super.onSaveInstanceState(outState);
    }

    private void updateCurrentEnteredValues() {
        mEnteredUsername = Util.getTextOrEmptyStringFromView(mUsernameEditText);
        mEnteredPassword = Util.getTextOrEmptyStringFromView(mPasswordEditText);
        mEnteredConfirmPassword = Util.getTextOrEmptyStringFromView(mConfirmPasswordEditText);
    }

    private void restoreSavedState(Bundle savedInstanceState) {
        String savedUsername = savedInstanceState.getString(BUNDLE_USERNAME_TEXT);
        String savedPassword = savedInstanceState.getString(BUNDLE_PASSWORD_TEXT);
        String savedConfirmPassword = savedInstanceState.getString(BUNDLE_CONFIRM_PASSWORD_TEXT);

        if (savedUsername != null) {
            mUsernameEditText.setText(savedUsername);
        }
        if (savedPassword != null) {
            mPasswordEditText.setText(savedPassword);
        }
        if (savedConfirmPassword != null) {
            mConfirmPasswordEditText.setText(savedConfirmPassword);
        }
    }

    private boolean areAllFieldsFilled() {
        return !TextUtils.isEmpty(Util.getTextOrEmptyStringFromView(mUsernameEditText)) &&
                !TextUtils.isEmpty(Util.getTextOrEmptyStringFromView(mPasswordEditText)) &&
                !TextUtils.isEmpty(Util.getTextOrEmptyStringFromView(mConfirmPasswordEditText));
    }

    public boolean checkPasswordsMatch() {
        return Util.getTextOrEmptyStringFromView(mPasswordEditText).
                equals(Util.getTextOrEmptyStringFromView(mConfirmPasswordEditText));
    }

    public void setErrorToPasswordFields() {
        mPasswordEditText.setError(getString(R.string.register_passwords_do_not_match_error));
        mConfirmPasswordEditText.setError(getString(R.string.register_passwords_do_not_match_error));
    }

    @Override
    public void updateUser(User user) {
        user.setName(Util.getTextOrEmptyStringFromView(mUsernameEditText));
        user.setPassword(Util.getTextOrEmptyStringFromView(mPasswordEditText));
        updateCurrentEnteredValues();
    }


    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        if (!TextUtils.isEmpty(mUsernameEditText.getError())) {
            mUsernameEditText.setError(null);
        }
    }

    @Override
    public void afterTextChanged(Editable s) {
        if (areAllFieldsFilled() && mRegisterFragmentListener != null) {
            mRegisterFragmentListener.setEnableNextOrFinishButton(true);
        }
    }

    private void checkUsernameAvailability(String username) {
        // Here it is checked that the current username hasn't been checked already to avoid
        // doing one more request to the server
        if (!username.equals(mCheckedUsername)) {
            RegUserDetailsFragment currentFragment = this;
            Activity contextActivity = this.getActivity();

            mCommProgressDialog = DialogHelper.createAndDisplayCommProgressDialog(contextActivity,
                    getString(R.string.communication_progress_dialog_title),
                    getString(R.string.communication_check_username_dialog_message));

            // FIXME execute request
//            CheckExistUserRequest registerRequest = new CheckExistUserRequest(username,
//                    currentFragment, currentFragment);
//            mRequestManager.executeRequest(registerRequest, null);
            mCheckedUsername = username;
        }
    }

    /**
     * Called when a usernameAvailable is received.
     *
     * @param usernameAvailable
     */
    @Override
    public void onResponse(Boolean usernameAvailable) {
        dismissCommunicationProgressDialog();
        if (!usernameAvailable) {
            mUsernameEditText.setError(getString(R.string.register_username_already_exists));
        }
    }

    /**
     * Callback method that an error has been occurred with the
     * provided error code and optional user-readable message.
     *
     * @param error
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        dismissCommunicationProgressDialog();
        Util.displayErrorScreenAndLogout(this.getActivity(), "error", false);
    }

    private void dismissCommunicationProgressDialog() {
        if (mCommProgressDialog != null && mCommProgressDialog.isShowing()) {
            mCommProgressDialog.dismiss();
        }
    }
}