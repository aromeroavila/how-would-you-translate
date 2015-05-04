package com.arao.hwyt.controller.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.NetworkResponse;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.arao.hwyt.R;
import com.arao.hwyt.controller.HwytApplication;
import com.arao.hwyt.controller.fragments.register.RegAvatarFragment;
import com.arao.hwyt.controller.fragments.register.RegMotherLanguageFragment;
import com.arao.hwyt.controller.fragments.register.RegSpokenLanguagesFragment;
import com.arao.hwyt.controller.fragments.register.RegUserDetailsFragment;
import com.arao.hwyt.controller.fragments.register.RegisterFragment;
import com.arao.hwyt.controller.fragments.register.RegisterFragmentListener;
import com.arao.hwyt.model.User;
import com.arao.hwyt.util.DialogHelper;
import com.arao.hwyt.util.Util;

public class RegisterActivity extends FragmentActivity implements RegisterFragmentListener,
        Response.Listener<Integer>, Response.ErrorListener {

    private final static String BUNDLE_CURRENT_DISPLAYED_POSITION = "bundle_current_displayed_position";
    private final static String BUNDLE_USER_TO_BE_REGISTERED = "bundle_user_to_be_registered";

    private final static int USER_DETAILS_STEP_FRAGMENT_POSITION = 1;
    private final static int MOTHER_LANGUAGE_STEP_FRAGMENT_POSITION = 2;
    private final static int SPOKEN_LANGUAGES_STEP_FRAGMENT_POSITION = 3;
    private final static int AVATAR_SELECTION_STEP_FRAGMENT_POSITION = 4;
    private final static int REGISTRATION_NUMBER_OF_STEPS = 4;

    private TextView mStepIndicatorTextView;
    private Button mPreviousButton;
    private Button mNextButton;
    private Button mFinishButton;
    private ProgressDialog mCommPd;

    private int mCurrentStep;
    private FragmentManager mFragmentManager;
    private User mUserToBeRegistered;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registration_screen);

        mFragmentManager = getSupportFragmentManager();
        setTitle(R.string.register_action_bar_title);
        initViews();

        if (savedInstanceState != null) {
            mCurrentStep = savedInstanceState.getInt(BUNDLE_CURRENT_DISPLAYED_POSITION);
            mUserToBeRegistered = savedInstanceState.getParcelable(BUNDLE_USER_TO_BE_REGISTERED);
            updateButtonsAndIndicators(mCurrentStep);
        } else {
            mCurrentStep = USER_DETAILS_STEP_FRAGMENT_POSITION;
            mUserToBeRegistered = new User();
            navigateToStep(mCurrentStep);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        dismissCommunicationProgressDialog();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_CURRENT_DISPLAYED_POSITION, mCurrentStep);
        outState.putParcelable(BUNDLE_USER_TO_BE_REGISTERED, mUserToBeRegistered);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onBackPressed() {
        DialogHelper.displayExitRegistrationConfirmationDialog(this);
    }

    private void initViews() {
        mStepIndicatorTextView = (TextView) findViewById(R.id.register_step_indicator_tv);
        mPreviousButton = (Button) findViewById(R.id.register_previous_button);
        mPreviousButton.setVisibility(View.INVISIBLE);
        mPreviousButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mFragmentManager.popBackStack();
                updateButtonsAndIndicators(mCurrentStep - 1);
            }
        });

        mNextButton = (Button) findViewById(R.id.register_next_button);
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment currentFragment = (RegisterFragment) mFragmentManager.
                        findFragmentById(R.id.register_steps_fragment_container);

                if (currentFragment instanceof RegUserDetailsFragment) {
                    RegUserDetailsFragment regUserDetailsFragment = (RegUserDetailsFragment) currentFragment;

                    if (regUserDetailsFragment.checkPasswordsMatch()) {
                        regUserDetailsFragment.updateUser(mUserToBeRegistered);
                        navigateToStep(mCurrentStep + 1);
                    } else {
                        regUserDetailsFragment.setErrorToPasswordFields();
                    }
                } else {
                    currentFragment.updateUser(mUserToBeRegistered);
                    navigateToStep(mCurrentStep + 1);
                }
            }
        });

        mFinishButton = (Button) findViewById(R.id.register_finish_button);
        mFinishButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterFragment currentFragment = (RegisterFragment) mFragmentManager.
                        findFragmentById(R.id.register_steps_fragment_container);

                currentFragment.updateUser(mUserToBeRegistered);
                RegisterActivity registerActivity = RegisterActivity.this;

                mCommPd = DialogHelper.createAndDisplayCommProgressDialog(registerActivity,
                        getString(R.string.communication_progress_dialog_title),
                        getString(R.string.communication_registration_dialog_message));

                // FIXME execute request
