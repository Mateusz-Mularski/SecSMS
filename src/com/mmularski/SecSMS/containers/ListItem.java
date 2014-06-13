package com.mmularski.SecSMS.containers;

/**
 * Created by Admin on 02.06.14.
 */
public class ListItem {

    private String itemName =null;
    private int itemImage =0;


    public ListItem(String name, int image){
        itemName = name;
        itemImage = image;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getItemImage() {
        return itemImage;
    }

    public void setItemImage(int itemImage) {
        this.itemImage = itemImage;
    }
}


