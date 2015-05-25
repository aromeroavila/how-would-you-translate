package com.arao.hwyt.controller.fragments.home;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arao.hwyt.R;
import com.arao.hwyt.controller.adapters.QuestionsPageAdapter;
import com.arao.hwyt.model.Filter;
import com.astuetz.PagerSlidingTabStrip;
import com.getbase.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class QuestionsFragment extends HomeFragment implements View.OnClickListener {

    private final static String FILTER_LIST_BUNDLE_ARGUMENT_ID = "filters";

    private List<Filter> mFilterList;


    public static QuestionsFragment newInstance(List<Filter> filterList) {
        QuestionsFragment questionsFragment = new QuestionsFragment();
        Bundle b = new Bundle();
        b.putParcelableArrayList(FILTER_LIST_BUNDLE_ARGUMENT_ID, (ArrayList<Filter>) filterList);
        questionsFragment.setArguments(b);

        return questionsFragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.questions_fragment, container, false);
        Bundle bundle = getArguments();

        if (rootView != null) {
            if (bundle != null) {
                mFilterList = bundle.getParcelableArrayList(FILTER_LIST_BUNDLE_ARGUMENT_ID);
            } else {
                mFilterList = initDefaultQuestionFilters();
            }

            updateTabTitles();

            QuestionsPageAdapter questionsPageAdapter = new QuestionsPageAdapter(getChildFragmentManager(), mFilterList);
            ViewPager mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
            mViewPager.setAdapter(questionsPageAdapter);

            PagerSlidingTabStrip mPagerSlidingTabStrip = (PagerSlidingTabStrip) rootView.findViewById(R.id.questions_tabs);
            mPagerSlidingTabStrip.setViewPager(mViewPager);

            FloatingActionButton mFab = (FloatingActionButton) rootView.findViewById(R.id.new_questions_fab);
            mFab.setOnClickListener(this);
        }

        return rootView;
    }

    private List<Filter> initDefaultQuestionFilters() {
        List<Filter> filters = new ArrayList<>();

        Filter filter1 = new Filter();
        filter1.setFilterTitle("One");
        filters.add(filter1);
        Filter filter2 = new Filter();
        filter2.setFilterTitle("Two");
        filters.add(filter2);

        return filters;
    }

    private void updateTabTitles() {
        List<String> tabTitles = new ArrayList<>();
        for (int i = 0; i < mFilterList.size(); i++) {
            tabTitles.add(mFilterList.get(i).getFilterTitle());
        }
    }

    @Override
    public void onClick(View view) {
        NewQuestionFragment newQuestionFragment = new NewQuestionFragment();
        getActivity().getSupportFragmentManager().beginTransaction()
                .replace(R.id.content_frame, newQuestionFragment)
                .commit();
    }
}