package com.esp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.esp.ui.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Message extends BaseFragment {

    private ListView messageList;

    public Message() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_message, container, false);
        messageList = (ListView) view.findViewById(R.id.messageListId);

        return view;
    }

}
