package com.esp.util;

/**
 * Created by eduardo on 7/23/16.
 */
public class StringUtil {
    public static boolean isNullOrEmpty(String str) {
        boolean isNullOrEmpty = false;

        if (str == null || str.trim().length() == 0 || str.equals("null"))
            isNullOrEmpty = true;
        return isNullOrEmpty;
    }
}
