package com.arao.hwyt.controller.fragments.home;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import com.arao.hwyt.R;
import com.arao.hwyt.controller.adapters.EntryListAdapter;
import com.arao.hwyt.model.Question;

import java.util.ArrayList;
import java.util.List;

/**
 * User: angelromero
 * Date: 07/05/2014
 * Time: 11:47
 */
public class FilteredQuestionsFragment extends HomeFragment {

    private final static String FILTERED_QUESTIONS_LIST_BUNDLE_ARGUMENT_ID = "filtered_questions";

    private List<Question> mFilteredQuestions;

    public static FilteredQuestionsFragment newInstance(List<Question> questionsList) {
        FilteredQuestionsFragment questionsFragment = new FilteredQuestionsFragment();
        Bundle b = new Bundle();
        b.putParcelableArrayList(FILTERED_QUESTIONS_LIST_BUNDLE_ARGUMENT_ID, (ArrayList<Question>) questionsList);
        questionsFragment.setArguments(b);

        return questionsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.questions_filtered_frament, container, false);
        Bundle bundle = getArguments();

        if (rootView != null && bundle != null) {
            mFilteredQuestions = bundle.getParcelableArrayList(FILTERED_QUESTIONS_LIST_BUNDLE_ARGUMENT_ID);

            EntryListAdapter entryListAdapter = new EntryListAdapter(getActivity(), R.layout.question_list_item,
                    mFilteredQuestions, false, null);

            ListView questionListView = (ListView) rootView.findViewById(R.id.questions_lv);
            questionListView.setAdapter(entryListAdapter);

            questionListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Question question = mFilteredQuestions.get(position);

                    if (question.getAnswers() != null && question.getAnswers().size() > 0) {
                        SingleQuestionFragment singleQuestionFragment = SingleQuestionFragment.newInstance(question);
                        FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
                        fragmentManager.beginTransaction()
                                .replace(R.id.content_frame, singleQuestionFragment)
                                .addToBackStack(null)
                                .commit();
                    }
                }
            });
        }

        if (mHomeFragmentListener != null) {
            mHomeFragmentListener.onQuestionsLoadingComplete();
        }

        return rootView;
    }
}