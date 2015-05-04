package com.arao.hwyt.controller.fragments.register;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.arao.hwyt.R;
import com.arao.hwyt.controller.adapters.AvatarImagePageAdapter;
import com.arao.hwyt.model.User;
import com.arao.hwyt.util.Util;

/**
 * User: angelromero
 * Date: 10/04/2014
 * Time: 11:04
 */
public class RegAvatarFragment extends RegisterFragment {

    private static int AVATAR_IMAGE_RESOURCE_FILES_NUMBER;

    private ViewPager mAvatarViewPager;
    private TextView mViewPagerIndicator;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AVATAR_IMAGE_RESOURCE_FILES_NUMBER = getResources().getInteger(R.integer.avatar_image_resource_files_number);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.registration_avatar_fragment, container, false);

        if (rootView != null) {
            mAvatarViewPager = (ViewPager) rootView.findViewById(R.id.avatar_selection_vp);
            mViewPagerIndicator = (TextView) rootView.findViewById(R.id.avatar_view_pager_indicator_tv);
            setIndicator(1);
            AvatarImagePageAdapter avatarImagePageAdapter =
                    new AvatarImagePageAdapter(getChildFragmentManager(), getActivity(), AVATAR_IMAGE_RESOURCE_FILES_NUMBER);

            mAvatarViewPager.setAdapter(avatarImagePageAdapter);
            mAvatarViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
                @Override
                public void onPageScrolled(int i, float v, int i2) {

                }

                @Override
                public void onPageSelected(int i) {
                    setIndicator(i + 1);
                }

                @Override
                public void onPageScrollStateChanged(int i) {

                }
            });
        }

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();

        if (mRegisterFragmentListener != null) {
            mRegisterFragmentListener.setEnableNextOrFinishButton(true);
        }
    }

    @Override
    public void updateUser(User user) {
        user.setAvatarImage(Util.getAvatarResourceIdFromPosition(getActivity(), mAvatarViewPager.getCurrentItem()));
    }

    private void setIndicator(int position) {
        mViewPagerIndicator.setText(position + getString(R.string.of_indicator) + AVATAR_IMAGE_RESOURCE_FILES_NUMBER);
    }
}