package com.esp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.esp.constants.G;
import com.esp.model.ProfileData;
import com.esp.ui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Profile extends BaseFragment {

    private TextView profileName;

    public Profile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        ProfileData profileData = G.PROFILE_DATA;

//        profileName = (TextView) view.findViewById(R.id.profileNameId);
//        profileName.setText(profileData.getFirstName() +" "+profileData.getLastName());

        return view;
    }

}
