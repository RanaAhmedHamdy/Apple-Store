package com.bigr.applestore.models;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Rana on 3/18/2016.
 */

public class Product implements Serializable {
    private String code;
    private String age;
    private String price;
    private String image;
    private String material;
    private String gender;
    private int count;
    private ArrayList<String> colors;
    private ArrayList<String> sizes;

    public Product() {

    }

    public Product(String code, String age, String price, String image, String material, String gender, int count, ArrayList<String> colors, ArrayList<String> sizes) {
        this.code = code;
        this.age = age;
        this.price = price;
        this.image = image;
        this.material = material;
        this.gender = gender;
        this.count = count;
        this.colors = colors;
        this.sizes = sizes;
    }

    public String getCode() {
        return code;
    }

    public String getAge() {
        return age;
    }

    public String getPrice() {
        return price;
    }

    public String getImage() {
        return image;
    }

    public String getMaterial() {
        return material;
    }

    public String getGender() {
        return gender;
    }

    public int getCount() {
        return count;
    }

    public ArrayList<String> getColors() {
        return colors;
    }

    public ArrayList<String> getSizes() {
        return sizes;
    }
}
