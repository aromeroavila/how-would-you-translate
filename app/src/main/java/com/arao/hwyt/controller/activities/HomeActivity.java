package com.arao.hwyt.controller.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.arao.hwyt.R;
import com.arao.hwyt.controller.broadreceivers.LogoutBroadcastReceiver;
import com.arao.hwyt.controller.fragments.home.HomeFragmentListener;
import com.arao.hwyt.controller.fragments.home.QuestionsFragment;
import com.arao.hwyt.model.Filter;
import com.arao.hwyt.util.DialogHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * User: angelromero
 * Date: 21/03/2014
 * Time: 17:00
 */
public class HomeActivity extends AppCompatActivity implements ListView.OnItemClickListener,
        HomeFragmentListener {

    private final static int QUESTIONS_NAVIGATION_DRAWER_ITEM_POSITION = 0;
    private final static int YOUR_QUESTIONS_NAVIGATION_DRAWER_ITEM_POSITION = 1;
    private final static int YOUR_ANSWERS_NAVIGATION_DRAWER_ITEM_POSITION = 2;
    private final static int PROFILE_NAVIGATION_DRAWER_ITEM_POSITION = 3;
    private final static int LOG_OUT_NAVIGATION_DRAWER_ITEM_POSITION = 4;

    private FragmentManager mFragmentManager;
    private DrawerLayout mDrawerLayout;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;
    private ListView mDrawerList;
    private ProgressDialog mProgressDialog;

    private String[] mDrawerItemsTitle;
    private CharSequence mTitle;
    private boolean mTopLevelScreenDisplayed = true;
    /**
     * Used to listen to the Logout Broadcast message sent when logging out the app so this activity is finished
     */
    private LogoutBroadcastReceiver logoutBroadcastReceiver;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        // Broadcast receiver is registered
        logoutBroadcastReceiver = new LogoutBroadcastReceiver(this);
        registerReceiver(logoutBroadcastReceiver, new IntentFilter(LogoutBroadcastReceiver.BROADCAST_ACTION_LOGOUT));

        mFragmentManager = getSupportFragmentManager();
        mDrawerItemsTitle = getResources().getStringArray(R.array.home_drawer_list_titles);

        initProgressBar();
        initViews();
        initToolBar();

        selectItem(QUESTIONS_NAVIGATION_DRAWER_ITEM_POSITION);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Broadcast receiver is unregistered
        unregisterReceiver(logoutBroadcastReceiver);
    }

    private void initProgressBar() {
        mProgressDialog = new ProgressDialog(this);
        mProgressDialog.setMessage(getString(R.string.menu_progress_dialog_message));
        mProgressDialog.show();
    }

    private void initViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.navigation_drawer_list_item, mDrawerItemsTitle));
        mDrawerList.setOnItemClickListener(this);
    }

    private void initToolBar() {
        mToolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);


        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  /* host Activity */
                mDrawerLayout,         /* DrawerLayout object */
                mToolbar,
                R.string.drawer_open,  /* "open drawer" description */
                R.string.drawer_close  /* "close drawer" description */
        ) {

            /** Called when a drawer has settled in a completely closed state. */
            public void onDrawerClosed(View view) {
                mToolbar.setTitle(mTitle);
            }

            /** Called when a drawer has settled in a completely open state. */
            public void onDrawerOpened(View drawerView) {
                mToolbar.setTitle(mTitle);
            }
        };

        // Set the drawer toggle as the DrawerListener
        mDrawerLayout.setDrawerListener(mDrawerToggle);

        setTitle(mDrawerItemsTitle[0]);
    }

    @Override
    public void onBackPressed() {
        if (mTopLevelScreenDisplayed) {
            moveTaskToBack(true);
        } else {
            mDrawerToggle.setDrawerIndicatorEnabled(true);
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        // Sync the toggle state after onRestoreInstanceState has occurred.
        mDrawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        switch (item.getItemId()) {
            case android.R.id.home:
                // Pay attention to this implementation as it won't work in case more nested levels are added to the
                // hierarchy (one more level of deepness)
                mDrawerToggle.setDrawerIndicatorEnabled(true);
                onBackPressed();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Swaps fragments in the home_screen_menu content view
     */
    private void selectItem(int position) {

        switch (position) {
            case QUESTIONS_NAVIGATION_DRAWER_ITEM_POSITION:
                mProgressDialog.show();
                QuestionsFragment questionsFragment = new QuestionsFragment();
                mFragmentManager.beginTransaction()
                        .replace(R.id.content_frame, questionsFragment)
                        .commit();
                break;
            case YOUR_QUESTIONS_NAVIGATION_DRAWER_ITEM_POSITION:
                mProgressDialog.show();
                List<Filter> filters = new ArrayList<>(1);
                Filter yourQuestionsFilter = new Filter();
                yourQuestionsFilter.setFilterTitle("TestTitle"); // TODO delete this
                filters.add(yourQuestionsFilter);
                QuestionsFragment yourQuestionsFragment = QuestionsFragment.newInstance(filters);
                mFragmentManager.beginTransaction()
                        .replace(R.id.content_frame, yourQuestionsFragment)
                        .commit();
                break;
            case YOUR_ANSWERS_NAVIGATION_DRAWER_ITEM_POSITION:
                mProgressDialog.show();
                List<Filter> yourAnswersFilters = new ArrayList<>(1);
                Filter yourAnswersFilter = new Filter();
                yourAnswersFilter.setFilterTitle("TestTitle"); // TODO delete this
                yourAnswersFilters.add(yourAnswersFilter);
                QuestionsFragment yourAnswersFragment = QuestionsFragment.newInstance(yourAnswersFilters);
                mFragmentManager.beginTransaction()
                        .replace(R.id.content_frame, yourAnswersFragment)
                        .commit();
                break;
            case PROFILE_NAVIGATION_DRAWER_ITEM_POSITION:
                Intent profileActivityIntent = new Intent(this, ProfileActivity.class);
                startActivity(profileActivityIntent);
                break;
            case LOG_OUT_NAVIGATION_DRAWER_ITEM_POSITION:
                DialogHelper.displayLogoutConfirmationDialog(this);
                break;
        }

        mDrawerList.setItemChecked(position, true);
        setTitle(mDrawerItemsTitle[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }

    @Override
    public void setTitle(CharSequence title) {
        mTitle = title;
        mToolbar.setTitle(mTitle);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        selectItem(position);
    }

    @Override
    public void onDisplayBackButton() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }
        mDrawerToggle.setDrawerIndicatorEnabled(false);
    }

    @Override
    public void onTopLevelScreenDisplayed(boolean isTopLevelScreen) {
        mTopLevelScreenDisplayed = isTopLevelScreen;
    }

    @Override
    public void onQuestionsLoadingComplete() {
        mProgressDialog.dismiss();
    }
}