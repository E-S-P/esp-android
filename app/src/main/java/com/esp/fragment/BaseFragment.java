package com.esp.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.esp.ui.Main;

/**
 * Created by eduardo on 9/5/16.
 */
public class BaseFragment extends Fragment {
    public Main mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity =	(Main) this.getActivity();
    }

    public boolean onBackPressed(){
        return false;
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data){

    }
}