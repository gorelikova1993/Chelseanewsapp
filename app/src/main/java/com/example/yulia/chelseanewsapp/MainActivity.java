package com.example.yulia.chelseanewsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List <Item>>{

    public static final String LOG_TAG = MainActivity.class.getName();
    private static final String NEWS_URL = "https://content.guardianapis.com/search?show-fields=byline&q=Chelsea&api-key=ed09bd09-20c7-41db-8390-8784613f3b6b";
    public static final int LOADER_ID = 1;
    private ItemAdapter itemAdapter;
    private TextView empryTextView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView itemListView = (ListView) findViewById(R.id.list);
        itemAdapter = new ItemAdapter(MainActivity.this, new ArrayList<Item>());
        itemListView.setAdapter(itemAdapter);

        empryTextView = (TextView)findViewById(R.id.empty_view);
        itemListView.setEmptyView(empryTextView);

        itemListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Item currentItem = itemAdapter.getItem(position);
                Uri newsUri = Uri.parse(currentItem.getmUrl());
                Intent websiteIntent = new Intent(Intent.ACTION_VIEW, newsUri);
                startActivity(websiteIntent);
            }
        });

        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected){
            // Get a reference to the LoaderManager, in order to interact with loaders.
            LoaderManager loaderManager = getLoaderManager();

            // Initialize the loader. Pass in the int ID constant defined above and pass in null for
            // the bundle. Pass in this activity for the LoaderCallbacks parameter (which is valid
            // because this activity implements the LoaderCallbacks interface).
            loaderManager.initLoader(LOADER_ID, null, this);
        }
        else {
            View loadingIndicator = (View)findViewById(R.id.loading_spinner);
            loadingIndicator.setVisibility(View.GONE);
            empryTextView.setText(R.string.no_internet_connection);
        }

    }

    public Loader<List<Item>> onCreateLoader(int i, Bundle bundle) {
        // Create a new loader for the given URL
        return new NewsLoader(this, NEWS_URL);

    }

    @Override
    public void onLoadFinished(Loader<List<Item>> loader, List<Item> items) {
           itemAdapter.clear();

        View progresBar = (View)findViewById(R.id.loading_spinner);
        progresBar.setVisibility(View.GONE);


        if (items != null && !items.isEmpty()) {
                itemAdapter.addAll(items);
            }
            else {
            Log.e(LOG_TAG, "Adapter contains no data.");
        }

        empryTextView.setText(R.string.no_news);

    }

    @Override
    public void onLoaderReset(Loader<List<Item>> loader) {
        // Loader reset, so we can clear out our existing data.
        itemAdapter.clear();
    }
}
