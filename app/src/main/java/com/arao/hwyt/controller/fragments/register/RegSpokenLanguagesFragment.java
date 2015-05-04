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

import java.util.ArrayList;
import java.util.List;

public class RegSpokenLanguagesFragment extends RegisterFragment {

    private LanguageListAdapter mLanguageListAdapter;
    private List<Boolean> mSelectionStatus;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.registration_spoken_languages_fragment, container, false);

        if (rootView != null) {
            User loggedUser = mRegisterFragmentListener.getUserToBeRegistered();
            List<LanguageConstant> excludedLanguages = new ArrayList<LanguageConstant>(1);
            excludedLanguages.add(loggedUser.getMotherLanguage().getLanguageConstant());
            final ListView languagesListView = (ListView) rootView.findViewById(R.id.register_spoken_languages_list_view);
            mLanguageListAdapter = new LanguageListAdapter(getActivity(), R.layout.language_list_item, excludedLanguages);
            languagesListView.setAdapter(mLanguageListAdapter);
            languagesListView.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);

            languagesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    boolean previousSelectionStatus = mSelectionStatus.get(position);
                    mSelectionStatus.set(position, !previousSelectionStatus);
                    if (mRegisterFragmentListener != null) {
                        mRegisterFragmentListener.setEnableNextOrFinishButton(isAtLeastOneItemSelected());
                    }
                    view.setSelected(!previousSelectionStatus);
                }
            });

            mSelectionStatus = new ArrayList<Boolean>();
            for (int i=0; i<mLanguageListAdapter.getCount(); i++) {
                mSelectionStatus.add(i, false);
            }
        }

        return rootView;
    }

    private void selectItems() {

    }

    private boolean isAtLeastOneItemSelected() {
        for (int i=0; i<mSelectionStatus.size(); i++) {
            if (mSelectionStatus.get(i)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void updateUser(User user) {
        List<Language> selectedLanguages = new ArrayList<Language>();
        for (int i=0; i<mSelectionStatus.size(); i++) {
            if (mSelectionStatus.get(i)) {
                selectedLanguages.add(new Language(mLanguageListAdapter.getLanguagesList().get(i), LanguageLevel.UNASSIGNED));
            }
        }
        user.setSpokenLanguages(selectedLanguages);
    }
}