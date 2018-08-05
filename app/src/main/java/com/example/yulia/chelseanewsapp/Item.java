package com.example.yulia.chelseanewsapp;

import java.util.Date;

public class Item {
    private String mTitle;
    private String mSection;
    private String mAuthor;
    private String mUrl;
    private Date mDate;

    public Item(String title, String section, String author, String url, Date date){
        mTitle = title;
        mSection = section;
        mAuthor = author;
        mUrl = url;
        mDate = date;
    }

    public String getmTitle() {
        return mTitle;
    }

    public String getmSection(){
        return mSection;
    }

    public String getmAuthor() {
        return mAuthor;
    }

    public String getmUrl(){
        return mUrl;
    }

    public Date getmDate(){
        return mDate;
    }
}
