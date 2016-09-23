package com.esp.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.esp.model.Event;
import com.esp.ui.R;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class Home extends BaseFragment {

    private List<Event> events;
    private LinearLayout eventLayout;


    public Home() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        eventLayout = (LinearLayout) view.findViewById(R.id.eventLayoutId);

        loadDummyEvents();
        loadEvents();

        return view;
    }

    private void loadDummyEvents(){
        events = new ArrayList<Event>();

        for (int i = 0; i < 5; i++){
            List<Integer> userIds = new ArrayList<>();
            userIds.add(1);
            userIds.add(2);
            userIds.add(3);

            List<String> linkPhotoVid = new ArrayList<String>();
            events.add(new Event(i+1, "Event"+i, null, "Welcome to event "+i+" Blah blah", "0", "0", "Address "+i,
                    "time", "comments", userIds, "email-sample"+i+"@gmail.com", linkPhotoVid));
        }

    }

    private void loadEvents(){
        if(eventLayout.getChildCount() > 0)
            eventLayout.removeAllViews();

        for (int i = 0; i < events.size(); i++){

            View eventView = mActivity.getLayoutInflater().inflate(R.layout.event_item, null);


            TextView title = (TextView) eventView.findViewById(R.id.eventTitleId);
            title.setText(events.get(i).getTitle());

//            TextView msg = (TextView) eventView.findViewById(R.id.eventMsgId);
//            msg.setText(events.get(i).getMessage());

            LinearLayout layoutComment = (LinearLayout) eventView.findViewById(R.id.layoutCommentId);

            eventLayout.addView(eventView);
        }
    }

}
