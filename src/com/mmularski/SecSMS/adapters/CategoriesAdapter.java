package com.mmularski.SecSMS.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.mmularski.SecSMS.R;
import com.mmularski.SecSMS.containers.ListItem;
import com.mmularski.SecSMS.containers.CategoryList;

import java.util.LinkedList;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class CategoriesAdapter extends ArrayAdapter<ListItem> {
    Context context;
    LinkedList<ListItem> categories;

    public CategoriesAdapter(Context context){
        super(context, R.layout.categories, CategoryList.getInstance());
        this.context=context;
        this.categories=CategoryList.getInstance();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent){
        if(convertView==null){
            LayoutInflater inflater = ((Activity) context).getLayoutInflater();
            convertView = inflater.inflate(R.layout.categories, parent, false);
        }

        TextView label = (TextView) convertView.findViewById(R.id.labelBig);
        ImageView image = (ImageView) convertView.findViewById(R.id.icon);
        label.setText(categories.get(position).getItemName());
        image.setImageResource(categories.get(position).getItemImage());

        return convertView;
    }
}
