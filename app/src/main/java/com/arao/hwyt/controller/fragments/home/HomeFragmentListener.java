package com.arao.hwyt.controller.fragments.home;

import java.util.List;

/**
 * User: angelromero
 * Date: 07/05/2014
 * Time: 16:21
 */
public interface HomeFragmentListener {
    public void onTabConfigurationChanged(List<String> titles);
    public void onTabsRemoved();
    public void onPageChanged(int position);
    public void onDisplayBackButton();
    public void onTopLevelScreenDisplayed(boolean isTopLevelScreen);
    public void onQuestionsLoadingComplete();
}
