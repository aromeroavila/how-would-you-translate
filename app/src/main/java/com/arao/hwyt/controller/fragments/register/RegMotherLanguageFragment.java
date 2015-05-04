package com.arao.hwyt.controller.fragments.register;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.arao.hwyt.R;
import com.arao.hwyt.controller.adapters.LanguageListAdapter;
import com.arao.hwyt.model.Language;
import com.arao.hwyt.model.User;
import com.arao.hwyt.model.enums.LanguageConstant;
import com.arao.hwyt.model.enums.LanguageLevel;

public class RegMotherLanguageFragment extends RegisterFragment {

    private final static String BUNDLE_SELECTED_ITEM_INDEX = "bundle_selected_item";

    private int mSelectedItemIndex = -1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.registration_mother_language_fragment, container, false);

        if (rootView != null) {
            LanguageListAdapter languageListAdapter = new LanguageListAdapter(getActivity(), R.layout.language_list_item, null);
            ListView languagesListView = (ListView) rootView.findViewById(R.id.register_languages_list_view);
            languagesListView.setAdapter(languageListAdapter);
            languagesListView.setChoiceMode(ListView.CHOICE_MODE_SINGLE);

            languagesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    view.setSelected(true);
                    mSelectedItemIndex = position;
                    if (mRegisterFragmentListener != null) {
                        mRegisterFragmentListener.setEnableNextOrFinishButton(true);
                    }

                }
            });

            if (savedInstanceState != null) {
                mSelectedItemIndex = savedInstanceState.getInt(BUNDLE_SELECTED_ITEM_INDEX);
                if (mSelectedItemIndex != -1) {
                    languagesListView.setSelection(mSelectedItemIndex);
                    // FIXME make selected visible
                }
            }
        }

        return rootView;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(BUNDLE_SELECTED_ITEM_INDEX, mSelectedItemIndex);
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mSelectedItemIndex != -1 && mRegisterFragmentListener != null) {
            mRegisterFragmentListener.setEnableNextOrFinishButton(true);
        }
    }

    @Override
    public void updateUser(User user) {
        LanguageConstant selectedLanguageConstant = LanguageConstant.getLanguageByPosition(mSelectedItemIndex);
        Language selectedLanguage = new Language(selectedLanguageConstant, LanguageLevel.NATIVE_SPEAKER);
        user.setMotherLanguage(selectedLanguage);
    }
}