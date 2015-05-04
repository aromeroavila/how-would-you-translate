package com.arao.hwyt.controller.fragments.home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.RelativeLayout;

import com.arao.hwyt.R;
import com.arao.hwyt.controller.adapters.EntryListAdapter;
import com.arao.hwyt.controller.adapters.EntryListAdapterListener;
import com.arao.hwyt.model.Question;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * User: angelromero
 * Date: 09/05/2014
 * Time: 12:05
 */
public class SingleQuestionFragment extends HomeFragment implements EntryListAdapterListener {

    private final static String QUESTION_BUNDLE_ARGUMENT_ID = "question";

    private Question mQuestion;

    private View mRootView;

    public static SingleQuestionFragment newInstance(Question question) {
        SingleQuestionFragment singleQuestionFragment = new SingleQuestionFragment();
        Bundle b = new Bundle();
        b.putParcelable(QUESTION_BUNDLE_ARGUMENT_ID, question);
        singleQuestionFragment.setArguments(b);

        return singleQuestionFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);
        if (mHomeFragmentListener != null) {
            mHomeFragmentListener.onDisplayBackButton();
        }
    }

    @Override
    protected boolean isTopLevelScreen() {
        return false;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.single_question_fragment, container, false);
        Bundle bundle = getArguments();

        if (bundle != null && mRootView != null) {
            mQuestion = bundle.getParcelable(QUESTION_BUNDLE_ARGUMENT_ID);

            EntryListAdapter entryListAdapter = new EntryListAdapter(getActivity(),
                    R.layout.question_list_item, new ArrayList<Question>(Arrays.asList(mQuestion)), true, this);

            ExpandableListView questionListView = (ExpandableListView) mRootView.findViewById(R.id.single_question_answers_elv);
            questionListView.setAdapter((ExpandableListAdapter) entryListAdapter);
        }

        return mRootView;
    }

    @Override
    public void onAnswerButtonClicked(final View answerButton) {
        if (mRootView != null) {
            // FIXME This doesn't work for the first time, find out why
            answerButton.setEnabled(false);

            final RelativeLayout answerFormRL = (RelativeLayout) mRootView.findViewById(R.id.answer_form_rl);
            answerFormRL.setVisibility(View.VISIBLE);

            final EditText answerFormEditText = (EditText) mRootView.findViewById(R.id.answer_form_et);

            Button answerSubmitButton = (Button) mRootView.findViewById(R.id.answer_form_submit_b);
            answerSubmitButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // TODO submit answer, update view
                    answerFormRL.setVisibility(View.GONE);
                    answerButton.setEnabled(true);
                    answerFormEditText.setText("");
                }
            });

            Button answerCancelButton = (Button) mRootView.findViewById(R.id.answer_form_cancel_b);
            answerCancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    answerFormRL.setVisibility(View.GONE);
                    answerButton.setEnabled(true);
                }
            });
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
            fragmentManager.popBackStack();
        }

        return true;
    }
}