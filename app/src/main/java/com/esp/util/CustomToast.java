package com.esp.util;

import android.content.Context;
import android.widget.Toast;

/**
 * Created by eduardo on 9/22/16.
 */

public class CustomToast {

    public static void toastLong(Context mContext, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_LONG).show();
    }

    public static void toastShort(Context mContext, String msg) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show();
    }
}
