package com.bigr.applestore.models;

/**
 * Created by Rana on 4/25/2016.
 */
public class Subcategory {
    private String name;
    private String image;

    public Subcategory() {

    }

    public Subcategory(String name, String image) {
        this.name = name;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Override
    public String toString() {
        return name;
    }
}
