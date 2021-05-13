package com.example.aconmemo.util;

import android.content.Context;
import android.content.SharedPreferences;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {

    /**
     * 헌재 시간 가져오기
     */
    public static String getTimestamp(String format) {
        long now = System.currentTimeMillis();
        Date mDate = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        String getTime = simpleDateFormat.format(mDate);
        return getTime;
    }

    /**
     * SharedPreferences를 이용한 문자열 가져오기
     */
    public static String loadString(Context context, String key) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.hometohome", Context.MODE_PRIVATE);
        String JSONString = sharedPreferences.getString(key, null);
        return JSONString;
    }

    /**
     * SharedPreferences를 이용한 문자열 저장하기
     */
    public static void saveString(Context context, String key, String value) {
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.hometohome", Context.MODE_PRIVATE);
        sharedPreferences.edit().putString(key, value).commit();
    }

    /**
     * SharedPreferences를 이용한 문자열 삭제하기
     */
    public static void commitString(Context context){
        SharedPreferences sharedPreferences = context.getSharedPreferences("com.example.hometohome", Context.MODE_PRIVATE);
        sharedPreferences.edit().commit();
    }
}
