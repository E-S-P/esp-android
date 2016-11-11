package com.esp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esp.constants.G;
import com.esp.ui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Search extends BaseFragment implements View.OnClickListener {

    private TextView byCountry, categories, favorites;


    public Search() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        byCountry = (TextView) view.findViewById(R.id.byCountryId);
        byCountry.setOnClickListener(this);
        categories = (TextView) view.findViewById(R.id.categoriesId);
        categories.setOnClickListener(this);
        favorites = (TextView) view.findViewById(R.id.favoritesId);
        favorites.setOnClickListener(this);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.byCountryId:
                byCountry.setTextColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));
                categories.setTextColor(ContextCompat.getColor(mActivity, R.color.black));
                favorites.setTextColor(ContextCompat.getColor(mActivity, R.color.black));
                break;
            case R.id.categoriesId:
                byCountry.setTextColor(ContextCompat.getColor(mActivity, R.color.black));
                categories.setTextColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));
                favorites.setTextColor(ContextCompat.getColor(mActivity, R.color.black));
                break;
            case R.id.favoritesId:
                byCountry.setTextColor(ContextCompat.getColor(mActivity, R.color.black));
                categories.setTextColor(ContextCompat.getColor(mActivity, R.color.black));
                favorites.setTextColor(ContextCompat.getColor(mActivity, R.color.colorPrimary));
                break;
            default:
                break;
        }
    }
}
