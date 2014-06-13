package com.mmularski.SecSMS.containers;

import java.util.LinkedList;

/**
 * Created by Mateusz Mularski
 * Copyright (c) 2014 Mateusz Mularski. All rights reserved.
 */
public class CategoryList {

    private static LinkedList<ListItem> categories=null;

    private CategoryList(){};

    public static void add(String name, int image){
        if(categories==null)
            categories=new LinkedList<ListItem>();
        categories.add(new ListItem(name, image));
    }

    public static LinkedList<ListItem> getInstance(){
        return categories;
    }
}
