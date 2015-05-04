package com.arao.hwyt.controller.adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.arao.hwyt.R;
import com.arao.hwyt.controller.fragments.register.AvatarImageFragment;
import com.arao.hwyt.util.Util;

/**
 * User: angelromero
 * Date: 10/04/2014
 * Time: 11:25
 */
public class AvatarImagePageAdapter extends FragmentStatePagerAdapter {

    private int mImageResourceFilesNumber;
    private Context mContext;

    public AvatarImagePageAdapter(FragmentManager fm, Context context, int imageResourceFilesNumber) {
        super(fm);

        mContext = context;
        mImageResourceFilesNumber = imageResourceFilesNumber;
    }

    @Override
    public Fragment getItem(int i) {
        AvatarImageFragment avatarImageFragment = new AvatarImageFragment();

        Bundle bundle = new Bundle();
        bundle.putInt(mContext.getString(R.string.BUNDLE_AVATAR_IMAGE_FRAGMENT_RESOURCE_ID_FIELD),
                Util.getAvatarResourceIdFromPosition(mContext, i));
        avatarImageFragment.setArguments(bundle);

        return avatarImageFragment;
    }

    @Override
    public int getCount() {
        return mImageResourceFilesNumber;
    }
}
