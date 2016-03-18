package com.bigr.applestore.models;

/**
 * Created by Rana on 3/18/2016.
 */
public class GridItem {
    private String title;
    private String image;

    public GridItem(String title, String image) {
        this.title = title;
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public String getImage() {
        return image;
    }
}
