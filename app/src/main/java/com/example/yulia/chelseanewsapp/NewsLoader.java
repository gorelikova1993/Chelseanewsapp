package com.example.yulia.chelseanewsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class NewsLoader extends AsyncTaskLoader<List<Item>> {
    /** Tag for log messages */
    private static final String LOG_TAG = NewsLoader.class.getName();

    /** Query URL */
    private String mUrl;

    public NewsLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<Item> loadInBackground() {

        if (mUrl == null) {
            Log.e(LOG_TAG, "No info from Url.");
            return null;
        }
        List<Item> items = QueryUtils.fetchNewsData(mUrl);
        return items;
    }
}