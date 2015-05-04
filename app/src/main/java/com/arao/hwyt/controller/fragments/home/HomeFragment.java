package com.arao.hwyt.controller.fragments.home;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;

/**
 * User: angelromero
 * Date: 12/05/2014
 * Time: 15:26
 */
public abstract class HomeFragment extends Fragment {

    protected HomeFragmentListener mHomeFragmentListener;

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mHomeFragmentListener = (HomeFragmentListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement HomeFragmentListener");
        }

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mHomeFragmentListener != null) {
            mHomeFragmentListener.onTopLevelScreenDisplayed(isTopLevelScreen());
        }
    }

    protected boolean isTopLevelScreen() {
        return true;
    }
}