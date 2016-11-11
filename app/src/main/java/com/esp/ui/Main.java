package com.esp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.esp.constants.G;
import com.esp.fragment.CreateEvent;
import com.esp.fragment.Message;
import com.esp.fragment.NewsFeed;
import com.esp.fragment.Profile;
import com.esp.fragment.Search;

import java.util.HashMap;
import java.util.Stack;

public class Main extends AppCompatActivity {

    private LinearLayout homeSelector, searchSelector, cameraSelector, heartSelector, profileSelector;
    public HashMap<String, Stack<Fragment>> mStacks;
    private String mCurrentList;
    private Fragment currentFragment;
    private ImageView espLogo;
    private TextView barTitle, shareEventBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        espLogo = (ImageView) findViewById(R.id.espLogo);
        barTitle = (TextView) findViewById(R.id.barTitle);
        shareEventBtn = (TextView) findViewById(R.id.shareEventBtn);

        homeSelector = (LinearLayout) findViewById(R.id.homeTabSelector);
        searchSelector = (LinearLayout) findViewById(R.id.searchTabSelector);
        cameraSelector = (LinearLayout) findViewById(R.id.cameraTabSelector);
        heartSelector = (LinearLayout) findViewById(R.id.heartTabSelector);
        profileSelector = (LinearLayout) findViewById(R.id.profileTabSelector);
        initFragment();
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.homeTab:
                espLogo.setVisibility(View.VISIBLE);
                barTitle.setVisibility(View.GONE);
                shareEventBtn.setVisibility(View.GONE);
                homeSelector.setVisibility(View.VISIBLE);
                searchSelector.setVisibility(View.GONE);
                cameraSelector.setVisibility(View.GONE);
                heartSelector.setVisibility(View.GONE);
                profileSelector.setVisibility(View.GONE);
                if (mCurrentList != G.HOME) {
                    currentFragment = new NewsFeed();
                    selectList(G.HOME, currentFragment);
                }
                break;
            case R.id.searchTab:

                espLogo.setVisibility(View.GONE);
                barTitle.setVisibility(View.VISIBLE);
                setTitle("Search Event");
                shareEventBtn.setVisibility(View.GONE);
                homeSelector.setVisibility(View.GONE);
                searchSelector.setVisibility(View.VISIBLE);
                cameraSelector.setVisibility(View.GONE);
                heartSelector.setVisibility(View.GONE);
                profileSelector.setVisibility(View.GONE);
                if (mCurrentList != G.SEARCH) {
                    currentFragment = new Search();
                    selectList(G.SEARCH, currentFragment);
                }
                break;
            case R.id.createEventTab:
                espLogo.setVisibility(View.GONE);
                barTitle.setVisibility(View.VISIBLE);
                setTitle("Create Event");
                shareEventBtn.setVisibility(View.VISIBLE);
                homeSelector.setVisibility(View.GONE);
                searchSelector.setVisibility(View.GONE);
                cameraSelector.setVisibility(View.VISIBLE);
                heartSelector.setVisibility(View.GONE);
                profileSelector.setVisibility(View.GONE);
                if (mCurrentList != G.CREATE_EVENT) {
                    currentFragment = new CreateEvent();
                    selectList(G.CREATE_EVENT, currentFragment);
                }
                break;
            case R.id.messageTab:
                espLogo.setVisibility(View.GONE);
                barTitle.setVisibility(View.VISIBLE);
                setTitle("Message");
                shareEventBtn.setVisibility(View.GONE);
                homeSelector.setVisibility(View.GONE);
                searchSelector.setVisibility(View.GONE);
                cameraSelector.setVisibility(View.GONE);
                heartSelector.setVisibility(View.VISIBLE);
                profileSelector.setVisibility(View.GONE);
                if (mCurrentList != G.MESSAGE) {
                    currentFragment = new Message();
                    selectList(G.MESSAGE, currentFragment);
                }
                break;
            case R.id.profileTab:
                espLogo.setVisibility(View.GONE);
                barTitle.setVisibility(View.VISIBLE);
                setTitle("Profile");
                shareEventBtn.setVisibility(View.GONE);
                homeSelector.setVisibility(View.GONE);
                searchSelector.setVisibility(View.GONE);
                cameraSelector.setVisibility(View.GONE);
                heartSelector.setVisibility(View.GONE);
                profileSelector.setVisibility(View.VISIBLE);
                if (mCurrentList != G.PROFILE) {
                    currentFragment = new Profile();
                    selectList(G.PROFILE, currentFragment);
                }
                break;
            case R.id.shareEventBtn:
                CreateEvent createEvent = (CreateEvent)mStacks.get(G.CREATE_EVENT).peek();
                if(createEvent != null){
                    createEvent.share();
                }
                break;
            default:
                break;
        }
    }

    private void initFragment() {
        mStacks = new HashMap<String, Stack<Fragment>>();
        mStacks.put(G.HOME, new Stack<Fragment>());
        mStacks.put(G.SEARCH, new Stack<Fragment>());
        mStacks.put(G.CREATE_EVENT, new Stack<Fragment>());
        mStacks.put(G.MESSAGE, new Stack<Fragment>());
        mStacks.put(G.PROFILE, new Stack<Fragment>());

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        mCurrentList = G.HOME;

        currentFragment = new NewsFeed();

        ft.replace(R.id.content_frame, currentFragment);
        ft.commit();

        mStacks.get(mCurrentList).push(currentFragment);
    }

    private void selectList(String listId, Fragment currFragment) {
        mCurrentList = listId;

        if (mStacks.get(listId).size() == 0) {
            pushFragments(listId, currFragment, false, true);
        } else {
            pushFragments(listId, mStacks.get(listId).lastElement(), false,
                    false);
        }
    }

    public void pushFragments(String tag, Fragment fragment,
                              boolean shouldAnimate, boolean shouldAdd) {

        if (!fragment.isAdded()) {
            if (shouldAdd)
                mStacks.get(tag).push(fragment);
            FragmentManager manager = getSupportFragmentManager();
            FragmentTransaction ft = manager.beginTransaction();
            if (shouldAnimate)
                ft.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left);
            ft.replace(R.id.content_frame, fragment);
            ft.commit();
        }
    }

    private void popFragments() {
        /*
         * Select the second last fragment in current tab's stack.. which will
		 * be shown after the fragment transaction given below
		 */
        Fragment fragment = mStacks.get(mCurrentList).elementAt(
                mStacks.get(mCurrentList).size() - 2);

		/* pop current fragment from stack.. */
        mStacks.get(mCurrentList).pop();

		/*
         * We have the target fragment in hand.. Just show it.. Show a standard
		 * navigation animation
		 */
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        ft.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
        ft.replace(R.id.content_frame, fragment);
        ft.commit();
    }

    public void setTitle(String title){
        barTitle.setText(title);
    }

    public void showToast(String msg){
        Toast.makeText(Main.this, msg, Toast.LENGTH_LONG).show();
    }
}
