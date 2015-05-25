package com.arao.hwyt.controller.fragments.home;

/**
 * User: angelromero
 * Date: 07/05/2014
 * Time: 16:21
 */
public interface HomeFragmentListener {
    void onDisplayBackButton();
    void onTopLevelScreenDisplayed(boolean isTopLevelScreen);
    void onQuestionsLoadingComplete();
}
