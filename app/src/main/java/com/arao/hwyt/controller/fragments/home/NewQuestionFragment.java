package com.arao.hwyt.controller.fragments.home;


import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import com.arao.hwyt.R;

/**
 * User: angelromero
 * Date: 14/05/2014
 * Time: 15:45
 */
public class NewQuestionFragment extends HomeFragment {

    private final static int MAX_CHARACTER_NUMBER = 500;

    private TextView mCharactersLeftIndicator;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (mHomeFragmentListener != null) {
            mHomeFragmentListener.onTabsRemoved();
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.new_question_fragment, container, false);

        if (rootView != null) {
            mCharactersLeftIndicator = (TextView) rootView.findViewById(R.id.new_question_character_indicator_tv);
            updateCharacterLeftIndicator(MAX_CHARACTER_NUMBER);

            EditText newQuestionEditText = (EditText) rootView.findViewById(R.id.new_question_et);
            newQuestionEditText.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {
                    updateCharacterLeftIndicator(MAX_CHARACTER_NUMBER - s.length());
                }

                @Override
                public void afterTextChanged(Editable s) {

                }
            });
        }

        return rootView;
    }

    private void updateCharacterLeftIndicator(int charactersLeft) {
        mCharactersLeftIndicator.setText(String.valueOf(charactersLeft));
    }
}