//                ServerCommunicator serverCommunicator = ServerCommunicator.getServerCommunicator(RegisterActivity.this);
//                try {
//                    RegisterRequest registerRequest = new RegisterRequest(mUserToBeRegistered,
//                            registerActivity, registerActivity);
//                    serverCommunicator.addRequestToQueue(registerRequest);
//                } catch (UnsupportedEncodingException e) {
//                    Util.displayErrorScreenAndLogout(registerActivity, "error", false);
//                } catch (GeneralSecurityException e) {
//                    Util.displayErrorScreenAndLogout(registerActivity, "error", false);
//                }
            }
        });

        setEnableNextOrFinishButton(false);
    }

    public void navigateToStep(int stepNumber) {
        updateButtonsAndIndicators(stepNumber);

        RegisterFragment registerFragment = null;

        switch (stepNumber) {
            case USER_DETAILS_STEP_FRAGMENT_POSITION:
                registerFragment = new RegUserDetailsFragment();
                break;
            case MOTHER_LANGUAGE_STEP_FRAGMENT_POSITION:
                registerFragment = new RegMotherLanguageFragment();
                break;
            case SPOKEN_LANGUAGES_STEP_FRAGMENT_POSITION:
                registerFragment = new RegSpokenLanguagesFragment();
                break;
            case AVATAR_SELECTION_STEP_FRAGMENT_POSITION:
                registerFragment = new RegAvatarFragment();
                break;
        }

        FragmentTransaction fragmentTransaction = mFragmentManager.beginTransaction();
        fragmentTransaction.setCustomAnimations(R.anim.left_to_right_in, R.anim.left_to_right_out,
                R.anim.right_to_left_in, R.anim.right_to_left_out);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.replace(R.id.register_steps_fragment_container, registerFragment);
        fragmentTransaction.commit();

    }

    private void updateButtonsAndIndicators(int stepNumber) {
        if (mCurrentStep < stepNumber) {
            setEnableNextOrFinishButton(false);
        }

        mCurrentStep = stepNumber;
        setStepIndicatorText(stepNumber);
        setButtonsVisibility(stepNumber);
    }

    private void setStepIndicatorText(int stepNumber) {
        mStepIndicatorTextView.setText((stepNumber) + getString(R.string.of_indicator) + REGISTRATION_NUMBER_OF_STEPS);
    }

    private void setButtonsVisibility(int stepNumber) {
        mPreviousButton.setVisibility(View.VISIBLE);
        mNextButton.setVisibility(View.VISIBLE);
        mFinishButton.setVisibility(View.GONE);

        if (stepNumber == USER_DETAILS_STEP_FRAGMENT_POSITION) {
            mPreviousButton.setVisibility(View.INVISIBLE);
        } else if (stepNumber == REGISTRATION_NUMBER_OF_STEPS) {
            mNextButton.setVisibility(View.GONE);
            mFinishButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void setEnableNextOrFinishButton(boolean enable) {
        mNextButton.setEnabled(enable);
        mFinishButton.setEnabled(enable);
    }

    @Override
    public User getUserToBeRegistered() {
        return mUserToBeRegistered;
    }

    /**
     * Called when a RegisterRequest response is received.
     *
     * @param response registered user id returned by the database
     */
    @Override
    public void onResponse(Integer response) {
        // The user is updated with the userId generated by the db
        mUserToBeRegistered.setId(response);
        // The password is deleted when the user is saved or stored
        mUserToBeRegistered.setPassword("");
        ((HwytApplication) getApplication()).setLoggedUser(mUserToBeRegistered);

        dismissCommunicationProgressDialog();

        Intent goToHomeScreenIntent = new Intent(RegisterActivity.this, HomeActivity.class);
        startActivity(goToHomeScreenIntent);
        // The current activity is destroyed so it is removed from the back stack.
        finish();
    }

    /**
     * Callback method that an error has been occurred with the
     * provided error code and optional user-readable message.
     *
     * @param error received from the server
     */
    @Override
    public void onErrorResponse(VolleyError error) {
        dismissCommunicationProgressDialog();
        String errorMessage;
        NetworkResponse response = error.networkResponse;
        if(response != null && response.data != null){
            // TODO handle this properly and maybe extract the comm error handling to other class
            errorMessage = new String(response.data);
        } else {
            errorMessage = getString(R.string.communication_server_down_error_msg);
        }

        Util.displayErrorScreenAndLogout(this, errorMessage, false);
    }

    private void dismissCommunicationProgressDialog() {
        if (mCommPd != null && mCommPd.isShowing()) {
            mCommPd.dismiss();
        }
    }
}