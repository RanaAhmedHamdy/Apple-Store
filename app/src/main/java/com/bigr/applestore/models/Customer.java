package com.bigr.applestore.models;

import java.util.HashMap;

/**
 * Created by Rana on 3/25/2016.
 */
public class Customer {
    private String name;
    private String email;
    private String phoneNumber;
    private String city;
    private String address;
    private HashMap<String, Object> timestampJoined;

    public Customer() {
    }

    public Customer(String name, String email, String phoneNumber, String city, String address, HashMap<String, Object> timestampJoined) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.city = city;
        this.address = address;
        this.timestampJoined = timestampJoined;
    }

    public Customer(String name, String email, HashMap<String, Object> timestampJoined) {
        this.name = name;
        this.email = email;
        this.timestampJoined = timestampJoined;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getAddress() {
        return address;
    }

    public HashMap<String, Object> getTimestampJoined() {
        return timestampJoined;
    }

    public String getCity() {
        return city;
    }
}
