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

import java.util.ArrayList;
import java.util.List;

public class QuestionsFragment extends HomeFragment implements ViewPager.OnPageChangeListener {

    private final static String FILTER_LIST_BUNDLE_ARGUMENT_ID = "filters";

    private ViewPager mViewPager;
    private PagerSlidingTabStrip mPagerSlidingTabStrip;
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
            mViewPager = (ViewPager) rootView.findViewById(R.id.pager);
            mViewPager.setAdapter(questionsPageAdapter);
            mViewPager.setOnPageChangeListener(this);

            mPagerSlidingTabStrip = (PagerSlidingTabStrip) rootView.findViewById(R.id.questions_tabs);
            mPagerSlidingTabStrip.setViewPager(mViewPager);
        }

        return rootView;
    }

    private List<Filter> initDefaultQuestionFilters() {
        List<Filter> filters = new ArrayList<Filter>();

        Filter filter1 = new Filter();
        filter1.setFilterTitle("One");
        filters.add(filter1);
        Filter filter2 = new Filter();
        filter2.setFilterTitle("Two");
        filters.add(filter2);

        return filters;
    }

    private  void updateTabTitles() {
        List<String> tabTitles = new ArrayList<String>();
        for (int i=0; i<mFilterList.size(); i++) {
            tabTitles.add(mFilterList.get(i).getFilterTitle());
        }

        if (mHomeFragmentListener != null) {
            mHomeFragmentListener.onTabConfigurationChanged(tabTitles);
        }
    }

    @Override
    public void onPageScrolled(int i, float v, int i2) {

    }

    @Override
    public void onPageSelected(int i) {
        if (mHomeFragmentListener != null) {
            mHomeFragmentListener.onPageChanged(i);
        }
    }

    @Override
    public void onPageScrollStateChanged(int i) {

    }

    public void setCurrentViewPagerItem(int i) {
        if (mViewPager != null) {
            mViewPager.setCurrentItem(i, true);
        }
    }
}