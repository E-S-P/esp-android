package com.esp.request;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;

import com.esp.constants.G;
import com.esp.model.ProfileData;
import com.esp.util.StringUtil;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.TextHttpResponseHandler;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.ByteArrayEntity;
import cz.msebera.android.httpclient.message.BasicHeader;
import cz.msebera.android.httpclient.protocol.HTTP;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;


/**
 * Created by edelito on 03/11/2016.
 */

public class RequestService {

    public static void getCountryCode(final Context mContext, final ProgressDialog prgDialog, String url){
        prgDialog.setMessage("Please wait...");
        prgDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        client.setTimeout(9000);
        client.get(url, null, new TextHttpResponseHandler() {
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String res) {
                        // called when response HTTP status is "200 OK"
                        if(!StringUtil.isNullOrEmpty(res)) {
                            JSONObject jsonObject = null;
                            try {
                                jsonObject = new JSONObject(res);
                                G.CODE = jsonObject.getString("countryCode");
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }

                        }
                        prgDialog.dismiss();
                    }

                    @Override
                    public void onFailure(int statusCode, Header[] headers, String res, Throwable t) {
                        // called when response HTTP status is "4XX" (eg. 401, 403, 404)
                        prgDialog.dismiss();
                    }
                }
        );
    }

    public static void makePost(final boolean isLogin, final Context mContext, final ProgressDialog prgDialog, String url, JSONObject jsonObject,
                                final Handler mHandler) {
        // Show Progress Dialog
        prgDialog.show();
        AsyncHttpClient client = new AsyncHttpClient();
        ByteArrayEntity entity = null;
        try {
            entity = new ByteArrayEntity(jsonObject.toString().getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        entity.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
        client.post(mContext, url, entity, "application/json", new JsonHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject obj) {
                try {

                    if(isLogin){
                        setProfileData(obj);
                    }
                    boolean error = obj.getBoolean("error");
                    int code = obj.getInt("code");
                    String message = "";
                    if (!obj.isNull("message")) {
                        message = obj.getString("message");
                        if (!error && code == 200) {
                            showAlertIfSuccess(mContext, "Success", message, "Continue", "Close", mHandler);
                        } else if (!error && code == 409) {
                            showAlertIfFailed(mContext, "Failed", message, "Close");
                        } else {
                            showAlertIfFailed(mContext, "Failed", message, "Close");
                        }
                    }else{
                        if (!error && code == 200) {
                            mHandler.sendEmptyMessage(0);
                        } else if (!error && code == 409) {
                            mHandler.sendEmptyMessage(1);
                        } else {
                            mHandler.sendEmptyMessage(1);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
                prgDialog.dismiss();
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, Throwable throwable, JSONObject errorResponse) {
                prgDialog.dismiss();
            }
        });

    }

    private static void showAlertIfSuccess(Context context, String title, String message, String positiveBtnText,
                                           String negativeBtnText, final Handler mHandler) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(positiveBtnText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mHandler.sendEmptyMessage(0);
                    }
                })
                .setNegativeButton(negativeBtnText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        mHandler.sendEmptyMessage(1);
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private static void showAlertIfFailed(Context context, String title, String message,
                                          String btnText) {
        new AlertDialog.Builder(context)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(btnText, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }


    private static void setProfileData(JSONObject jsonObject){
        ProfileData profileData = new ProfileData();
        try {
            profileData.setUserId(jsonObject.getString("userid"));
            profileData.setFirstName(jsonObject.getString("firstName"));
            profileData.setLastName(jsonObject.getString("lastName"));
            profileData.setUserName(jsonObject.getString("username"));
            profileData.setEmailAddress(jsonObject.getString("emailAddr"));
            profileData.setDob(jsonObject.getString("dob"));
            profileData.setGender(jsonObject.getString("gender"));
            profileData.setPhoneNumber(jsonObject.getString("phone"));
            profileData.setCity(jsonObject.getString("city"));
            profileData.setState(jsonObject.getString("state"));
            profileData.setZipCode(jsonObject.getString("zipcode"));
            profileData.setCountry(jsonObject.getString("country"));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        G.PROFILE_DATA = profileData;
    }

}
