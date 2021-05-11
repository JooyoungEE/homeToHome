package com.example.hometohome.memo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Memo {

    public static final String MEMO_KEY = "memos";

    private int id;
    private String memo;
    private String date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMemo() {
        return memo;
    }

    public void setMemo(String memo) {
        this.memo = memo;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
