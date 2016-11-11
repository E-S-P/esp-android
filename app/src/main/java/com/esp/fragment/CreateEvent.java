package com.esp.fragment;


import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.esp.constants.G;
import com.esp.ui.CreateEventName;
import com.esp.ui.CreateLocation;
import com.esp.ui.CreatePhotoVideo;
import com.esp.ui.CreateTimeRange;
import com.esp.ui.R;
import com.esp.util.StringUtil;

/**
 * A simple {@link Fragment} subclass.
 */

public class CreateEvent extends BaseFragment implements View.OnClickListener{

    private LinearLayout layoutEventNameBtn, layoutPhotoVideoBtn, layoutLocationBtn, layoutTimeBtn;
    private TextView eventNameTextWithImg;

    public CreateEvent() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_create_event, container, false);
        layoutEventNameBtn = (LinearLayout) view.findViewById(R.id.layoutEventNameBtnId);
        layoutEventNameBtn.setOnClickListener(this);
        layoutPhotoVideoBtn = (LinearLayout) view.findViewById(R.id.layoutPhotoVideoBtnId);
        layoutPhotoVideoBtn.setOnClickListener(this);
        layoutLocationBtn = (LinearLayout) view.findViewById(R.id.layoutLocationBtnId);
        layoutLocationBtn.setOnClickListener(this);
        layoutTimeBtn = (LinearLayout) view.findViewById(R.id.layoutTimeBtnId);
        layoutTimeBtn.setOnClickListener(this);

        eventNameTextWithImg = (TextView) view.findViewById(R.id.eventNameTextWithImgId);
        return view;
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.layoutEventNameBtnId:
                Intent eventName = new Intent(mActivity, CreateEventName.class);
                startActivity(eventName);
                break;
            case R.id.layoutPhotoVideoBtnId:
                Intent photoVideo = new Intent(mActivity, CreatePhotoVideo.class);
                startActivity(photoVideo);
                break;
            case R.id.layoutLocationBtnId:
                Intent location = new Intent(mActivity, CreateLocation.class);
                startActivity(location);
                break;
            case R.id.layoutTimeBtnId:
                Intent timeRange = new Intent(mActivity, CreateTimeRange.class);
                startActivity(timeRange);
                break;
            default:
                break;
        }
    }


    public void share(){
        mActivity.showToast("Nothing to share for now.");
    }


    @Override
    public void onResume() {
        super.onResume();

        if(!StringUtil.isNullOrEmpty(G.EVENT_NAME_VALUE)){
            Drawable image = mActivity.getResources().getDrawable( R.drawable.circle_light_tiffany );
            int h = image.getIntrinsicHeight();
            int w = image.getIntrinsicWidth();
            image.setBounds( 0, 0, w, h );
            eventNameTextWithImg.setCompoundDrawables(image, null, null, null);
        }else{
            Drawable image = mActivity.getResources().getDrawable( R.drawable.circle_light_gray );
            int h = image.getIntrinsicHeight();
            int w = image.getIntrinsicWidth();
            image.setBounds( 0, 0, w, h );
            eventNameTextWithImg.setCompoundDrawables(image, null, null, null);
        }

    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        G.EVENT_NAME_VALUE = "";
    }



}
