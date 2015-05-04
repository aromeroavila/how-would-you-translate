package com.arao.hwyt.controller.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.arao.hwyt.controller.fragments.home.FilteredQuestionsFragment;
import com.arao.hwyt.model.Filter;
import com.arao.hwyt.model.Question;
import com.arao.hwyt.net.test.QuestionsRetriever;

import java.util.List;

public class QuestionsPageAdapter extends FragmentStatePagerAdapter {

    private List<Filter> mFilters;

    public QuestionsPageAdapter(FragmentManager fm, List<Filter> filters) {
        super(fm);

        mFilters = filters;
    }

    @Override
    public Fragment getItem(int i) {
        Filter filter = mFilters.get(i);
        List<Question> filteredQuestions = QuestionsRetriever.retrieveQuestions(filter);
        return FilteredQuestionsFragment.newInstance(filteredQuestions);
    }

    @Override
    public int getCount() {
        return mFilters.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mFilters.get(position).getFilterTitle();
    }
}
