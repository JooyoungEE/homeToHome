package com.example.aconmemo.memo;

import android.provider.BaseColumns;

public class MemoContract {
    private MemoContract(){}

    public static class MemoEntry implements BaseColumns{
        public static final String TABLE_NAME = "memo";
        public static final String COLUMN_MEMO = "note";
    }
}
