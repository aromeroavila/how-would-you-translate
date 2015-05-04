package com.arao.hwyt.controller.fragments.register;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import com.arao.hwyt.R;

/**
 * User: angelromero
 * Date: 10/04/2014
 * Time: 11:28
 */
public class AvatarImageFragment extends Fragment {

    private int mImageResourceId;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getArguments();
        mImageResourceId = bundle.getInt(getString(R.string.BUNDLE_AVATAR_IMAGE_FRAGMENT_RESOURCE_ID_FIELD), R.drawable.avatar_0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        ImageView avatarImage = new ImageView(getActivity());
        avatarImage.setImageResource(mImageResourceId);

        LinearLayout layout = new LinearLayout(getActivity());
        layout.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));

        layout.setGravity(Gravity.CENTER);
        layout.addView(avatarImage);

        return layout;
    }
}