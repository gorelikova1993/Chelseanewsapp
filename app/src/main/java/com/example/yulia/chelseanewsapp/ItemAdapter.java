package com.example.yulia.chelseanewsapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class ItemAdapter extends ArrayAdapter<Item> {
    private static final String NO_AUTHOR = "Unknown author";

    public ItemAdapter(Context context, List<Item> items) {
        super(context, 0, items);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        //Section
        Item currentItem = getItem(position);
        TextView sectionView = (TextView)listItemView.findViewById(R.id.section);
        sectionView.setText(currentItem.getmSection());

        //title
        TextView titlView = (TextView)listItemView.findViewById(R.id.title);
        titlView.setText(currentItem.getmTitle());

        //Date

        TextView dateView = (TextView)listItemView.findViewById(R.id.date);
        String fortmatedDate = formatDate(currentItem.getmDate());

        dateView.setText(fortmatedDate);


        //author
        TextView authorView = (TextView)listItemView.findViewById(R.id.author);
        String author = currentItem.getmAuthor();
        if (author == null) {
            authorView.setText(R.string.no_author);
        }
        else{
            authorView.setText(author);
        }

        return listItemView;
    }

    private String formatDate(Date dateObject) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return dateFormat.format(dateObject);
    }





}