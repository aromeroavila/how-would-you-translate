package com.arao.hwyt.controller.fragments.register;


import android.app.Activity;
import android.support.v4.app.Fragment;
import com.arao.hwyt.model.User;

/**
 * User: angelromero
 * Date: 31/03/2014
 * Time: 15:21
 */
public abstract class RegisterFragment extends Fragment {

    protected RegisterFragmentListener mRegisterFragmentListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mRegisterFragmentListener = (RegisterFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement RegisterFragmentListener");
        }
    }

    public abstract void updateUser(User user);
}