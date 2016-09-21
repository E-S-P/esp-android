package com.esp.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;

import com.esp.constants.G;
import com.esp.fragment.Camera;
import com.esp.fragment.Heart;
import com.esp.fragment.Home;
import com.esp.fragment.Profile;
import com.esp.fragment.Search;

import java.util.HashMap;
import java.util.Stack;

public class Main extends AppCompatActivity {

    private LinearLayout homeSelector, searchSelector, cameraSelector, heartSelector, profileSelector;
    private HashMap<String, Stack<Fragment>> mStacks;
    private String mCurrentList;
    private Fragment currentFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
                homeSelector.setVisibility(View.VISIBLE);
                searchSelector.setVisibility(View.GONE);
                cameraSelector.setVisibility(View.GONE);
                heartSelector.setVisibility(View.GONE);
                profileSelector.setVisibility(View.GONE);
                if (mCurrentList != G.HOME) {
                    currentFragment = new Home();
                    selectList(G.HOME, currentFragment);
                }
                break;
            case R.id.searchTab:
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
            case R.id.cameraTab:
                homeSelector.setVisibility(View.GONE);
                searchSelector.setVisibility(View.GONE);
                cameraSelector.setVisibility(View.VISIBLE);
                heartSelector.setVisibility(View.GONE);
                profileSelector.setVisibility(View.GONE);
                if (mCurrentList != G.CAMERA) {
                    currentFragment = new Camera();
                    selectList(G.CAMERA, currentFragment);
                }
                break;
            case R.id.heartTab:
                homeSelector.setVisibility(View.GONE);
                searchSelector.setVisibility(View.GONE);
                cameraSelector.setVisibility(View.GONE);
                heartSelector.setVisibility(View.VISIBLE);
                profileSelector.setVisibility(View.GONE);
                if (mCurrentList != G.HEART) {
                    currentFragment = new Heart();
                    selectList(G.HEART, currentFragment);
                }
                break;
            case R.id.profileTab:
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
            default:
                break;
        }
    }

    private void initFragment() {
        mStacks = new HashMap<String, Stack<Fragment>>();
        mStacks.put(G.HOME, new Stack<Fragment>());
        mStacks.put(G.SEARCH, new Stack<Fragment>());
        mStacks.put(G.CAMERA, new Stack<Fragment>());
        mStacks.put(G.HEART, new Stack<Fragment>());
        mStacks.put(G.PROFILE, new Stack<Fragment>());


        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction ft = manager.beginTransaction();
        mCurrentList = G.HOME;

        currentFragment = new Home();

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



}
